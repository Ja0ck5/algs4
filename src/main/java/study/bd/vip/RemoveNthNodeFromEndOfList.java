package study.bd.vip;

import study.linkedlist.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [1], n = 1
 * 输出：[]
 * <p>
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int len = len(head);
        int k = len - n + 1;
        ListNode node = dummy;
        while (k-- > 1) {
            node = node.next;
        }
        node.next = node.next.next;
        return dummy.next;
    }

    int len(ListNode head) {
        ListNode node = head;
        int i = 0;
        while (node != null) {
            node = node.next;
            i++;
        }
        return i;
    }
}
