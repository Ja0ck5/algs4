package study.string;

/**
 * 151. 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入："the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 * <p>
 * 输入："  hello world!  "
 * 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入："a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 4：
 * <p>
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * 示例 5：
 * <p>
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 请尝试使用 O(1) 额外空间复杂度的原地解法。
 */
public class ReverseWords {

    public String reverseWords(String s) {
        if (s == null || (s = s.trim()).isEmpty()) return "";
        int i, j, start, end;
        //首先翻转trim()后的一大长条字符串，之后再翻转每个单词(String内定义的char[]用final修饰，不可再变)
        StringBuilder sentence = new StringBuilder(s).reverse();
        //用来记录一个单词的前后两个空格
        start = end = 0;
        for (i = 0; i < sentence.length(); i++) {
            //如果碰到了一个空格，说明这个空格前到之前的一个空格已经有一个单词了，现在要翻转这个单词了
            if (sentence.charAt(i) == ' ') {
                //单词之间只能有一个空格，删除多余空格
                j = i + 1;
                while (sentence.charAt(j) == ' ') {
                    j++;
                }
                //delete方法前 包括，后 不包括
                sentence.delete(i + 1, j);
                //翻转单个单词
                end = i - 1;
                revSingleWord(sentence, start, end);
                //重新确定下一个单词的起始位置
                start = i + 1;
            }
        }
        //由于最后一个单词末尾没有空格，所以这里多处理一下
        revSingleWord(sentence, start, i - 1);
        return sentence.toString();
    }

    /**
     * 翻转单个单词
     */
    public void revSingleWord(StringBuilder sentence, int start, int end) {
        while (start < end) {
            char temp = sentence.charAt(start);
            sentence.setCharAt(start++, sentence.charAt(end));
            sentence.setCharAt(end--, temp);
        }
    }



    public String reverseWords2(String s) {
        StringBuilder sb = trimSpaces(s);

        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }

    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ')
            ++left;
        while (left <= right && s.charAt(right) == ' ')
            --right;

        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            ++left;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }

    public static void main(String[] args) {
        char a = 'a';
        char b = 'b';
        System.out.println(a+""+b);
    }

}
