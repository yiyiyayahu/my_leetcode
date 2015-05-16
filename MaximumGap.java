/*
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

Credits:
Special thanks to @porker2008 for adding this problem and creating all test cases.
*/

/*
下面这个是最简单的做法，先sort然后再遍历。但是不符合题目要求，这个的时间复杂度是O(nlogn)
*/
public class Solution {
    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        Arrays.sort(nums);
        int gap = 0;
        for(int i = 1; i < nums.length; i++) {
            gap = Math.max(gap, nums[i]-nums[i-1]);
        }
        return gap;
    }
}
