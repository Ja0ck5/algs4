package study.string;

/**
 * 描述
 * 给一个带有 B*A*C*D* 模式的字符串，* 意味着前面的字符在字符串中可以显示 0次 或 多次。计算字符 'A' 出现的次数。
 * <p>
 * 为了保证时间复杂度小于O(n),程序会重复执行 1000 次
 * <p>
 * 您在真实的面试中是否遇到过这个题？
 * 样例
 * 样例 1:
 * <p>
 * 输入: s = "BBAACCDDD"
 * 输出: 2
 * 样例 2:
 * <p>
 * 输入: s = "BBCDD"
 * 输出: 0
 * 样例 3:
 * <p>
 * 输入: s = "AAA"
 * 输出: 3
 * 挑战
 * 如果字符串太大而无法全部读入到内存中该怎么办？
 */
public class TheNumberOfA {

    /**
     * @param s: the given string
     * @return: the number of A
     */
    public int countA(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int start = getAstart(s);
        int end = getAEnd(s);
        return end - start + 1;
    }

    private int getAEnd(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            char tmp = s.charAt(mid);
            if(tmp <= 'B'){
                start = mid;
            }else {
                end = mid;
            }
        }
        if(s.charAt(end) == 'A'){
            return end;
        }
        return start;
    }

    private int getAstart(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            char tmp = s.charAt(mid);
            if(tmp == 'B'){
                start = mid;
            }else if(tmp == 'A' || tmp > 'B'){
                end = mid;
            }
        }
        if(s.charAt(start) == 'A'){
            return start;
        }
        return end;
    }

}
