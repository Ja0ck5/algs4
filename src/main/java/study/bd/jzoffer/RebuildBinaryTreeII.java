package study.bd.jzoffer;

import java.util.HashMap;
import java.util.Map;
import study.binarytree.TreeNode;

/**
 * @author liyanjie
 * @createTime 2021-05-07 15:18
 */
public class RebuildBinaryTreeII {

    //根据一棵树的中序遍历与后序遍历构造二叉树。
//
// 注意:
//你可以假设树中没有重复的元素。
//
// 例如，给出
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3]
//
// 返回如下的二叉树：
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// Related Topics 树 深度优先搜索 数组
// 👍 496 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    int[] postorder;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recur(0, postorder.length - 1, 0, inorder.length - 1);
    }

    TreeNode recur(int poseStartIdx, int postRootIdx, int inLeftIdx, int inRightIdx) {
        if (inLeftIdx > inRightIdx || postRootIdx < poseStartIdx) {
            return null;
        }
        int root = postorder[postRootIdx];
        int inRootIdx = map.get(root);
        TreeNode node = new TreeNode(root);
        node.left = recur(poseStartIdx, poseStartIdx + (inRootIdx - inLeftIdx) - 1, inLeftIdx, inRootIdx - 1);
        node.right = recur(poseStartIdx + (inRootIdx - inLeftIdx), postRootIdx - 1, inRootIdx + 1, inRightIdx);
        return node;
    }

//leetcode submit region end(Prohibit modification and deletion)


}
