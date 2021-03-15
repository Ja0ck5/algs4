package study.binarytree;

import java.util.*;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 * 通过次数71,442提交次数132,033
 */
public class SerializeAndDeserializeBinaryTree {

    private static final String NULL = "NULL";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return reSerial(root, "");
    }

    private String reSerial(TreeNode root, String s) {
        if (root == null) {
            s += NULL + ",";
        } else {
            s += root.val + ",";
            s = reSerial(root.left, s);
            s = reSerial(root.right, s);
        }
        return s;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strArr = data.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(strArr));
        return reDeserial(list);
    }

    private TreeNode reDeserial(List<String> list) {
        if(NULL.equals(list.get(0))){
            list.remove(0);
            return null;
        }
        String s = list.remove(0);
        TreeNode node = new TreeNode(Integer.valueOf(s));
        node.left = reDeserial(list);
        node.right = reDeserial(list);
        return node;
    }


    public Queue<String> preSerial(TreeNode root) {
        Queue<String> res = new LinkedList<>();
        pre(root, res);
        return res;
    }

    private void pre(TreeNode root, Queue<String> res) {
        if (root == null) {
            res.offer(null);
        } else {
            res.offer(String.valueOf(root.val));
            pre(root.left, res);
            pre(root.right, res);
        }
    }

    public TreeNode preDeserial(Queue<String> preList) {
        if (preList == null || preList.isEmpty()) {
            return null;
        }
        return preD(preList);
    }

    private TreeNode preD(Queue<String> preList) {
        String val = preList.poll();
        if (val == null) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = preD(preList);
        node.right = preD(preList);
        return node;
    }

}
// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
