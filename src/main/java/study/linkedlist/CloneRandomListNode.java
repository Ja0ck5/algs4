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

}
