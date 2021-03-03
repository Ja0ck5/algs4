package study.string;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class IsAnagram {


    /**
     * @param s: The first string
     * @param t: The second string
     * @return: true or false
     */
    public boolean anagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] tIdx = new int[t.length()];
        int[] sIdx = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            sIdx[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            tIdx[t.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (sIdx[i] != tIdx[i]) {
                return false;
            }
        }

        return true;
    }

}
