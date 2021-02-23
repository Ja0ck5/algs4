package study.binarytree.bfs;

import study.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS
 * - 2 Queues
 * - 1 Queue & dummy node
 * - 1 Queue
 */
public class BinaryTreeLevelOrderTraversal {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if(head.left!=null){
                    queue.offer(head.left);
                }
                if(head.right!=null){
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }
        return result;
    }

}
