package study.bd.jzoffer;//一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 示例 1： 
//
// 输入：n = 2
//输出：2
// 
//
// 示例 2： 
//
// 输入：n = 7
//输出：21
// 
//
// 示例 3： 
//
// 输入：n = 0
//输出：1 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/ 
//
// 
// Related Topics 递归 
// 👍 157 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class NumWays {
    public int numWays(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        int a = 0, b = 0, ret = 1;
        for (int i = 1; i < n; i++) {
            a = b;
            b = ret;
            ret = a + b;
        }
        return ret;
    }

    public int jumpFloor(int target) {
        if(target == 0 || target == 1) return target;
        int a = 1, b = 1, res = 0;
        for(int i = 2; i <= target; i++){
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }
}