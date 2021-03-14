package study.bd;

import study.binarytree.TreeNode;

/**
 * 最大二叉搜索子树 size
 */
public class MaxBinarySearchTree {

    public int getMaxBinarySearchTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxSubBSTSize;

    }

    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int min = root.val;
        int max = root.val;
        int maxSubBSTSize = 0;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(min, leftInfo.max);
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(min, rightInfo.max);
            maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
        }
        boolean isBST = false;
        if ((leftInfo == null || leftInfo.isBST)
                && (rightInfo == null || rightInfo.isBST)
                && (leftInfo == null || leftInfo.max < root.val)
                && (rightInfo == null || rightInfo.min > root.val)) {
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
            isBST = true;
        }
        return new Info(isBST, maxSubBSTSize, min, max);
    }

    class Info {
        public boolean isBST;

        public int maxSubBSTSize;

        public int min;

        public int max;

        public Info(boolean isBST, int maxSubBSTSize, int min, int max) {
            this.isBST = isBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
        }
    }


}

