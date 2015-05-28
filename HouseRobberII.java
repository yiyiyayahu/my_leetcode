/*
Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. 
This time, all houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. 
Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.
*/

/*
我的想法是用两个array做dp
其实这个circle就是要考虑两种情况：
1. nums[0] involved, nums[n-1] not
2. nums[0] not involved, nums[n-1] involvoed
第一种情况，算到dp1[len-2]
第二种情况，dp2[0]置为0，算到dp2[len-1]
最后两者取最大

要注意array长度为1和为2的情况
*/
public class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];

        int len = nums.length;
        int[] dp1 = new int[len];
        int[] dp2 = new int[len];
        dp1[0] = nums[0]; dp1[1] = nums[1];
        dp2[0] = 0; dp2[1] = nums[1];

        for(int i = 1; i < len-1; i++) {
            dp1[i] = Math.max(dp1[i-1], i < 2 ? nums[i] : dp1[i-2] + nums[i]);
        }
        
        for(int i = 2; i < len; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + nums[i]);
        }

        return Math.max(dp1[len-2], dp2[len-1]);
    }
}
