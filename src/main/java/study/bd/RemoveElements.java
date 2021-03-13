package study.bd;

import study.linkedlist.ListNode;

/**
 * 452. 删除链表中的元素
 * 中文English
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 样例
 * 样例 1：
 * <p>
 * 输入：head = 1->2->3->3->4->5->3->null, val = 3
 * 输出：1->2->4->5->null
 * 样例 2：
 * <p>
 * 输入：head = 1->1->null, val = 1
 * 输出：null
 */
public class RemoveElements {

    /**
     * @param head: a ListNode
     * @param val:  An integer
     * @return: a ListNode
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null) {
            if (head.next.val == val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }


}
