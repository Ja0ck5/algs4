package study.cache;

import java.util.*;

public class LRUTest {

    static class Node {
        Node prev, next;
        int key, val;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int k;
    private Map<Integer, Node> map = new HashMap<>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public int[] LRU(int[][] operators, int k) {
        this.k = k;
        head.next = tail;
        tail.prev = head;
        int len = (int) Arrays.stream(operators).filter(x -> x[0] == 2).count();
        int[] res = new int[len];
        int cnt = 0;
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                set(operators[i][1], operators[i][2]);
            } else {
                res[cnt++] = get(operators[i][1]);
            }
        }
        return res;
    }

    public void set(int key, int value) {
        if (get(key) > -1) {
            map.get(key).val = value;
        } else {
            if (map.size() == k) {
                map.remove(tail.prev.key);
                tail.prev.prev.next = tail;
                tail.prev = tail.prev.prev;
            }
            Node node = new Node(key, value);
            map.put(key, node);
            removeToHead(node);
        }
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            // rem node
            node.prev.next = node.next;
            node.next.prev = node.prev;
            removeToHead(node);
            return node.val;
        }
        return -1;
    }

    private void removeToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        List<Integer> list = new ArrayList<>();
    }

}
