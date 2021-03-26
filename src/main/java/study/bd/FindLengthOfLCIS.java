package study.bd;

/**
 * 最长连续递增序列
 * @author liyanjie
 * @createTime 2021-03-26 15:40
 */
public class FindLengthOfLCIS {


    public int findLengthOfLCIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int i=0, j = 1, n = nums.length, max = 1, cnt = 1;
        while(i < n  && j < n){
            if(nums[j++] > nums[i++]){
                cnt++;
            }else {
                cnt = 1;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }

}
