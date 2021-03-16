package study.linkedlist;

/**
 * 给定两个链表，可能有环无环，如果相交则返回第一个相交的节点，否则 null
 */
public class IntersectionNodeLinkedList {

    public ListNode getIntersectionNodeNoLoop(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        int n = 0;
        while (p1.next != null) {
            p1 = p1.next;
            n++;
        }
        while (p2.next != null) {
            p2 = p2.next;
            n--;
        }
        if (p1 != p2) {
            return null;
        }

        p1 = n > 0 ? headA : headB;
        p2 = p1 == headA ? headB : headA;
        n = Math.abs(n);
        while (n != 0) {
            p1 = p1.next;
            n--;
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }


    public ListNode getIntersectionNodeBothLoop(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode loopA = getLoopNode(headA);
        ListNode loopB = getLoopNode(headB);
        if (loopA == null && loopB == null) {
            return getIntersectionNodeNoLoop(headA, headB);
        } else if (loopA == null || loopB == null) {
            return null;
        } else {
            if (loopA == loopB) {
                ListNode p1 = headA;
                ListNode p2 = headB;
                int n = 0;
                while (p1.next != loopA) {
                    p1 = p1.next;
                    n++;
                }
                while (p2.next != loopB) {
                    p2 = p2.next;
                    n--;
                }
                if (p1 != p2) {
                    return null;
                }

                p1 = n > 0 ? headA : headB;
                p2 = p1 == headA ? headB : headA;
                n = Math.abs(n);
                while (n != 0) {
                    p1 = p1.next;
                    n--;
                }
                while (p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
                return p1;
            } else {
                ListNode p1 = loopA.next;
                while (p1 != loopA) {
                    if (p1 == loopB) {
                        return loopA;
                    }
                    p1 = p1.next;
                }
                return null;
            }
        }
    }

    public ListNode getLoopNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode s = head.next;
        ListNode f = head.next.next;
        while (s != f) {
            if (f.next == null || f.next.next == null) {
                return null;
            }
            f = f.next.next;
            s = s.next;
        }

        f = head;
        while (s != f) {
            f = f.next;
            s = s.next;
        }
        return s;
    }

}
