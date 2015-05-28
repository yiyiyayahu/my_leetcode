/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

/*
这道新题也太简单了吧，没有其他的trick么
*/
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int index = nums.length - k;
        return nums[index];
    }
}
