package study.binarytree.dfs;

import edu.princeton.cs.algs4.Stack;
import study.binarytree.TreeNode;

public class ImplementsIteratorOfBinarySearchTree {

    private Stack<TreeNode> stack = new Stack<>();

    private TreeNode cur;

    public ImplementsIteratorOfBinarySearchTree(TreeNode root) {
        this.cur = root;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }

    public TreeNode next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        TreeNode node = cur;
        cur = cur.right;
        return node;
    }

}
