##### Redis 是如何判断数据是否过期的呢

Redis 通过一个叫做过期字典（可以看作是 hash 表）来保存数据过期的时间。过期字典的键指向 Redis 数据库中的某个 key(键)，过期字典的值是一个 long long 类型的整数，这个整数保存了 key 所指向的数据库键的过期时间（毫秒精度的 UNIX 时间戳）。

过期字典是存储在 redisDb 这个结构里的：

```c++
typedef struct redisDb {
    ...

    dict *dict;     //数据库键空间,保存着数据库中所有键值对
    dict *expires   // 过期字典,保存着键的过期时间
    ...
} redisDb;
```


##### 过期的数据的删除策略

惰性删除 ：只会在取出 key 的时候才对数据进行过期检查。这样对 CPU 最友好，但是可能会造成太多过期 key 没有被删除。
定期删除 ： 每隔一段时间抽取一批 key 执行删除过期 key 操作。并且，Redis 底层会通过限制删除操作执行的时长和频率来减少删除操作对 CPU 时间的影响。
定期删除对内存更加友好，惰性删除对 CPU 更加友好。两者各有千秋，所以 Redis 采用的是 定期删除+惰性/懒汉式删除 。

但是，仅仅通过给 key 设置过期时间还是有问题的。因为还是可能存在定期删除和惰性删除漏掉了很多过期 key 的情况。这样就导致大量过期 key 堆积在内存里，然后就 Out of memory 了。

怎么解决这个问题呢？答案就是： **Redis 内存淘汰机制**。


##### Redis 内存淘汰机制

6 种数据淘汰策略

```text
1. volatile-lru（least recently used）：从已设置过期时间的数据集（server.db[i].expires）中挑选最近最少使用的数据淘汰

2. volatile-ttl：从已设置过期时间的数据集（server.db[i].expires）中挑选将要过期的数据淘汰

3. volatile-random：从已设置过期时间的数据集（server.db[i].expires）中任意选择数据淘汰

4. allkeys-lru（least recently used）：当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的 key（这个是最常用的）

5. allkeys-random：从数据集（server.db[i].dict）中任意选择数据淘汰

6. no-eviction：禁止驱逐数据，也就是说当内存不足以容纳新写入数据时，新写入操作会报错

4.0 版本后增加以下两种：

7. volatile-lfu（least frequently used）：从已设置过期时间的数据集(server.db[i].expires)中挑选最不经常使用的数据淘汰
8. allkeys-lfu（least frequently used）：当内存不足以容纳新写入数据时，在键空间中，移除最不经常使用的 key


```




##### Redis 持久化机制(怎么保证 Redis 挂掉之后再重启数据可以进行恢复)

###### 快照（snapshotting）持久化（RDB）

Redis 可以通过创建快照来获得存储在内存里面的数据在某个时间点上的副本。Redis 创建快照之后，可以对快照进行备份，可以将快照复制到其他服务器从而创建具有相同数据的服务器副本（Redis 主从结构，主要用来提高 Redis 性能），还可以将快照留在原地以便重启服务器的时候使用。

快照持久化是 Redis 默认采用的持久化方式，在 Redis.conf 配置文件中默认有此下配置：

```text

save 900 1           #在900秒(15分钟)之后，如果至少有1个key发生变化，Redis就会自动触发BGSAVE命令创建快照。

save 300 10          #在300秒(5分钟)之后，如果至少有10个key发生变化，Redis就会自动触发BGSAVE命令创建快照。

save 60 10000        #在60秒(1分钟)之后，如果至少有10000个key发生变化，Redis就会自动触发BGSAVE命令创建快照。
```


###### AOF（append-only file）持久化

与快照持久化相比，AOF 持久化 的实时性更好，因此已成为主流的持久化方案。默认情况下 Redis 没有开启 AOF（append only file）方式的持久化，可以通过 appendonly 参数开启：

appendonly yes
开启 AOF 持久化后每执行一条会更改 Redis 中的数据的命令，Redis 就会将该命令写入硬盘中的 AOF 文件。AOF 文件的保存位置和 RDB 文件的位置相同，都是通过 dir 参数设置的，默认的文件名是 appendonly.aof。

在 Redis 的配置文件中存在三种不同的 AOF 持久化方式，它们分别是：
```text

appendfsync always    #每次有数据修改发生时都会写入AOF文件,这样会严重降低Redis的速度
appendfsync everysec  #每秒钟同步一次，显示地将多个写命令同步到硬盘
appendfsync no        #让操作系统决定何时进行同步
```

为了兼顾数据和写入性能，用户可以考虑 appendfsync everysec 选项 ，让 Redis 每秒同步一次 AOF 文件，Redis 性能几乎没受到任何影响。而且这样即使出现系统崩溃，用户最多只会丢失一秒之内产生的数据。当硬盘忙于执行写入操作的时候，Redis 还会优雅的放慢自己的速度以便适应硬盘的最大写入速度。




##### AOF 重写

AOF 重写可以产生一个新的 AOF 文件，这个新的 AOF 文件和原有的 AOF 文件所保存的数据库状态一样，但体积更小。

AOF 重写是一个有歧义的名字，该功能是通过读取数据库中的键值对来实现的，程序无须对现有 AOF 文件进行任何读入、分析或者写入操作。

在执行 BGREWRITEAOF 命令时，Redis 服务器会维护一个 AOF 重写缓冲区，该缓冲区会在子进程创建新 AOF 文件期间，记录服务器执行的所有写命令。当子进程完成创建新 AOF 文件的工作之后，服务器会将重写缓冲区中的所有内容追加到新 AOF 文件的末尾，使得新旧两个 AOF 文件所保存的数据库状态一致。最后，服务器用新的 AOF 文件替换旧的 AOF 文件，以此来完成 AOF 文件重写操作



##### 不支持 rollback

Redis 是不支持 roll back 的，因而不满足原子性的（而且不满足持久性）。

Redis 官网也解释了自己为啥不支持回滚。简单来说就是 Redis 开发者们觉得没必要支持回滚，这样更简单便捷并且性能更好。Redis 开发者觉得即使命令执行错误也应该在开发过程中就被发现而不是生产过程中。


## Redis 数据类型及编码

### String类型的内部编码情况
字符串是 Redis最基本的数据类型，Redis 中字符串对象的编码可以是 int，raw 或者 embstr 中的某一种，分别介绍如下：
```
1. int 编码：保存long 型的64位有符号整数
2. embstr 编码：保存长度小于44字节的字符串
3. raw 编码：保存长度大于44字节的字符串
```

Redis 是使用 SDS（“简单动态字符串”）这个结构体来存储字符串，代码里定义了 5种 SDS结构体：

```c++

struct __attribute__ ((__packed__)) sdshdr5 {
    unsigned char flags; /* 3 lsb of type, and 5 msb of string length */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr8 {
    uint8_t len; /* used */
    uint8_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr16 {
    uint16_t len; /* used */
    uint16_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr32 {
    uint32_t len; /* used */
    uint32_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr64 {
    uint64_t len; /* used */
    uint64_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};


```

#### INT 编码格式
字符串键值的内容可以用一个 64位有符号整形 来表示时，Redis会将键值转化为 long型来进行存储，此时即对应 OBJ_ENCODING_INT 编码类型

Redis 启动时会预先建立 10000 个分别存储 0~9999 的 redisObject 变量作为共享对象，这就意味着如果 set字符串的键值在 0~10000 之间的话，则可以 直接指向共享对象 而不需要再建立新对象，此时键值不占空间！

#### EMBSTR编码格式
Redis 在保存长度小于 44 字节的字符串时会采用 OBJ_ENCODING_EMBSTR 编码方式

EMBSTR 顾名思义即：embedded string，表示嵌入式的String。从内存结构上来讲 即字符串 sds结构体与其对应的 redisObject 对象分配在 **同一块连续的内存空间**，这就仿佛字符串 sds 嵌入在 redisObject 对象之中


#### RAW 编码格式

当字符串的键值为长度大于 44 的 超长字符串 时，Redis 则会将键值的内部编码方式改为 OBJ_ENCODING_RAW 格式，这与上面的 OBJ_ENCODING_EMBSTR 编码方式的不同之处在于 此时动态字符串 sds 的内存与其依赖的 redisObject 的 **内存不再连续** 


基于redis 5.0.5）来分析下
在redis中，的每个键值内部都是使用一个名字叫做 redisObject 这个 C语言结构体保存的

```c++
typedef struct redisObject {
    unsigned type:4;
    unsigned encoding:4;
    unsigned lru:LRU_BITS; /* LRU time (relative to global lru_clock) or
                            * LFU data (least significant 8 bits frequency
                            * and most significant 16 bits access time). */
    int refcount;
    void *ptr;
} robj

```

type：表示键值的数据类型，包括 String、List、Set、ZSet、Hash
refcount：表示该键值被引用的数量，即一个键值可被多个键引用
encoding：表示键值的内部编码方式，从 Redis源码看目前取值有如下几种：

```c++
/* Objects encoding. Some kind of objects like Strings and Hashes can be
 * internally represented in multiple ways. The 'encoding' field of the object
 * is set to one of this fields for this object. */
#define OBJ_ENCODING_RAW 0     /* Raw representation */
#define OBJ_ENCODING_INT 1     /* Encoded as integer */
#define OBJ_ENCODING_HT 2      /* Encoded as hash table */
#define OBJ_ENCODING_ZIPMAP 3  /* Encoded as zipmap */
#define OBJ_ENCODING_LINKEDLIST 4 /* No longer used: old list encoding. */
#define OBJ_ENCODING_ZIPLIST 5 /* Encoded as ziplist */
#define OBJ_ENCODING_INTSET 6  /* Encoded as intset */
#define OBJ_ENCODING_SKIPLIST 7  /* Encoded as skiplist */
#define OBJ_ENCODING_EMBSTR 8  /* Embedded sds string encoding */
#define OBJ_ENCODING_QUICKLIST 9 /* Encoded as linked list of ziplists */
#define OBJ_ENCODING_STREAM 10 /* Encoded as a radix tree of listpacks */
```

### list类型的内部编码情况
1、使用压缩列表（ziplist）实现的列表对象
2、使用双端链表（quicklist）实现的列表对象

 ziplist 与 quicklist 之间存在着一种编码转换机制，当列表对象可以同时满足下列两个条件时
 列表对象采用ziplist编码，否则采用quicklist编码
```text
（1）列表对象保存的所有字符串元素的长度都小于64字节
（2）列表元素保存的元素数量小于512个
```

### hash
1. dict  512<节点数量 && 64<字符长度
2. ziplist  节点数量 < 512 || 字符长度 < 64

### set 
1. intset 整数集合, 元素都为整数且 节点数量 <= 512
2. dict 元素不为整数或者节点数量 > 512

### zset
1. skiplist 跳表，数量>128 || 字符长度 > 64

理想的跳表结构的问题：增删改查 
利用概率解决：一个节点出发，每次插入的时候按概率1/4生成 层级(最多32层)。
按照数学期望来说，理想的跳表结构的时间复杂度趋于 O(logn), 但是概率需要基于大量的数据才能趋于稳定，而这里经过验证，为 128

2. ziplist 否则压缩列表

#### hash 与 zset 谁更占内存？

hash 最差的情况应该是 5n(准备扩容时：有 fork 操作时，服务器正在执行BGSAVE或BGREWRITEAOF命令，并且哈希表负载因子大于等于5)
![Redis-hash-zset-m.PNG](./Redis-compare-hash-zset.PNG)


## 扩容方式

1. Redis 没有fork 操作时(bgsave, bgrewriteaof)，hashtable 负载因子>=1 时 扩容
2. 有 fork 操作时，服务器正在执行BGSAVE或BGREWRITEAOF命令，并且哈希表负载因子大于等于5

区分这两种情况的目的在于，因为执行BGSAVE与BGREWRITEAOF过程中，Redis都需要创建子进程，

而大多数操作系统都采用写时复制技术来优化子进程使用效率，所以在子进程存在期间，

服务器会提高执行扩展操作所需的负载因子，

从而尽可能避免在子进程存在期间进行哈希表扩展操作，这可以避免不必要的内存写入，最大限度的节约空间。

### 缩容
当哈希表负载因子小于0.1时，程序自动开始对哈希表执行收缩操作

### 渐进式rehash

　　Redis中的rehash动作并不是一次性、集中式完成的，而是分多次、渐进式的完成的。

　　这样做的目的是，如果服务器中包含很多键值对，要一次性的将这些键值对全部rehash到ht[1]的话，庞大的计算量可能导致服务器在一段时间内停止服务。

　　为了避免这种影响，Redis采用了渐进式Rehash：

1. 每次增删改查，rehash 1次(数组某个槽位的所有数据)
2. 在 redis 没有 fork 操作的时候，定时执行，每次执行 1ms ，每次执行 100次(数组相邻100个槽位中所有数据)


```text

　　1、为ht[1]分配空间，让字典同时持有ht[0]和ht[1]两个哈希表。

　　2、在字典中维持一个索引计数器变量rehashidx，并将它置为0，表示rehash工作开始。

　　3、在rehash进行期间，每次对字典执行添加、删除、查找或者更新操作时，程序除了执行指定操作以外，还会顺带将ht[0]哈希表在rehashidx索引上的所有键值对rehash到ht[1]中，当rehash工作完成之后，程序将rehashidx属性的值+1。

　　4、随着字典操作的不断进行，最终在某个时间点上，ht[0]的所有键值对都被rehash到ht[1]上，这时将rehashidx属性设为-1，表示rehash完成。

　　渐进式rehash 的好处在于其采取分而治之的方式，将rehash键值对所需要的计算工作均摊到字典的每个添加、删除、查找和更新操作上，从而避免了集中式rehash而带来的庞大计算量。
```

渐进式rehash执行期间的哈希表操作

　　因为在渐进式rehash的过程中，字典会同时使用ht[0]和ht[1]两个哈希表，所以在渐进式rehash进行期间，字典的删除、查找、更新等操作都是在两个表上进行的。

　　例如，查找操作会先在ht[0]上进行，如果没找到再在ht[1]上进行。

　　添加操作的键值对会一律保存到ht[1]中，这一措施保证ht[0]包含的键值对只会减少不会增加。

### BloomFilter 布隆过滤器

![Redis-hash-zset-m.PNG](./BloomFilter-principle.PNG)