package study.bd.vip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。 解集不能包含重复的组合。 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8, 所求解集为: [ [1, 7], [1, 2, 5], [2, 6], [1, 1, 6] ] 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5, 所求解集为: [ [1,2,2], [5] ] 通过次数154,738提交次数243,067 https://leetcode-cn.com/problems/combination-sum-ii
 */
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> com = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, res, com, 0);
        return res;
    }

    void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> com, int idx) {

        if (target == 0) {
            res.add(new ArrayList<>(com));
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) {
                if (i > idx && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                com.add(candidates[i]);
                dfs(candidates, target - candidates[i], res, com, i + 1);
                com.remove(com.size() - 1);
            }
        }
    }
}