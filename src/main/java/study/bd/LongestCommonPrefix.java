package study.bd;

/**
 *
 *14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 *
 * 提示：
 *
 * 0 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 * 通过次数476,205提交次数1,212,074
 *
 * @author liyanjie
 * @createTime 2021-03-23 16:30
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        String res = "";
        if(strs == null || strs.length == 0){
            return res;
        }
        int min = Integer.MAX_VALUE, minIdx = 0;

        for (int i = 0; i < strs.length; i++) {
            int len = strs[i].length();
            if(len < min){
                min = len;
                minIdx = i;
            }
        }

        for (int i = 0; i < strs[minIdx].length(); i++) {
            char c = strs[minIdx].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if(j != minIdx){
                    if(strs[j].charAt(i) != c){
                        return res;
                    }
                }
            }
            res += c;
        }

        return res;
    }

}
