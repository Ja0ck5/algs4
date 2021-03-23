package study.bd;

/**
 *
 * //给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * //
 * // 示例 1:
 * //
 * // 输入: num1 = "2", num2 = "3"
 * //输出: "6"
 * //
 * // 示例 2:
 * //
 * // 输入: num1 = "123", num2 = "456"
 * //输出: "56088"
 * //
 * // 说明：
 * //
 * //
 * // num1 和 num2 的长度小于110。
 * // num1 和 num2 只包含数字 0-9。
 * // num1 和 num2 均不以零开头，除非是数字 0 本身。
 * // 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * //
 * // Related Topics 数学 字符串
 * // 👍 588 👎 0
 * @author liyanjie
 * @createTime 2021-03-23 18:11
 */
public class StringMultiply {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        int[] mul = new int[m + n - 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int vi = num1.charAt(i) - 48;
                int vj = num2.charAt(j) - 48;
                mul[i + j] += vi * vj;
            }
        }
        for (int i = m + n - 2; i > 0; --i) {
            mul[i - 1] += mul[i] / 10;
            mul[i] %= 10;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m + n - 1; ++i) {
            result.append(mul[i]);
        }
        return result.toString();
    }

}
