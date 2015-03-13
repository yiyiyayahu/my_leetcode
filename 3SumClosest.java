/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
Return the sum of the three integers. You may assume that each input would have exactly one solution.

For example, given array S = {-1 2 1 -4}, and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/
public class Solution {
    public int threeSumClosest(int[] num, int target) {
        if(num == null || num.length == 0) return Integer.MAX_VALUE;
    	
    	Arrays.sort(num);
    	int len = num.length;
    	int minDiff = Integer.MAX_VALUE;
    	
    	for(int i = 0; i < len - 2; i++) {
			int start = i+1, end = len-1;
			
			while(start < end) {
				int tmp = num[start] + num[end] + num[i];
				if(tmp == target) return target;
				
				int diff = tmp - target;
				if(minDiff == Integer.MAX_VALUE || Math.abs(diff) < Math.abs(minDiff)) {
					minDiff = diff;
				} 
				if(tmp < target) start ++;
				else if(tmp > target) end --;	
			}
    	}
    	int result = target + minDiff;
        return result;
    }
}
