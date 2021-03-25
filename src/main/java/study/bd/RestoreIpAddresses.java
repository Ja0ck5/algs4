package study.bd;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原 IP 地址
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 *
 *
 *
 * 1. 回溯算法事实上就是在一个树形问题上做深度优先遍历，因此 首先需要把问题转换为树形问题
 * 2. 有些枝叶是没有必要的，把没有必要的枝叶剪去的操作就是剪枝，在代码中一般通过 break 或者 contine 和 return （表示递归终止）实现
 * 3. 切割问题就可以使用回溯搜索法把所有可能性搜出来
 * https://www.bilibili.com/video/BV1cy4y167mM/
 * @author liyanjie
 * @createTime 2021-03-25 16:13
 */
public class RestoreIpAddresses {

    private static final int SEG = 4;

    List<String> res = new ArrayList<>();
    int[] segments = new int[SEG];

    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return res;
    }

    private void dfs(String s, int cnt, int start){
        if(cnt == SEG){
            if(start == s.length()){
                process();
            }
            return;
        }
        if(start == s.length()){
            return;
        }
        if(s.charAt(start) == '0'){
            segments[cnt] = 0;
            dfs(s, cnt + 1, start + 1);
        }
        int addr = 0;
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            addr = addr * 10 + (c - '0');
            if(addr > 0 && addr <= 0xFF){
                segments[cnt] = addr;
                dfs(s, cnt + 1, i + 1);
            }else{
                break;
            }
        }
    }

    private void process() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < segments.length; i++) {
            sb.append(segments[i]);
            if (i != segments.length - 1) {
                sb.append(".");
            }
        }
        res.add(sb.toString());
    }

}
