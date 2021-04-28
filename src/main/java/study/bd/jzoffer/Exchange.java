package study.bd.jzoffer;

/**
 * //输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * //
 * //
 * //
 * // 示例：
 * //
 * //
 * //输入：nums = [1,2,3,4]
 * //输出：[1,3,2,4]
 * //注：[3,1,2,4] 也是正确的答案之一。
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // 0 <= nums.length <= 50000
 * // 1 <= nums[i] <= 10000
 * //
 * // 👍 121 👎 0
 * @author liyanjie
 * @createTime 2021-04-28 16:42
 */
public class Exchange {

    /**
     * 遇到 分成两边，并保持一定规律性的，考虑双指针
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        if(nums == null || nums.length == 0) return nums;
        int left = 0, right = nums.length - 1;
        while(left < right){
            if((nums[left] & 1) == 1){
                left++;
                continue;
            }
            if((nums[right] & 1) == 0){
                right--;
                continue;
            }
            exch(nums, left++, right--);
        }
        return nums;
    }

    private void exch(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
