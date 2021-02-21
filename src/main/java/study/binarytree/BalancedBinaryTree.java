package study.binarytree;

/**
 * 平衡二叉树，左右子树深度差不超过 1
 * 左右子树分别为平衡二叉树
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        // left || right 都非平衡，或者 left - right > 1 返回 -1(表示非平衡)
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

}
