package study.sort;

public class TestSort {

    // Quick sort

    public void quickSort(int[] nums){
        int lo = 0, hi = nums.length - 1;
        quickSort(nums, lo, hi);
    }

    private void quickSort(int[] nums, int lo, int hi) {
        int p = partition(nums, lo , hi);
        quickSort(nums, lo, p - 1);
        quickSort(nums, p + 1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo;
        int j = hi + 1;
        while(true){
            while(nums[++i] < v)
                if(i == hi) break;
            while(nums[--j] > v)
                if(j == lo) break;

            if(i >= j) break;

            exch(nums, i, j);
        }
        exch(nums, lo, j);
        return j;
    }

    private void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public void HeapSort(int[] nums){
        int n = nums.length;
        for(int k = n/2; k >= 1; k--)
            sink(nums, k, n);

        while(n > 1){
            exch2(nums, 1, n--);
            sink(nums, 1, n);
        }
    }

    private void sink(int[] nums, int k, int n) {
        while(2*k <= n){
            int j = 2*k;
            if(j < n && nums[j] < nums[j+1])
                j++;
            if(nums[k] > nums[j])
                break;

            exch2(nums, k, j);
            k = j;
        }

    }

    private void exch2(int[] nums, int i, int j) {
        int tmp = nums[i-1];
        nums[i-1] = nums[j-1];
        nums[j-1] = tmp;
    }



}
