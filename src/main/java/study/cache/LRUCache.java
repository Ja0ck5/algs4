package study.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Least Recent Used，淘汰掉最不经常使用的
 */
class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }
}

//    void afterNodeInsertion(boolean evict) { // possibly remove eldest
//        LinkedHashMap.Entry<K,V> first;
//        if (evict && (first = head) != null && removeEldestEntry(first)) {
//            K key = first.key;
//            removeNode(hash(key), key, null, false, true);
//        }
//    }