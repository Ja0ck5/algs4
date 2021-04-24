package study.bd.jzoffer;

import study.linkedlist.ListNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * //输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * //
 * //
 * //
 * // 示例 1：
 * //
 * // 输入：head = [1,3,2]
 * //输出：[2,3,1]
 * //
 * //
 * //
 * // 限制：
 * //
 * // 0 <= 链表长度 <= 10000
 * // Related Topics 链表
 * // 👍 138 👎 0
 */
public class ReversePrintLinkedList {

    public int[] reversePrint(ListNode head) {
        Stack<Integer> s = new Stack<>();
        while(head != null){
            s.push(head.val);
            head = head.next;
        }
        int size = s.size();
        int[] ans = new int[size];
        for(int i=0; i<size;i++){
            ans[i] = s.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        ReversePrintLinkedList r = new ReversePrintLinkedList();
        ListNode head = new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);
        int[] ints = r.reversePrint(head);
        System.out.println(Arrays.toString(ints));
    }

}
