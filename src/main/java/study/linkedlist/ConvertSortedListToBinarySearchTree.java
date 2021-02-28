package study.linkedlist;

import study.binarytree.TreeNode;

public class ConvertSortedListToBinarySearchTree {

    ListNode current;

    public TreeNode sortedListToBST(ListNode head) {
        int size;

        current = head;
        size = getListLength(head);

        return sortedListToBSTHelper(size);
    }

    private int getListLength(ListNode head) {
        int len = 0;
        if (head == null) {
            return 0;
        }
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }

    public TreeNode sortedListToBSTHelper(int size) {
        if (size < 1) {
            return null;
        }
        TreeNode left = sortedListToBSTHelper(size / 2);
        TreeNode root = new TreeNode(current.val);
        current = current.next;
        TreeNode right = sortedListToBSTHelper(size - 1 - size / 2);

        root.left = left;
        root.right = right;
        return root;
    }

}
