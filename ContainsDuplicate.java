/*
Given an array of integers, find if the array contains any duplicates. 
Your function should return true if any value appears at least twice in the array, 
and it should return false if every element is distinct.
*/
public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]) return true;
        }
        return false;
    }
}

/*
这里其实理解错了，只有every element is distinct的时候才return false，我以为要所有的至少出现两次呢
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
