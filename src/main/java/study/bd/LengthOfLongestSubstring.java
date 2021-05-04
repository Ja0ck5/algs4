package study.bd;

import java.util.HashSet;
import java.util.Set;

/**
 * 384. 最长无重复字符的子串 中文English 给定一个字符串，请找出其中无重复字符的最长子字符串。
 *
 * 样例 样例 1:
 *
 * 输入: "abcabcbb" 输出: 3 解释: 最长子串是 "abc". 样例 2:
 *
 * 输入: "bbbbb" 输出: 1 解释: 最长子串是 "b". 挑战 O(n) 时间复杂度
 *
 * @author liyanjie
 * @createTime 2021-03-23 16:04
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int j = -1, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                set.remove(s.charAt(i - 1));
            }

            while (j + 1 < s.length() && !set.contains(s.charAt(j + 1))) {
                set.add(s.charAt(j + 1));
                ++j;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

}
