package study.binarytree;

import java.util.ArrayList;

public class SearchRangeInBinarySearchTree {

    private ArrayList<Integer> results;

    private ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        results = new ArrayList<>();
        helper(root, k1, k2);
        return results;
    }

    private void helper(TreeNode root, int k1, int k2) {
        if (root == null) {
            return;
        }
        if (root.val > k1) {
            helper(root.left, k1, k2);
        }
        if (root.val >= k1 && root.val <= k2) {
            results.add(root.val);
        }
        if (root.val < k2) {
            helper(root.right, k1, k2);
        }
    }

}
