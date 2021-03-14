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

}
