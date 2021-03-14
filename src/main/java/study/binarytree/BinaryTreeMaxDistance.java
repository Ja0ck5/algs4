package study.binarytree;

import study.binarytree.TreeNode;

/**
 * 二叉树最大距离
 */
public class BinaryTreeMaxDistance {

    public int getMaxDistance(TreeNode root) {
        return process(root).maxDistance;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }

        Info left = process(root.left);
        Info right = process(root.right);
        int height = Math.max(left.height, right.height) + 1;
        int maxDistance = Math.max(Math.max(left.maxDistance, right.maxDistance), left.height + right.height + 1);
        return new Info(maxDistance, height);
    }

    class Info {
        public int maxDistance;

        public int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }
}

