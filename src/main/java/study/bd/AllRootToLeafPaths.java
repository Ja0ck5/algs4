package study.bd;

import study.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回
 * 480. 二叉树的所有路径
 * 描述
 * 给一棵二叉树，找出从根节点到叶子节点的所有路径。
 *
 * 您在真实的面试中是否遇到过这个题？
 * 样例
 * 样例 1:
 *
 * 输入：{1,2,3,#,5}
 * 输出：["1->2->5","1->3"]
 * 解释：
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * 样例 2:
 *
 * 输入：{1,2}
 * 输出：["1->2"]
 * 解释：
 *    1
 *  /
 * 2
 *
 */
public class AllRootToLeafPaths {

    /**
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if(root == null){
            return paths;
        }

        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);

        for (String path : leftPaths) {
            paths.add(root.val + "->" + path);
        }

        for (String path : rightPaths) {
            paths.add(root.val + "->" + path);
        }

        if(paths.size() == 0){
            paths.add("" + root.val);
        }

        return paths;
    }

}
