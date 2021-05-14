package study.bd;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * https://leetcode-cn.com/problems/multiply-strings/
 *
 * @author liyanjie
 * @createTime 2021-05-14 09:35
 */
public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)){
            return "0";
        }
        int n = num1.length();
        int m = num2.length();
        int[] mul = new int[n + m - 1];
        for(int i=0; i < n; i++){
            for(int j=0;j<m;j++){
                int reti = num1.charAt(i) - '0';
                int retj = num2.charAt(j) - '0';
                mul[i + j] += reti * retj;
            }
        }

        for(int i = n + m - 2; i > 0; i--){
            mul[i - 1] += mul[i]/10;
            mul[i] %= 10;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < mul.length; i++){
            sb.append(mul[i]);
        }
        return sb.toString();
    }

}
