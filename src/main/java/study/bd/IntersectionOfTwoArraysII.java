package study.bd;

import java.util.Arrays;

/**
 * 返回
 * 548. 两数组的交集 II
 * 描述
 * 给定两个数组，计算两个数组的交集
 * <p>
 * 每个元素出现次数得和在数组里一样
 * 答案可以以任意顺序给出
 * <p>
 * 您在真实的面试中是否遇到过这个题？
 * 样例
 * 样例1
 * <p>
 * 输入:
 * nums1 = [1, 2, 2, 1], nums2 = [2, 2]
 * 输出:
 * [2, 2]
 * 样例2
 * <p>
 * 输入:
 * nums1 = [1, 1, 2], nums2 = [1]
 * 输出:
 * [1]
 * 挑战
 * -如果给定的数组已经排序了怎么办?如何优化算法?
 * -如果nums1的大小比num2的小怎么办?哪种算法更好?
 * -如果nums2的元素存储在磁盘上，并且内存受到限制，以至于不能一次将所有元素加载到内存中，该怎么办?
 */
public class IntersectionOfTwoArraysII {

    /**
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0, cnt = 0;
        int[] res = new int[nums2.length];
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                res[cnt] = nums2[j];
                i++;
                j++;
                cnt++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return Arrays.copyOfRange(res, 0, cnt);
    }

}
