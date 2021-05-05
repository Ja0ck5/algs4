package study.bd.vip;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 * 通过次数247,347提交次数583,214
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int start = 0, end = nums.length - 1;
        int i = -1, j = -1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                i = mid;
                break;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (i > 0) {
            return search(nums, target, i);
        } else if (nums[start] == target) {
            return search(nums, target, start);
        } else if (nums[end] == target) {
            return search(nums, target, end);
        }

        return new int[]{i, j};
    }

    int[] search(int[] nums, int target, int mid) {
        int start = mid, end = mid;
        while (start >= 0 && nums[start] == target) {
            start--;
        }
        while (end < nums.length && nums[end] == target) {
            end++;
        }
        return new int[]{start + 1, end - 1};
    }

}
