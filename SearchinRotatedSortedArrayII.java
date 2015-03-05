/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
*/

/*
与1不同的是，有重复元素的话就会出现A[start] == A[mid]的情况，这样就不好判断那部分是sorted了
只能遇到重复元素的时候指针后移。那worst case呢，复杂度就是O(n)，而1的复杂度是O(logn)
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
