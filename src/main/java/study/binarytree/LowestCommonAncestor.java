package study.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 最近公共祖先
 */
public class LowestCommonAncestor {

    // Traditional version
    public List<TreeNode> getPath2Root(TreeNode node) {
        List<TreeNode> list = new ArrayList<>();
        while (node != null) {
            list.add(node);
            node = node.parent;
        }
        return list;
    }


    public TreeNode lowestCommonAncestor(TreeNode node1, TreeNode node2) {
        List<TreeNode> list1 = getPath2Root(node1);
        List<TreeNode> list2 = getPath2Root(node2);

        // 拿到list 之后倒过来对比，e.g.: list1=321, list2=6541; 则 LCA=1
        int i, j;
        for (i = list1.size() - 1, j = list2.size() - 1; i >= 0 && j >= 0; i--, j--) {
            if (list1.get(i) != list2.get(j)) {
                return list1.get(i).parent;
            }
        }

        return list1.get(i + 1);
    }


    // Divide & conquer version
    public TreeNode getRoot(TreeNode node) {
        while (node.parent != null) {
            node = node.parent;
        }
        return node;
    }

    public TreeNode getAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || root == node1 || root == node2) {
            return root;
        }

        // Divide
        TreeNode left = getAncestor(root.left, node1, node2);
        TreeNode right = getAncestor(root.right, node1, node2);

        // Conquer
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    public TreeNode lowestCommonAncestorVersion2(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return null;
        }
        TreeNode root = getRoot(node1);
        return getAncestor(root, node1, node2);
    }


}
