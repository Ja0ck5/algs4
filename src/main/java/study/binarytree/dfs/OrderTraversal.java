package study.binarytree.dfs;

import study.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OrderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderTraverse(root, result);
        return result;
    }

    private void preorderTraverse(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorderTraverse(root.left, result);
        preorderTraverse(root.right, result);
    }

    public List<Integer> divideAndConquerTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // Divide
        List<Integer> left = divideAndConquerTraversal(root.left);
        List<Integer> right = divideAndConquerTraversal(root.right);

        // Conquer
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);

        return result;
    }

    public List<Integer> preorderTraversalVersion2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            results.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return results;
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    results.add(root.val);
                    root = root.right;
                }
            }
        }
        return results;
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            TreeNode c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && root != c.left && root != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && root != c.right) {
                    stack.push(c.right);
                } else {
                    results.add(stack.pop().val);
                    root = c;
                }
            }
        }
        return results;
    }


}
