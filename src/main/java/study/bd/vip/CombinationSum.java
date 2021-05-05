package study.bd.vip;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * <p>
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> com = new ArrayList<>();
        dfs(candidates, target, res, com, 0);
        return res;
    }

    void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> com, int i) {
        if (i == candidates.length) return;
        if (target == 0) {
            res.add(new ArrayList<>(com));
            return;
        }

        dfs(candidates, target, res, com, i + 1);

        if (target - candidates[i] >= 0) {
            com.add(candidates[i]);
            dfs(candidates, target - candidates[i], res, com, i);
            com.remove(com.size() - 1);
        }
    }
}