package study.bd;

import study.linkedlist.ListNode;

/**
 * @author liyanjie
 * @createTime 2021-04-16 11:17
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                dummy.next = left;
                left = left.next;
            } else {
                dummy.next = right;
                right = right.next;
            }
            dummy = dummy.next;
        }
        dummy.next = left != null ? left : right;
        return res.next;
    }
}
