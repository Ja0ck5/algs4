package study.bd;

import java.util.Random;

/**
 * 第k 大，用 heap 解决
 */
public class FindKth {

    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    // --------------------------------------------------------------------------------- //

    public int findKthLargestHeapVersion(int[] nums, int k) {
        int n = nums.length;
        for (int i = n/2; i >= 1; i--) {
            sink(nums, i, n);
        }
        while (n > k) {
            exch(nums, 1, n--);
            sink(nums, 1, n);
        }
        return nums[0];
    }

    private void exch(int[] nums, int i, int j) {
        int swap = nums[i - 1];
        nums[i - 1] = nums[j - 1];
        nums[j - 1] = swap;
    }

    private void sink(int[] nums, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(nums, j + 1, j)) {
                j++;
            }
            if (less(nums, k, j)) {
                break;
            }
            exch(nums, k, j);
            k = j;
        }
    }

    private boolean less(int[] nums, int i, int j) {
        return nums[i - 1] < nums[j - 1];
    }
}
