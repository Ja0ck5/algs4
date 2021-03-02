package study.dynamic;

/**
 * 给定一个数字三角形，找到从顶部到底部的最小路径和。每一步可以移动到下面一行的相邻数字上。
 *
 * 如果你只用额外空间复杂度O(n)的条件下完成可以获得加分，其中n是数字三角形的总行数。
 *
 * 样例
 * 样例 1
 *
 * 输入下列数字三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 输出： 11
 * 解释： 从顶到底部的最小路径和为11 ( 2 + 3 + 5 + 1 = 11)。
 * 样例 2
 *
 * 输入下列数字三角形：
 * [
 *      [2],
 *     [3,2],
 *    [6,5,7],
 *   [4,4,8,1]
 * ]
 * 输出： 12
 * 解释： 从顶到底部的最小路径和为12 ( 2 + 2 + 7 + 1 = 12)。
 *
 */
public class Triangle {

    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
        int row = triangle.length;
        int[][] dp = new int[row][row];

        for(int i =0 ; i < row; i++){
            dp[i] = new int[i+1];
        }

        for(int i = 0;i < row; i++){
            for(int j = 0; j < i+1; j++){
                if(i == 0){
                    dp[0][0] = triangle[0][0];
                }
                else{
                    int a;
                    if(j == 0){
                        a = dp[ i - 1][0] + triangle[i][0];
                    }else if(j == i){
                        a = dp[ i - 1][j - 1] + triangle[i][j];
                    }else{
                        a = Math.min(dp[i-1][j-1]+ triangle[i][j], dp[i-1][j]+ triangle[i][j]);
                    }
                    dp[i][j] = a;
                }
            }
        }

        int min = dp[row - 1][0];
        for(int i=1; i<dp[row - 1].length;i++){
            min = Math.min(min, dp[row-1][i]);
        }

        return min;
    }

}
