package study.bd;

import java.util.Arrays;

/**
 * 60. 排列序列 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123" "132" "213" "231" "312" "321" 给定 n 和 k，返回第 k 个排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3, k = 3 输出："213" 示例 2：
 *
 * 输入：n = 4, k = 9 输出："2314" 示例 3：
 *
 * 输入：n = 3, k = 1 输出："123"
 *
 *
 * 提示：
 *
 * 1 <= n <= 9 1 <= k <= n!
 *
 *
 * 方法一：数学 + 缩小问题规模
 * 思路
 *
 * 要想解决本题，首先需要了解一个简单的结论：
 *
 * 对于 nn 个不同的元素（例如数 1, 2, \cdots, n1,2,⋯,n），它们可以组成的排列总数目为 n!n!。
 *
 * 对于给定的 nn 和 kk，我们不妨从左往右确定第 kk 个排列中的每一个位置上的元素到底是什么。
 *
 * 我们首先确定排列中的首个元素 a_1a
 * 1
 * ​
 *  。根据上述的结论，我们可以知道：
 *
 * 以 11 为 a_1a
 * 1
 * ​
 *   的排列一共有 (n-1)!(n−1)! 个；
 * 以 22 为 a_1a
 * 1
 * ​
 *   的排列一共有 (n-1)!(n−1)! 个；
 * \cdots⋯
 * 以 nn 为 a_1a
 * 1
 * ​
 *   的排列一共有 (n-1)!(n−1)! 个。
 * 由于我们需要求出从小到大的第 kk 个排列，因此：
 *
 * 如果 k \leq (n-1)!k≤(n−1)!，我们就可以确定排列的首个元素为 11；
 * 如果 (n-1)! < k \leq 2 \cdot (n-1)!(n−1)!<k≤2⋅(n−1)!，我们就可以确定排列的首个元素为 22；
 * \cdots⋯
 * 如果 (n-1) \cdot (n-1)! < k \leq n \cdot (n-1)!(n−1)⋅(n−1)!<k≤n⋅(n−1)!，我们就可以确定排列的首个元素为 nn。
 * 因此，第 kk 个排列的首个元素就是：
 *
 * a_1 = \lfloor \frac{k-1}{(n-1)!} \rfloor + 1
 * a
 * 1
 * ​
 *  =⌊
 * (n−1)!
 * k−1
 * ​
 *  ⌋+1
 *
 * 其中 \lfloor x \rfloor⌊x⌋ 表示将 xx 向下取整。
 *
 * 当我们确定了 a_1a
 * 1
 * ​
 *   后，如何使用相似的思路，确定下一个元素 a_2a
 * 2
 * ​
 *   呢？实际上，我们考虑以 a_1a
 * 1
 * ​
 *   为首个元素的所有排列：
 *
 * 以 [1,n] \backslash a_1[1,n]\a
 * 1
 * ​
 *   最小的元素为 a_2a
 * 2
 * ​
 *   的排列一共有 (n-2)!(n−2)! 个；
 * 以 [1,n] \backslash a_1[1,n]\a
 * 1
 * ​
 *   次小的元素为 a_2a
 * 2
 * ​
 *   的排列一共有 (n-2)!(n−2)! 个；
 * \cdots⋯
 * 以 [1,n] \backslash a_1[1,n]\a
 * 1
 * ​
 *   最大的元素为 a_2a
 * 2
 * ​
 *   的排列一共有 (n-2)!(n−2)! 个；
 * 其中 [1,n] \backslash a_1[1,n]\a
 * 1
 * ​
 *   表示包含 1, 2, \cdots n1,2,⋯n 中除去 a_1a
 * 1
 * ​
 *   以外元素的集合。这些排列从编号 (a_1-1) \cdot (n-1)!(a
 * 1
 * ​
 *  −1)⋅(n−1)! 开始，到 a_1 \cdot (n-1)!a
 * 1
 * ​
 *  ⋅(n−1)! 结束，总计 (n-1)!(n−1)! 个，因此第 kk 个排列实际上就对应着这其中的第
 *
 * k' = (k-1) \bmod (n-1)! + 1
 * k
 * ′
 *  =(k−1)mod(n−1)!+1
 *
 * 个排列。这样一来，我们就把原问题转化成了一个完全相同但规模减少 11 的子问题：
 *
 * 求 [1, n] \backslash a_1[1,n]\a
 * 1
 * ​
 *   这 n-1n−1 个元素组成的排列中，第 k'k
 * ′
 *   小的排列。
 *
 * 算法
 *
 * 设第 kk 个排列为 a_1, a_2, \cdots, a_na
 * 1
 * ​
 *  ,a
 * 2
 * ​
 *  ,⋯,a
 * n
 * ​
 *  ，我们从左往右地确定每一个元素 a_ia
 * i
 * ​
 *  。我们用数组 \textit{valid}valid 记录每一个元素是否被使用过。
 *
 * 我们从小到大枚举 ii：
 *
 * 我们已经使用过了 i-1i−1 个元素，剩余 n-i+1n−i+1 个元素未使用过，每一个元素作为 a_ia
 * i
 * ​
 *   都对应着 (n-i)!(n−i)! 个排列，总计 (n-i+1)!(n−i+1)! 个排列；
 *
 * 因此在第 kk 个排列中，a_ia
 * i
 * ​
 *   即为剩余未使用过的元素中第 \lfloor \frac{k-1}{(n-i)!} \rfloor + 1⌊
 * (n−i)!
 * k−1
 * ​
 *  ⌋+1 小的元素；
 *
 * 在确定了 a_ia
 * i
 * ​
 *   后，这 n-i+1n−i+1 个元素的第 kk 个排列，就等于 a_ia
 * i
 * ​
 *   之后跟着剩余 n-in−i 个元素的第 (k-1) \bmod (n-i)! + 1(k−1)mod(n−i)!+1 个排列。
 *
 * 在实际的代码中，我们可以首先将 kk 的值减少 11，这样可以减少运算，降低代码出错的概率。对应上述的后两步，即为：
 *
 * 因此在第 kk 个排列中，a_ia
 * i
 * ​
 *   即为剩余未使用过的元素中第 \lfloor \frac{k}{(n-i)!} \rfloor + 1⌊
 * (n−i)!
 * k
 * ​
 *  ⌋+1 小的元素；
 *
 * 在确定了 a_ia
 * i
 * ​
 *   后，这 n-i+1n−i+1 个元素的第 kk 个排列，就等于 a_ia
 * i
 * ​
 *   之后跟着剩余 n-in−i 个元素的第 k \bmod (n-i)!kmod(n−i)! 个排列。
 *
 * 实际上，这相当于我们将所有的排列从 00 开始进行编号。
 *
 *
 *
 * @author liyanjie
 * @createTime 2021-03-29 16:52
 */
public class PermutationSequence {


    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }

        --k;
        StringBuffer ans = new StringBuffer();
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        for (int i = 1; i <= n; ++i) {
            int order = k / factorial[n - i] + 1;
            for (int j = 1; j <= n; ++j) {
                order -= valid[j];
                if (order == 0) {
                    ans.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k %= factorial[n - i];
        }
        return ans.toString();
    }




    /**
     * 记录数字是否使用过
     */
    private boolean[] used;

    /**
     * 阶乘数组
     */
    private int[] factorial;

    private int n;
    private int k;

    public String getPermutationDFS(int n, int k) {
        this.n = n;
        this.k = k;
        calculateFactorial(n);

        // 查找全排列需要的布尔数组
        used = new boolean[n + 1];
        Arrays.fill(used, false);

        StringBuilder path = new StringBuilder();
        dfs(0, path);
        return path.toString();
    }


    /**
     * @param index 在这一步之前已经选择了几个数字，其值恰好等于这一步需要确定的下标位置
     * @param path
     */
    private void dfs(int index, StringBuilder path) {
        if (index == n) {
            return;
        }

        // 计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1
        int cnt = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            path.append(i);
            used[i] = true;
            dfs(index + 1, path);
            // 注意 1：不可以回溯（重置变量），算法设计是「一下子来到叶子结点」，没有回头的过程
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    /**
     * 计算阶乘数组
     *
     * @param n
     */
    private void calculateFactorial(int n) {
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

}
