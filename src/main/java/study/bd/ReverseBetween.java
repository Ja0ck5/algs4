package study.bd;

import study.linkedlist.ListNode;

public class ReverseBetween {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null || left >= right) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        int i = 0;
        while(pre != null && i < left - 1){
            pre = pre.next;
            i++;
        }

        ListNode end = pre;
        i = 0;
        while(end != null && i < right -left + 1){
            end = end.next;
            i++;
        }

        ListNode start = pre.next;
        ListNode next = end.next;

        pre.next = null;
        end.next = null;

        reverse(start);

        pre.next = end;
        start.next = next;
        return dummy.next;
    }

    ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode node = head;
        while(node != null){
            ListNode tmp = node.next;
            node.next = pre;
            pre = node;
            node = tmp;
        }
        return pre;
    }



}
