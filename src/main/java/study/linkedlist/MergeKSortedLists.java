package study.linkedlist;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 合并K 個排序鏈表
 * - 使用 Priority Queue
 */
public class MergeKSortedLists {

    private Comparator<ListNode> listNodeComparator = (o1, o2) -> {
        if (o1 == null) {
            return 1;
        } else if (o2 == null) {
            return -1;
        }
        return o1.val - o2.val;
    };

    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        Queue<ListNode> heap = new PriorityQueue<>(lists.size(), listNodeComparator);

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                heap.add(lists.get(i));
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!heap.isEmpty()) {
            ListNode head = heap.poll();
            tail.next = head;
            tail = head;
            if (head.next != null) {
                heap.add(head.next);
            }
        }

        return dummy.next;
    }

}
