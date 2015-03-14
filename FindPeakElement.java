/*
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Note:
Your solution should be in logarithmic complexity.
*/

public class Solution {
    public int findPeakElement(int[] num) {
        if(num == null || num.length == 0) return -1;
    	return helper(num, 0, num.length-1);
    }
    
    public int helper(int[] num, int start, int end) {
    	if(start > end) return -1;
    	
    	int mid = (start+end)/2;
    	
    	if(mid - 1 >= start && num[mid] < num[mid-1]) {
    		return helper(num, start, mid-1);
    	}
    	
    	if(mid + 1 <= end && num[mid] < num[mid+1]) {
    		return helper(num, mid+1, end);
    	}
    	
    	return mid;
    }
}
