package study.bd.jzoffer;

/**
 * @author liyanjie
 * @createTime 2021-04-27 17:30
 */
public class HammingWeight {

    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }

    public int hammingWeight2(int n) {
        int res = 0;
        while(n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

}
