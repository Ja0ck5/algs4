package study.arr;

import java.util.Stack;

/**
 * 面试题 17.10. 主要元素
 * 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：[3,2]
 * 输出：-1
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * <p>
 * 说明：
 * 你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？
 */
public class FindMajorityElement {

//    public int majorityElement(int[] nums) {
//        Stack<Integer> stack = new Stack();
//        for (int i = 0; i < nums.length; i++) {
//            if (stack.isEmpty()) {
//                stack.push(nums[i]);
//            } else if (stack.peek() == nums[i]) {
//                stack.push(nums[i]);
//            } else {
//                stack.pop();
//            }
//        }
//        return stack.pop();
//    }

    public int majorityElement(int[] nums) {
        // 首先找到这个数组中出现最多的元素
        int res = nums[0], cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            if (cnt == 0) {
                res = nums[i];
                cnt++;
            } else if (nums[i] == res) {
                cnt++;
            } else {
                cnt--;
            }
        }

        // 遍历一遍结果就是找到出现次数最多的元素，判断是不是满足题目中大于一半的条件
        int curNo = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == res) {
                curNo++;
            }
        }
        return curNo > (nums.length / 2) ? res : -1;
    }

}
