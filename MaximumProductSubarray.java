/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

/*
这道题和maximum subarray类似，应该都是用O(n)解法可以做出来的，array扫描一遍
不同的是，之前遇到小于0的就把之前的放弃，重新设新的起点。但是这里存在负负得正的情况。
所以，不仅要存当前最大的，也要存当前最小的
比如[2,3,-2,-4]到i=2的时候，最大的是[2,3]但是如果看整个array的话，最大的结果是[2,3,-2,-4]也就是我要把-12这个结果保留下来
所以用了max和min两个变量，每次max*nums[i]，min*nums[i]
还要注意一点的是：0的情况
比如[1,0,9,2]或者[1,0,-9,2]，这个时候，9和-9都可以分别作为max和min新的起点
所以当乘出来的结果比nums[i]小的时候,max更新成nums[i]
同理，结果比nums[i]大的时候，min更新成nums[i]
*/
public class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int max = 1, min = 1, ret = Integer.MIN_VALUE;
        
        for(int i = 0; i < nums.length; i++) {
            int a = max * nums[i], b = min * nums[i];
            max = Math.max(Math.max(a,b), nums[i]);
            min = Math.min(Math.min(a,b), nums[i]);
            ret = Math.max(max, ret);
        }
        return ret;
    }
}
