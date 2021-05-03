package study.bd;

public class FindKthUseQSort {

    int tk, tn;
    public int findKth(int[] a, int n, int K) {
        // write code here
        this.tk = K;
        this.tn = n;
        sort(a, 0, n - 1);
        return a[n - K];
    }
    void sort(int[] a, int lo, int hi){
        if(hi <= lo) return;
        int p = partition(a, lo, hi);
        if(tn - tk == p) return;
        sort(a, lo, p - 1);
        sort(a, p + 1, hi);
    }

    int partition(int[] a, int lo, int hi){
        int v = a[lo];
        int i = lo;
        int j = hi + 1;
        while(true){
            while(a[++i] < v)
                if(i == hi) break;
            while(a[--j] > v)
                if(j == lo) break;
            if(i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    void exch(int[]a , int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
