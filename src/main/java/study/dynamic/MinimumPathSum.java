package study.dynamic;

/**
 * 描述
 * 给定一个只含非负整数的m*n网格，找到一条从左上角到右下角的可以使数字和最小的路径。
 * <p>
 * <p>
 * <p>
 * 你在同一时间只能向下或者向右移动一步
 * <p>
 * 您在真实的面试中是否遇到过这个题？
 * 样例
 * 样例 1:
 * <p>
 * 输入:  [[1,3,1],[1,5,1],[4,2,1]]
 * 输出: 7
 * 样例解释：
 * 路线为： 1 -> 3 -> 1 -> 1 -> 1。
 * 样例 2:
 * <p>
 * 输入:  [[1,3,2]]
 * 输出:  6
 * 解释:
 * 路线是： 1 -> 3 -> 2
 */
public class MinimumPathSum {

    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[][] dp = new int[r][c];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < r; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < c; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[r - 1][c - 1];
    }

}
