/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

/*
滑动窗口法
[2, 3, 1, 2, 4, 3] s = 7
[2, 3, 1, 2]
   [3, 1, 2, 4]
      [1, 2, 4]
         [2, 4, 3]
             [4,3]
其实就是有一个sum，一个start，一个end，start++, sum-=nums[start]这样依次做下去
时间复杂度应该是O(n)吧，因为每个元素其实也只遍历了constant time
*/

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int start = 0, end = 0, size = nums.length;
        int sum = 0;
        int minlen = size + 1;
        while(start < size) {
            while(sum < s && end < size) {
                sum += nums[end];
                end ++;
            }
            int tmp = (sum >= s) ? end - start : size + 1;
            minlen = Math.min(minlen, tmp);
            sum -= nums[start];
            start ++;
        }
        if(minlen == size + 1) return 0;
        return minlen;
    }
}
