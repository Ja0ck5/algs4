package study.bd;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Element {
    public int row, col, val;
    Element(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }
}

public class MergeKSortedArray {

    private Comparator<Element> ec = Comparator.comparingInt(e -> e.val);
    
    /**
     * Nlogk
     *
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null) {
            return new int[0];
        }
        
        int total_size = 0;
        // 默认由小到大顺序
        Queue<Element> heap = new PriorityQueue<>(arrays.length, ec);
        
        // 初始化
        // 把每一行的第一个元素加入 PriorityQueue
        // 顺便统计元素总量
        for (int i = 0; i < arrays.length; i++) {
            // 当前行长度不为 0
            if (arrays[i].length > 0) {
                Element elem = new Element(i, 0, arrays[i][0]);
                heap.add(elem);
                total_size += arrays[i].length;
            }
        }
        
        int[] result = new int[total_size];
        int index = 0;
        while (!heap.isEmpty()) {
            Element elem = heap.poll();
            result[index++] = elem.val;
            // 当前结点被 PriorityQueue 抛出来后，当前行的第二个结点加入 PriorityQueue
            if (elem.col + 1 < arrays[elem.row].length) {
                elem.val = arrays[elem.row][++elem.col];
                heap.add(elem);
            }
        }
        
        return result;
    }
}