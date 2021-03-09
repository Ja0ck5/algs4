package study.bd;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 * 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
 * <p>
 * 你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 0 到 n-1。
 * <p>
 * 你可以假设只有一组答案。
 * <p>
 * 您在真实的面试中是否遇到过这个题？
 * 样例
 * 样例1:
 * 给出 numbers = [2, 7, 11, 15], target = 9, 返回 [0, 1].
 * 样例2:
 * 给出 numbers = [15, 2, 7, 11], target = 9, 返回 [1, 2].
 * 挑战
 * 给自己加点挑战
 * <p>
 * O(n)O(n) 空间复杂度，O(nlogn)O(nlogn) 时间复杂度，
 * O(n)O(n) 空间复杂度，O(n)O(n) 时间复杂度，
 */
public class TwoSum {

    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < numbers.length; i ++){
            map.put(numbers[i], i);
        }

        int[] res = new int[2];

        for(int i =0; i < numbers.length; i++){
            Integer b = map.get(target - numbers[i]);
            if(b != null){
                res[0] = i;
                res[1] = b;
                return res;
            }
        }

        return res;
    }

}
