package study.linkedlist;

/**
 * 给定两个链表，可能有环无环，如果相交则返回第一个相交的节点，否则 null
 */
public class IntersectionNodeLinkedList {

    public ListNode getIntersectionNodeNoLoop(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        int n = 0;
        while(p1.next != null){
            p1 = p1.next;
            n++;
        }
        while(p2.next != null){
            p2 = p2.next;
            n--;
        }
        if(p1 != p2){
            return null;
        }

        p1 = n > 0 ? headA : headB;
        p2 = p1 == headA ? headB : headA;
        n = Math.abs(n);
        while (n != 0){
            p1 = p1.next;
            n--;
        }
        while(p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

}
