package study.bd;

import study.binarytree.TreeNode;

/**
 * 二叉树叶子节点之和
 */
public class BinaryTreeLeavesSum {

    public int leafSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int sum = 0;
        sum += leafSum(root.left);
        sum += leafSum(root.right);
        return sum;
    }

}
