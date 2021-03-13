package study.bd;

import study.linkedlist.ListNode;

/**
 * 174. 删除链表中倒数第n个节点
 * 中文English
 * 给定一个链表，删除链表中倒数第n个节点，返回链表的头节点。
 * <p>
 * <p>
 * <p>
 * 样例
 * 样例 1:
 * 输出: list = 1->2->3->4->5->null， n = 2
 * 输出: 1->2->3->5->null
 * <p>
 * <p>
 * 样例 2:
 * 输入:  list = 5->4->3->2->1->null, n = 2
 * 输出: 5->4->3->1->null
 * <p>
 * 挑战
 * O(n)时间复杂度
 * <p>
 * 注意事项
 * 链表中的节点个数大于等于n
 */
public class RemoveNthFromEnd {

    /**
     * @param head: The first node of linked list.
     * @param n:    An integer
     * @return: The head of linked list.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        int len = 1;
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
            len++;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        int idx = len - n, i = 0;
        while (head.next != null) {
            if (i == idx) {
                head.next = head.next.next;
                break;
            } else {
                head = head.next;
            }
            i++;
        }
        return dummy.next;
    }
}
