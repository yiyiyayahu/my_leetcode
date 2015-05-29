/*
Given an array of integers and an integer k, return true if and only if 
there are two distinct indices i and j in the array such that nums[i] = nums[j] 
and the difference between i and j is at most k.
*/

public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null || nums.length == 0) return false;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if(map.containsKey(n)) {
                int diff = i - map.get(n);
                if(diff <= k) return true;
            } else {
                map.put(n, i);
            }
        }
        return false;
    }
}
