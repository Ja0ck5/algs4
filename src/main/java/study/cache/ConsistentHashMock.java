package study.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ConsistentHashMock {

    /**
     * 假设我们一共初始化有8个节点(可以是ip, 就理解为ip吧); 把 1024个虚拟节点跟 8个资源节点相对应
     */
    public static Map<Integer, String> realNodeMap = new HashMap<>();
    public static int V_NODES = 1024; // 假设我们的环上有1024个虚拟节点
    static TreeMap<Integer, String> virtualNodeMap = new TreeMap<>();
    private static final Integer REAL_NODE_COUNT = 8;

    static {

        for (int i = 0; i < 8; i++) {
            realNodeMap.put(i, "node_" + i);
        }
        for (Integer i = 0; i < V_NODES; i++) {
            // 每个虚拟节点跟其取模的余数的 nodeMap 中的key相对应;
            // 下面删除虚拟节点的时候, 就可以根据取模规则来删除 TreeMap中的节点了;
            virtualNodeMap.put(i, realNodeMap.get(i % REAL_NODE_COUNT));
        }
    }


    /**
     * 输入一个id
     */
    public static String getRealServerNode(String value) {
        // 1. 传递来一个字符串, 得到它的hash值
        Integer vnode = value.hashCode() % V_NODES;
        // 2.找到对应节点最近的key的节点值
        return virtualNodeMap.ceilingEntry(vnode).getValue();
    }

    /**
     * 模拟删掉一个物理可用资源节点, 其他资源可以返回其他节点
     */
    public static void dropBadNode(String nodeName) {
        int nodek = -1;
        // 1. 遍历 nodeMap 找到故障节点 nodeName对应的key;
        for (Map.Entry<Integer, String> entry : realNodeMap.entrySet()) {
            if (nodeName.equalsIgnoreCase(entry.getValue())) {
                nodek = entry.getKey();
                break;
            }
        }
        if (nodek == -1) {
            System.err.println(nodeName + "在真实资源节点中无法找到, 放弃删除虚拟节点!");
            return;
        }

        // 2. 根据故障节点的 key, 对应删除所有 chMap中的虚拟节点
        Iterator<Entry<Integer, String>> iter = virtualNodeMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, String> entry = iter.next();
            int key = entry.getKey();
            String value = entry.getValue();
            if (key % REAL_NODE_COUNT == nodek) {
                System.out.println("删除节点虚拟节点: [" + key + " = " + value + "]");
                iter.remove();
            }
        }
    }

}