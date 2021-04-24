package study.bd.jzoffer;

import study.binarytree.TreeNode;

import java.util.HashMap;

/**
 * //输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * //
 * //
 * //
 * // 例如，给出
 * //
 * // 前序遍历 preorder = [3,9,20,15,7]
 * //中序遍历 inorder = [9,3,15,20,7]
 * //
 * // 返回如下的二叉树：
 * //
 * //     3
 * //   / \
 * //  9  20
 * //    /  \
 * //   15   7
 * //
 * //
 * //
 * // 限制：
 * //
 * // 0 <= 节点个数 <= 5000
 * //
 * //
 * //
 * // 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
 * //preorder-and-inorder-traversal/
 * // Related Topics 树 递归
 * // 👍 426 👎 0
 */
public class RebuildBinaryTree {

    int[] preorder;
    HashMap<Integer, Integer> dic = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++)
            dic.put(inorder[i], i);
        return recur(0, 0, inorder.length - 1);
    }

    TreeNode recur(int preRootIdx, int inLeftIdx, int inRightIdx) {
        if (inLeftIdx > inRightIdx) return null;
        TreeNode node = new TreeNode(preorder[preRootIdx]);          // 建立根节点
        int inorderRootIdx = dic.get(preorder[preRootIdx]);// 划分根节点、左子树、右子树
        // 左子树的根节点就是 左子树的(前序遍历）第一个，就是+1([preRootIdx|leftIdx|rightIdx])
        // 左边边界就是left，右边边界是(中序遍历)中间区分的i-1
        node.left = recur(preRootIdx + 1, inLeftIdx, inorderRootIdx - 1);
        //右子树的根，就是右子树（前序遍历）的第一个,就是当前根节点 加上左子树的数量
        // preRootIdx 当前的根,  左子树的长度 = 左子树的左边-右边 (inorderRootIdx-1 - inLeftIdx +1) 。最后+1就是右子树的根了
        /*[preRoot|left|right],*/
        node.right = recur(preRootIdx + (inorderRootIdx - inLeftIdx) + 1, inorderRootIdx + 1, inRightIdx);
        return node;                                           // 回溯返回根节点
    }

}
