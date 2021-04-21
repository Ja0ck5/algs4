package study.bd;

import java.util.ArrayList;
import java.util.List;

/**
 *120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 *
 * 提示：
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *
 *
 * @author liyanjie
 * @createTime 2021-04-21 12:38
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null){
            return 0;
        }
        if(triangle.size() == 0){
            return triangle.get(0).get(0);
        }
        List<List<Integer>> dp = new ArrayList<>(triangle.size());

        for(int i = 0; i < triangle.size(); i++){
            if(i == 0){
                dp.add(new ArrayList<>(triangle.get(0)));
            }else{
                dp.add(new ArrayList<>(triangle.get(i).size()));
            }
        }

        for(int i = 1; i < triangle.size(); i++){
            List<Integer> sub = triangle.get(i);
            List<Integer> curDp = dp.get(i);
            for(int j = 0; j < sub.size(); j++){
                if(j == 0 || j == sub.size() - 1){
                    curDp.add(dp.get(i - 1).get(j == 0 ? 0 :j - 1) + sub.get(j));
                }else{
                    curDp.add(Math.min(dp.get(i - 1).get(j - 1) + sub.get(j), dp.get(i - 1).get(j) + sub.get(j)));
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(Integer n : dp.get(dp.size() - 1)){
            min = Math.min(min, n);
        }
        return min;
    }

    public int minimumTotal22(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n-1).get(i);
        }
        minimumTotal(triangle, dp, n-2);
        return dp[0];
    }

    private void minimumTotal(List<List<Integer>> triangle, int[] dp, int lv) {
        if (lv < 0) return;
        List<Integer> tmp = triangle.get(lv);
        for (int i = 0; i < tmp.size(); i++) {
            dp[i] = tmp.get(i) + Math.min(dp[i], dp[i+1]);
        }
        minimumTotal(triangle, dp, lv-1);
    }

}
