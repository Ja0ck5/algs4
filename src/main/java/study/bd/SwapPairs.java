package study.bd;

import study.linkedlist.ListNode;

/**
 * 451. 两两交换链表中的节点
 * 中文English
 * 给一个链表，两两交换其中的节点，然后返回交换后的链表。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：1->2->3->4->null
 * 输出：2->1->4->3->null
 * 样例 2：
 * <p>
 * 输入：5->null
 * 输出：5->null
 * 挑战
 * 你的算法只能使用常数的额外空间，并且不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class SwapPairs {

    public ListNode swapPairs(ListNode head) {
        // write your code here
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.next != null) {
            ListNode n1 = head.next;
            ListNode n2 = head.next.next;

            head.next = n2;
            n1.next = n2.next;
            n2.next = n1;
            head = n1;
        }
        return dummy.next;
    }

}
