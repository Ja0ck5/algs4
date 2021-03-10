package study.arr;

/**
 * 把数组中的 0 移动到最后
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums){
        int j = 0,i;
        for (i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }
}
