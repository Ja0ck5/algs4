package study.bd.jzoffer;

/**
 * //给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
 * //请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
 * //。
 * //
 * // 示例 1：
 * //
 * // 输入: 2
 * //输出: 1
 * //解释: 2 = 1 + 1, 1 × 1 = 1
 * //
 * // 示例 2:
 * //
 * // 输入: 10
 * //输出: 36
 * //解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * //
 * // 提示：
 * //
 * //
 * // 2 <= n <= 58
 * //
 * //
 * // 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
 * // Related Topics 数学 动态规划
 * // 👍 218 👎 0
 * <p>
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * <p>
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class CuttingRope {

    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                // 1. 将 i 拆分成 j 和 i-j 的和，且 i-j 不再拆分成多个正整数，此时的乘积是 j * (i-j)
                // 2. 将 i 拆分成 j 和 i-j 的和，且 i-j拆分成多个正整数，此時乘积 j * dp[i-j]
                max = Math.max(max, Math.max(i * (i - j), i * dp[i - j]));
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public int cuttingRope2(int n) {
        if(n < 4){
            return n - 1;
        }
        long res = 1;
        while(n > 4){
            //  尽可能将绳子以长度 33 等分为多段时，乘积最大
            res  = res * 3 % 1000000007;
            n -= 3;
        }
        return (int) (res * n % 1000000007);
    }

}
