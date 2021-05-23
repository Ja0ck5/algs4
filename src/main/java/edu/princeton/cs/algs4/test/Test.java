package edu.princeton.cs.algs4.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Q:合并区间，给出一组区间，请合并所有重叠的区间。
 * 例如，
 * 给出[1,3],[8,10],[15,18], [2,6]
 * <p>
 * [1,3] [2, 6] [8, 10]
 * 返回[1,6],[8,10],[15,18].
 */
public class Test {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {8, 10}, {15, 18}, {2, 6}};
        List<List<Integer>> merge = merge(intervals);
        for (List<Integer> sub : merge) {
            System.out.println(Arrays.toString(sub.toArray(new Integer[0])));
        }
    }

    static Comparator<int[]> c = Comparator.comparingInt(o -> o[0]);

    public static List<List<Integer>> merge(int[][] intervals) {

        Arrays.sort(intervals, c);
        List<List<Integer>> res = new ArrayList<>();

        int[] pre = intervals[0];
        int[] next = intervals[1];
        int preEnd = pre[1];
        int nextStart = next[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] ans = new int[2];
            if (preEnd > nextStart) {
                ans[0] = pre[0];
                ans[1] = next[1];
                // next pre is merged
                pre = ans;
                next = intervals[++i];
            } else {
                // m
                List<Integer> sub = new ArrayList<>(2);
                sub.add(ans[0]);
                sub.add(ans[1]);
                res.add(sub);

                pre = intervals[++i];
                next = intervals[++i];
            }
        }

        return res;

    }

    private Comparator<int[]> com = Comparator.comparingInt(o -> o[0]);

    public int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, com);
        List<int[]> res = new ArrayList<>();
        int i = 0;
        while (i < intervals.length) {
            int l = intervals[i][0];
            int r = intervals[i][1];
            while (i < intervals.length - 1 && r >= intervals[i + 1][0]) {
                r = Math.max(r, intervals[++i][1]);
            }
            res.add(new int[]{l, r});
        }
        return res.toArray(new int[0][]);
    }

}
