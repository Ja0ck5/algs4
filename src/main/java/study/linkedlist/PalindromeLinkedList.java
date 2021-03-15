package study.linkedlist;

import java.util.Stack;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode firstHalfEnd = firstHalfEndList(head);
        ListNode secondReverseList = reverseList(firstHalfEnd.next);

        ListNode p1 = head;
        ListNode p2 = secondReverseList;

        while(p2 != null){
            if(p1.val != p2.val){
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        firstHalfEnd.next = reverseList(secondReverseList);
        return true;
    }

    public ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode p = head;
        while(p != null){
            ListNode tmp = p.next;
            p.next = prev;
            prev = p;
            p = tmp;
        }
        return prev;
    }

    public ListNode firstHalfEndList(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
