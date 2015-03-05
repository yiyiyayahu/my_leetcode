/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
*/

public class Solution {
    public boolean search(int[] A, int target) {
        if(A == null || A.length == 0) return false;
		return helper(A, target, 0, A.length - 1);
    }
	
	public boolean helper(int[] A, int target, int start, int end) {
		if(start > end) return false;
		int mid = (start + end)/2;
		if(target == A[mid]) return true;
	
		while(start < mid && A[start] == A[mid]) {
			start ++;
		}
		
		if(A[start] < A[mid]) {
			if(target < A[start] || target > A[mid-1]) return helper(A, target, mid+1, end);
			else if(mid - 1 >= start && target > A[mid - 1]) return helper(A, target, mid+1, end);
			else return helper(A, target, start, mid-1);
		} else if (A[start] > A[mid]){
			if(target > A[end]) return helper(A, target, start, mid-1);
			else if(mid+1 <= end && target < A[mid+1]) return helper(A, target, start, mid-1);
			return helper(A, target, mid+1, end);
		} else {
			return helper(A, target, mid+1, end);
		}
    }
}
