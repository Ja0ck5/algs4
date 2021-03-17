package study.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证完全二叉树
 */
public class CompleteTree {

    public boolean isCompleteTree(TreeNode root) {
        List<ANode> nodes = new ArrayList();
        nodes.add(new ANode(root, 1));
        int i = 0;
        while (i < nodes.size()) {
            ANode anode = nodes.get(i++);
            if (anode.node != null) {
                nodes.add(new ANode(anode.node.left, anode.code * 2));
                nodes.add(new ANode(anode.node.right, anode.code * 2 + 1));
            }
        }

        return nodes.get(i - 1).code == nodes.size();
    }

}

class ANode {  // Annotated Node
    TreeNode node;
    int code;

    ANode(TreeNode node, int code) {
        this.node = node;
        this.code = code;
    }
}
