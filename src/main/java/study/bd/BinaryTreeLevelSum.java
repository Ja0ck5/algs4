package study.bd;

import edu.princeton.cs.algs4.In;
import study.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 返回
 * 482. 二叉树的某层节点之和
 * 描述
 * 给出一棵二叉树和一个整数代表树的某层深度。
 * 计算二叉树的某层节点之和。
 * <p>
 * 您在真实的面试中是否遇到过这个题？
 * 样例
 * 样例 1:
 * <p>
 * 输入：{1,2,3,4,5,6,7,#,#,8,#,#,#,#,9},2
 * 输出：5
 * 解释：
 * 1
 * /   \
 * 2     3
 * / \   / \
 * 4   5 6   7
 * /       \
 * 8         9
 * 2+3=5
 * 样例 2:
 * <p>
 * 输入：{1,2,3,4,5,6,7,#,#,8,#,#,#,#,9},3
 * 输出：22
 * 解释：
 * 1
 * /   \
 * 2     3
 * / \   / \
 * 4   5 6   7
 * /       \
 * 8         9
 * 4+5+6+7=22
 */
public class BinaryTreeLevelSum {

    /**
     * @param root:  the root of the binary tree
     * @param level: the depth of the target level
     * @return: An integer
     */
    public int levelSum(TreeNode root, int level) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                if (head == null) {
                    continue;
                }
                levelList.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            res.add(levelList);
        }

        int sum = 0;
        for (int i = 0; i < res.size(); i++) {
            if(i+1 == level){
                List<Integer> levelList = res.get(i);
                for (Integer val : levelList) {
                    sum+=val;
                }
            }
        }
        return sum;
    }




}
