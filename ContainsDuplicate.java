/*
Given an array of integers, find if the array contains any duplicates. 
Your function should return true if any value appears at least twice in the array, 
and it should return false if every element is distinct.
*/

public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        if(noDuplicate(nums)) return false;
        return true;
    }
    
    public boolean noDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) return false;
            else set.add(nums[i]);
        }
        return true;
    }
}
