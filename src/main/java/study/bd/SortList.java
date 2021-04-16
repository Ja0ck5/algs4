package study.bd;

import study.linkedlist.ListNode;

/**
 * @author liyanjie
 * @createTime 2021-04-16 11:17
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode fast = head.next, slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid = slow.next;
        slow.next = null;

        ListNode l = sortList(head);
        ListNode r = sortList(mid);
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;

        while(l != null && r != null){
            if(l.val < r.val){
                dummy.next = l;
                l = l.next;
            }else{
                dummy.next = r;
                r = r.next;
            }
            dummy = dummy.next;
        }

        dummy.next = l != null ? l : r;
        return res.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(4);
        node.next = new ListNode(2);
        node.next.next = new ListNode(1);
        node.next.next.next = new ListNode(6);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(2);
        SortList sortList = new SortList();
        ListNode listNode = sortList.sortList(node);
        System.out.println(listNode);
    }
}
