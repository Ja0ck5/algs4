package study.linkedlist;

public class MidOfLinkedList {

    public ListNode midOrUpMid(ListNode head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode midOrDownMid(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode slow = head.next;
        ListNode fast = head.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public ListNode midOrUpMidPre(ListNode head){
        if(head == null || head.next == null || head.next.next != null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode midOrDownMidPre(ListNode head){
        if(head == null || head.next == null ){
            return null;
        }
        if(head.next.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
