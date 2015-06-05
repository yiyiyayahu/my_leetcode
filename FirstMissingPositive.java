public class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) return 1;

        for(int i = 0; i < nums.length; i++) {
            
            int index = nums[i]-1;
            if(index >=0 && index < nums.length && nums[i] != nums[index] && nums[i] != i+1) {
                int tmp = nums[index];
                nums[index] = nums[i];
                nums[i] = tmp;
                i --;
            }
        } 

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == i+1) continue;
            else return i+1;
        }
        return nums.length+1;
    }
}
