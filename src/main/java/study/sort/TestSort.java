package study.sort;

import java.util.Arrays;

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


    public static void main(String[] args) {
        TestSort ts = new TestSort();
        int[] a = {4,3,5,7,9,8,2};
        ts.mergeSort(a);
        System.out.println(Arrays.toString(a));
    }

    public void mergeSort(int[] nums) {

        int[] aux = new int[nums.length];
        sort(nums, aux, 0, nums.length - 1);

    }

    private void sort(int[] nums, int[] aux, int lo, int hi) {
        if(hi <= lo) return;
        int mid = (hi - lo)/2 + lo;
        sort(nums,aux,lo,mid);
        sort(nums,aux,mid+1,hi);
        merge(nums,aux,lo, mid, hi);
    }

    private void merge(int[] nums, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = nums[i];
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if(i > mid) nums[k] = aux[j++];
            else if(j > hi) nums[k] = aux[i++];
            else if(nums[j] < nums[i]) nums[k] = aux[j++];
            else nums[k] = aux[i++];
        }

    }


}
