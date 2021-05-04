package study.bd.vip;

/**
 * 243. 最短单词距离
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 * <p>
 * 示例:
 * 假设 words = ["practice", "makes", "perfect", "coding", "makes"]
 * <p>
 * 输入: word1 = “coding”, word2 = “practice”
 * 输出: 3
 * 输入: word1 = "makes", word2 = "coding"
 * 输出: 1
 * 注意:
 * 你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
 * <p>
 * 通过次数6,987提交次数10,637
 * https://leetcode-cn.com/problems/shortest-word-distance/
 */
public class ShortestWordDistance {

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        if (wordsDict == null || wordsDict.length == 0) return 0;
        int i1 = -1, i2 = -1;
        int min = wordsDict.length;
        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1)) {
                i1 = i;
            } else if (wordsDict[i].equals(word2)) {
                i2 = i;
            }
            if (i1 != -1 && i2 != -1) {
                min = Math.min(min, Math.abs(i2 - i1));
            }
        }
        return min;
    }

}
