package study.bd;//请你实现一个数据结构支持以下操作：
//
// 
// Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。 
// Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任
//何事情。key 保证不为空字符串。 
// GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串"" 。 
// GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。 
// 
//
// 
//
// 挑战： 
//
// 你能够以 O(1) 的时间复杂度实现所有操作吗？ 
// Related Topics 设计 
// 👍 89 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class AllOne {

    /**
     * k-v查找节点
     */
    private final Map<String, ListNode> map = new HashMap<>();
    /**
     * key - 节点的值； value - 链表中第一个值为key的节点。
     */
    private final Map<Integer, ListNode> first = new HashMap<>();
    /**
     * key - 节点的值； value - 链表中最后一个值为key的节点。
     */
    private final Map<Integer, ListNode> last = new HashMap<>();

    /**
     * 链表伪头节点
     */
    private final ListNode head = new ListNode(null, 0);
    /**
     * 链表伪尾节点
     */
    private final ListNode tail = new ListNode(null, 0);

    AllOne() {
        head.next = tail;
        tail.prev = head;
    }

    private class ListNode { // 链表节点

        ListNode prev, next;
        String key;
        int val;

        public ListNode(String key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * 将节点 [insert] 插入到 n1 与 n2 之间
     */
    private void insert(ListNode n1, ListNode n2, ListNode insert) {
        n1.next = insert;
        n2.prev = insert;
        insert.prev = n1;
        insert.next = n2;
    }

    /**
     * 删除链表节点[n]
     */
    private void remove(ListNode n) {
        ListNode prev = n.prev;
        ListNode next = n.next;
        prev.next = next;
        next.prev = prev;
        n.prev = null;
        n.next = null;
    }

    /**
     * 将节点node移动到prev与next之间
     */
    private void move(ListNode node, ListNode prev, ListNode next) {
        remove(node);
        insert(prev, next, node);
    }

    /**
     * 将[node]设置为新的val值起始点
     */
    private void newFirst(int val, ListNode node) {
        first.put(val, node);
        if (!last.containsKey(val)) {
            last.put(val, node);
        }
    }

    /**
     * 将[node]设置为新的val值终止点
     */
    private void newLast(int val, ListNode node) {
        last.put(val, node);
        if (!first.containsKey(val)) {
            first.put(val, node);
        }
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     * <p>
     * 值加一后，当前节点会往左移动。 如果当前key不存在，那就把这个节点插入到链表尾部.
     */
    public void inc(String key) {
        if (!map.containsKey(key)) { // 当前key不存在，插入到链表末尾
            ListNode node = new ListNode(key, 1);
            map.put(key, node);
            insert(tail.prev, tail, node); // 插入
            if (!first.containsKey(1)) {
                newFirst(1, node); // 更新first
            }
            newLast(1, node); // 更新last
        } else {
            ListNode node = map.get(key); // 当前节点
            int val = node.val; // 旧值
            int newVal = val + 1; // 新值
            ListNode firstNode = first.get(val); // 链表中第一个值为val的节点
            ListNode lastNode = last.get(val); // 链表中最后一个值为val的节点

            // 1. 找位置
            node.val = newVal;
            if (firstNode == lastNode) { // 当前节点是唯一一个值为val的节点
                first.remove(val); // 没有值为val的节点了
                last.remove(val); // 没有值为val的节点了
                newLast(newVal, node); // 更新last
            } else if (node == firstNode) { // 该节点是链表中第一个值为val的节点
                // 不动
                newLast(newVal, node);
                newFirst(val, node.next);
            } else {
                if (node == lastNode) {
                    newLast(val, node.prev); // 是最后一个值val的节点
                }
                // 这个时候，节点应该移动到链表中第一个值为val的节点之前
                move(node, firstNode.prev, firstNode);
                newLast(newVal, node);
            }
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     *
     * 值减一之后，节点在链表中的位置会往右移动
     */
    public void dec(String key) {
        // 与inc类似，不过多了一个值为1删除的判断
        ListNode node = map.get(key);
        if (node == null) {
            return;
        }

        int val = node.val;
        int newVal = val - 1;
        ListNode firstNode = first.get(val);
        ListNode lastNode = last.get(val);

        if (val == 1) { // 值为1，删除这个节点
            if (firstNode == lastNode) { // 没有值为1的节点了
                first.remove(1);
                last.remove(1);
            } else if (node == firstNode) { // 起始值右移
                first.put(1, node.next);
            } else if (node == lastNode) { // 终结值左移
                last.put(1, node.prev);
            }
            remove(node);
            map.remove(key);
        } else {
            node.val = newVal;
            if (firstNode == lastNode) { // 唯一值为val的节点
                // 位置不变，成为newVal的首位
                first.remove(val);
                last.remove(val);
                newFirst(newVal, node);
            } else if (node == lastNode) { // 是最后一项val值的节点
                // 位置不变，成为newVal的首位，并且prev成为val的最后一位
                newFirst(newVal, node);
                newLast(val, node.prev);
            } else {
                if (node == firstNode) {
                    newFirst(val, node.next); // 是第一项val值的节点
                }
                move(node, lastNode, lastNode.next); // 移动到lastNode之后
                newFirst(newVal, node);
            }
        }
    }

    /**
     * Returns one of the keys with maximal value. 返回链表头
     */
    public String getMaxKey() {
        return head.next == tail ? "" : head.next.key;
    }

    /**
     * Returns one of the keys with Minimal value. 返回链表尾
     */
    public String getMinKey() {
        return tail.prev == head ? "" : tail.prev.key;
    }
}

/**
 * Your AllOne object will be instantiated and called as such: AllOne obj = new AllOne(); obj.inc(key); obj.dec(key); String param_3 =
 * obj.getMaxKey(); String param_4 = obj.getMinKey();
 */
//leetcode submit region end(Prohibit modification and deletion)
