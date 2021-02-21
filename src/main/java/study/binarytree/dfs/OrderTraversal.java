package study.binarytree.dfs;

import study.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

}
