package study.bd;

import study.binarytree.TreeNode;

/**
 * 二叉树的后继节点
 *
 * 后继节点：中序遍历中当前节点的下一个节点
 */
public class BinaryTreeSuccessorNode {

    public TreeNode getSuccessorNode(TreeNode node){
        if(node == null){
            return null;
        }
        if(node.right != null){
            return getMostLeft(node);
        }else{
            TreeNode parent = node.parent;
            while(parent != null && parent.left != node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public TreeNode getMostLeft(TreeNode node){
        if(node == null){
            return null;
        }
        while(node.left != null){
            node = node.left;
        }
        return node;
    }


    /**
     * 计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
     *
     * 如果指定节点没有对应的“下一个”节点，则返回null。
     *
     * 示例 1:
     *
     * 输入: root = [2,1,3], p = 1
     *
     *   2
     *  / \
     * 1   3
     *
     * 输出: 2
     * 示例 2:
     *
     * 输入: root = [5,3,6,2,4,null,null,1], p = 6
     *
     *       5
     *      / \
     *     3   6
     *    / \
     *   2   4
     *  /
     * 1
     *
     * 输出: null
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/successor-lcci
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        // 当前节点值小于等于目标值，那么当前目标值的后继者必然在右子树
        if (p.val >= root.val) {
            return inorderSuccessor(root.right, p);
        }
        // 否则结果有可能是当前节点，或者在当前节点的左子树中
        // 那么先去左子树找一下试试，找不到的话返回当前节点即是结果
        TreeNode node = inorderSuccessor(root.left, p);
        return node == null ? root : node;
    }

}
