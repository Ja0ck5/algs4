package study.bd.jzoffer;

/**
 * //请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * //
 * //
 * //
 * // 示例 1：
 * //
 * // 输入：s = "We are happy."
 * //输出："We%20are%20happy."
 * //
 * //
 * //
 * // 限制：
 * //
 * // 0 <= s 的长度 <= 10000
 * // 👍 102 👎 0
 */
public class ReplaceSpace {

    public String replaceSpace(String s) {
        int len = s.length();
        char[] c = new char[len * 3];
        int cap = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ' ') {
                c[cap++] = '%';
                c[cap++] = '2';
                c[cap++] = '0';
            } else {
                c[cap++] = s.charAt(i);
            }
        }
        return new String(c, 0, cap);
    }

}
