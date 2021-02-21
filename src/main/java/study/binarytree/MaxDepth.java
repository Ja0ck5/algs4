package study.binarytree;

public class MaxDepth {

    public int maxDepth(TreeNode root) {
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

}
