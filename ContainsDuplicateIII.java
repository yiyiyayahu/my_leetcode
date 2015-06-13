/*
Given an array of integers, find out whether there are two distinct indices i and j in the array 
such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*/

/*
这个用treeset的话，其实里面相当于bst，所以时间复杂度就是O(nlogk)
然后要注意边界
*/
public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0) return false;
        TreeSet<Integer> treeset = new TreeSet<Integer>();
        
        for(int i = 0; i < nums.length; i++) {
            int low = nums[i] < Integer.MIN_VALUE + t ? Integer.MIN_VALUE: nums[i] - t ;
            int high = nums[i] > Integer.MAX_VALUE - t - 1 ? Integer.MAX_VALUE: nums[i] + t + 1;
            if(t >= 0 && treeset.subSet(low, high).size() > 0) return true;
            treeset.add(nums[i]);
            if(i >= k) treeset.remove(nums[i-k]);
        }
        return false;
    }
}
