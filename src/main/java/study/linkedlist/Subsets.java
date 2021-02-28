package study.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;

public class Subsets {

    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return results;
        }

        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        subsetsHelper(results, list, nums, 0);
        return results;
    }

    private void subsetsHelper(ArrayList<ArrayList<Integer>> results, ArrayList<Integer> list, int[] nums, int pos) {

        results.add(new ArrayList<>(list));

        for (int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            subsetsHelper(results, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

}
