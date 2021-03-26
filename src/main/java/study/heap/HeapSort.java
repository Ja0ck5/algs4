package study.heap;

import java.util.Arrays;

/**
 * 最大最小堆 based = 0; parent = (i - 1)/2 left = 2 * i + 1 right = 2 * i + 2
 * <p>
 * based = 1; parent = (i)/2 ====>[ i >> 1] left = 2 * i ====>[ i << 1] right = 2 * i + 1 ====>[ i << 1 | 1 ]
 */
public class HeapSort {

    public void sort(int[] pq) {
        int n = pq.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }
        while (n > 1) {
            exch(pq, 1, n--);
            sink(pq, 1, n);
        }
    }

    private void exch(int[] pq, int i, int j) {
        int swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    private void sink(int[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            // left right 比较, right 更大，则 j++(right)
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }
            // 当前 k 比 j 大
            if (!less(pq, k, j)) {
                break;
            }
            // 交换并下沉
            exch(pq, k, j);
            k = j;
        }
    }

    private boolean less(int[] pq, int i, int j) {
        return pq[i - 1] < pq[j - 1];
    }

    public static void main(String[] args) {
        int[] a = {10, 5, 7, 9, 3, 1, 4, 4};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(a);
        System.out.println(Arrays.toString(a));
    }

}
