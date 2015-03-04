/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/

/*
其实是二分法，但是不同的是，array在某个元素那里rotate了一下，但是只要判断下是
left part of A is sorted or right part of A is sorted，然后相应地操作就行了
自己犯的一个错误：forget to check if mid-1 or mid+1 is a valid index !!!!切记，array啊，或者linkedlist啊这种都要查的
*/

public class Solution {
    public int search(int[] A, int target) {
        if(A == null || A.length == 0) return -1;
		return helper(A, target, 0, A.length - 1);
    }
	
	public static int helper(int[] A, int target, int start, int end) {
		if(start > end) return -1;
		int mid = (start + end)/2;
		if(target == A[mid]) return mid;

		if(A[start] < A[mid]) {
			if(target < A[start]) return helper(A, target, mid+1, end);
			else if(mid - 1 >= start && target > A[mid - 1]) return helper(A, target, mid+1, end);
			else return helper(A, target, start, mid-1);
		} else {
			if(target > A[end]) return helper(A, target, start, mid-1);
			else if(mid+1 <= end && target < A[mid+1]) return helper(A, target, start, mid-1);
			return helper(A, target, mid+1, end);
		}
	}
}
