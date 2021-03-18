package study.heap;


/**
 * 最大最小堆
 * based = 0;
 * parent = (i - 1)/2
 * left = 2 * i + 1
 * right = 2 * i + 2
 * <p>
 * based = 1;
 * parent = (i)/2 ====>[ i >> 1]
 * left = 2 * i ====>[ i << 1]
 * right = 2 * i + 1 ====>[ i << 1 | 1 ]
 */
public class MaxMinHeap {

    int[] heap = new int[Integer.MAX_VALUE];
    int heapSize = 0;

    int limit = Integer.MAX_VALUE;

    public int pop() {
        int res = heap[0];
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return res;
    }

    public void push(int val) {
        if (heapSize == limit) {
            // heap is full
            return;
        }
        heap[heapSize] = val;
        heapInsert(heap, ++heapSize);
    }

    private void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, arr[index], arr[(index - 1) / 2]);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] < arr[left] ? left : left + 1;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = left * 2 + 1;
        }
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

}
