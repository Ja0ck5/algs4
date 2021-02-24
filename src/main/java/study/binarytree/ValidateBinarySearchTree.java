package study.binarytree;

/**
 * 中序遍历一定是升序的
 */
public class ValidateBinarySearchTree {

    private int lastVal = Integer.MIN_VALUE;

    private boolean firstNode = true;

    /**
     * 中序遍历一定是升序的
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (!firstNode && lastVal >= root.val) {
            return false;
        }
        firstNode = false;
        lastVal = root.val;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }

    //TODO Divide & Conquer

}
