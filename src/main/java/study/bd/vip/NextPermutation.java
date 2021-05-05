package study.bd.vip;

/**
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 *
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * 通过次数160,396提交次数435,749
 *
 * https://leetcode-cn.com/problems/next-permutation/
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 1. 从后往前找，先找到单调递减被破坏的位置： 7,6,4,5,3,2,1  --> 被破坏的点为数字 4 所在的位置
        while(i >= 0 && nums[i] >= nums[i+1]){
            i--;
        }
        if(i >= 0){
            int j = nums.length - 1;
            // 2. 找到(被破坏的数字 4 的)后半部分大于 4 的最小的那个数的位置(由于后半部分是反过来单调递增来看待的，所以就是从后往前找到最后一个大于 nums[i])
            while(j >= 0 && nums[i] >= nums[j]){
                j--;
            }
            // 3. 找到最小的大于 被破坏的位置 进行交换
            exch(nums,i,j);
        }
        // 4. 将后半部分 反转
        reverse(nums,i+1);
    }

    void exch(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    void reverse(int[] nums, int start){
        int end = nums.length - 1;
        while(start < end)
            exch(nums, start++, end--);
    }

}
