package study.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 克隆 RandomListNode
 */
public class CloneRandomListNode {

    public RandomListNode clone(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode p = head;
        while (p != null) {
            map.put(p, new RandomListNode(p.label));
            p = p.next;
        }

        p = head;
        while (p != null) {
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
            p = p.next;
        }

        return map.get(head);
    }

    /**
     * 1. 1->1'->2->2'->3->3' 2. copy 3. split
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode p = head;

        while (p != null) {
            RandomListNode tmp = p.next;
            p.next = new RandomListNode(p.label);
            p.next.next = tmp;
            p = tmp;
        }

        p = head;
        RandomListNode copy;
        while (p != null) {
            RandomListNode tmp = p.next.next;
            copy = p.next;
            copy.random = p.random != null ? p.random.next : null;
            p = tmp;
        }

        RandomListNode res = head.next;
        p = head;
        while (p != null) {
            RandomListNode tmp = p.next.next;
            copy = p.next;
            p.next = tmp;
            copy.next = tmp != null ? tmp.next : null;
            p = tmp;
        }

        return res;
    }


}
