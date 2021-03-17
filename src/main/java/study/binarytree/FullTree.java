package study.binarytree;

/**
 * 验证满二叉树
 * 中文English
 * 如果一棵二叉树所有节点都有零个或两个子节点, 那么这棵树为满二叉树. 反过来说, 满二叉树中不存在只有一个子节点的节点. 更多关于满二叉树的信息可以在这里找到
 *
 * 满二叉树
 *       1
 *      / \
 *     2   3
 *    / \
 *   4   5
 *
 * 不是一棵满二叉树
 *       1
 *      / \
 *     2   3
 *    /
 *   4
 * 样例
 * 样例1
 *
 * 输入: {1,2,3}
 * 输出: true
 * 说明：
 *       1
 *      / \
 *     2   3
 * 是一棵满二叉树
 * 样例2
 *
 * 输入: {1,2,3,4}
 * 输出: false
 * 说明：
 *       1
 *      / \
 *     2   3
 *    /
 *   4
 * 不是一棵满二叉树
 */
public class FullTree {

    public boolean isFullTree(TreeNode root) {
        if(root == null)
            return true;

        if(root.left == null && root.right == null){
            return true;
        }else if(root.left == null){
            return false;
        }else if(root.right == null){
            return false;
        }

        return isFullTree(root.left)&&isFullTree(root.right);
    }


}
