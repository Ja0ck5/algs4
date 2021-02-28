package study.binarytree;

public class ConvertSortedArrayToBinaryTree {

    private TreeNode buildTree(int[] num, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode node = new TreeNode(num[start + (end - start) / 2]);
        node.left = buildTree(num, start, start + (end - start) / 2 - 1);
        node.right = buildTree(num, start + (end - start) / 2 - 1, end);

        return node;
    }

    public TreeNode convertSortedArrayToBinarySearchTree(int[] num) {
        if (null == num) {
            return null;
        }
        return buildTree(num, 0, num.length - 1);
    }

}
