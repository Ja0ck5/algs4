package study.bd.vip;

import study.linkedlist.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        ListNode pre, cur, next;
        while(node.next != null && node.next.next != null){
            pre=node;
            next = node.next.next;
            cur = node.next;

            ListNode tmp = next.next;
            pre.next = next;
            next.next = cur;
            cur.next = tmp;

            node = cur;
        }
        return dummy.next;
    }
}
