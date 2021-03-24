package study.bd;

import edu.princeton.cs.algs4.Stack;

/**
 * //给定一个字符串，逐个翻转字符串中的每个单词。
 * //
 * // 说明：
 * //
 * //
 * // 无空格字符构成一个 单词 。
 * // 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * // 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * //
 * //
 * //
 * //
 * // 示例 1：
 * //
 * // 输入："the sky is blue"
 * //输出："blue is sky the"
 * //
 * //
 * // 示例 2：
 * //
 * // 输入："  hello world!  "
 * //输出："world! hello"
 * //解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * //
 * //
 * // 示例 3：
 * //
 * // 输入："a good   example"
 * //输出："example good a"
 * //解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * //
 * //
 * // 示例 4：
 * //
 * // 输入：s = "  Bob    Loves  Alice   "
 * //输出："Alice Loves Bob"
 * //
 * //
 * // 示例 5：
 * //
 * // 输入：s = "Alice does not even like bob"
 * //输出："bob like even not does Alice"
 * //
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // 1 <= s.length <= 104
 * // s 包含英文大小写字母、数字和空格 ' '
 * // s 中 至少存在一个 单词
 * //
 * //
 * //
 * //
 * //
 * //
 * //
 * // 进阶：
 * //
 * //
 * // 请尝试使用 O(1) 额外空间复杂度的原地解法。
 * //
 * // Related Topics 字符串
 * // 👍 298 👎 0
 * @author liyanjie
 * @createTime 2021-03-24 14:04
 */
public class ReverseWords {

    public static String reverseWords(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        String[] sp = s.split(" ");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < sp.length; i++) {
            if(sp[i].isEmpty()){
                continue;
            }
            stack.push(sp[i]);
            if(i != sp.length - 1){
                stack.push(" ");
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "    hello world  ";
        System.out.println(reverseWords(s));
    }

}
