package study.linkedlist;

/**
 * 判断链表是否有环
 */
public class LoopListNode {

    public ListNode getLoopNode(ListNode head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }

        ListNode s = head.next;
        ListNode f = head.next.next;
        while(s != f){
            if(f.next == null || f.next.next == null){
                return null;
            }
            f = f.next.next;
            s = s.next;
        }

        f = head;
        while(s != f){
            f = f.next;
            s = s.next;
        }
        return s;
    }

}
