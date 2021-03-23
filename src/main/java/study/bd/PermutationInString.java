package study.bd;

import java.util.Arrays;

/**
 * 567. 字符串的排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 *
 * 提示：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 * @author liyanjie
 * @createTime 2021-03-23 17:31
 */
public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(); int m = s2.length();
        if(n > m){
            return false;
        }
        int[] A = new int[26];
        int[] B = new int[26];
        for (int i = 0; i < n; i++) {
            ++A[s1.charAt(i) - 'a'];
            ++B[s2.charAt(i) - 'a'];
        }

        if(Arrays.equals(A, B)){
            return true;
        }

        for (int i = n; i < m; ++i) {
            ++B[s2.charAt(i) - 'a'];
            --B[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(A, B)) {
                return true;
            }
        }
        return false;
    }

}
