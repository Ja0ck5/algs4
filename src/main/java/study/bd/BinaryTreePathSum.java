package study.bd;

import study.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 376. 二叉树的路径和
 * 中文English
 * 给定一个二叉树，找出所有路径中各节点相加总和等于给定 目标值 的路径。
 * <p>
 * 一个有效的路径，指的是从根节点到叶节点的路径。
 * <p>
 * 样例
 * 样例1:
 * <p>
 * 输入:
 * {1,2,4,2,3}
 * 5
 * 输出: [[1, 2, 2],[1, 4]]
 * 说明:
 * 这棵树如下图所示：
 * 1
 * / \
 * 2   4
 * / \
 * 2   3
 * 对于目标总和为5，很显然1 + 2 + 2 = 1 + 4 = 5
 * 样例2:
 * <p>
 * 输入:
 * {1,2,4,2,3}
 * 3
 * 输出: []
 * 说明:
 * 这棵树如下图所示：
 * 1
 * / \
 * 2   4
 * / \
 * 2   3
 * 注意到题目要求我们寻找从根节点到叶子节点的路径。
 * 1 + 2 + 2 = 5, 1 + 2 + 3 = 6, 1 + 4 = 5
 * 这里没有合法的路径满足和等于3.
 */
public class BinaryTreePathSum {

    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(root, target, path, res);
        return res;
    }

    private void dfs(TreeNode root, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (target == root.val) {
                res.add(new ArrayList<>(path));
            }
            path.removeLast();
            return;
        }

        dfs(root.left, target - root.val, path, res);
        dfs(root.right, target - root.val, path, res);
        path.removeLast();
    }

}
