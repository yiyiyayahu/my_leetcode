/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*/

/*
和1的区别就是当start和mid相等的时候start后移
时间复杂度增加，因为重复元素的关系，可能没有办法二分，所以是O(n)
*/
public class Solution {
    public int findMin(int[] num) {
		if(num == null || num.length == 0) return Integer.MIN_VALUE;
		return findMinHelper(num, 0, num.length - 1);
    }
	
	public static int findMinHelper(int[] num, int start, int end) {
		if(start == end) return num[start];
		int mid = (start + end)/2;
		int left_min = Integer.MAX_VALUE, right_min = Integer.MAX_VALUE;
		
		while(start < mid && num[start] == num[mid]) {
			start ++;
		}
		
		if(num[start] <= num[mid]) {
			left_min = num[start];
			if(mid<end) right_min = findMinHelper(num, mid+1, end);
		} else {
			left_min = findMinHelper(num, start+1, mid);
			if(mid<end) right_min = findMinHelper(num, mid+1, end);
		} 
		return Math.min(left_min, right_min);
	}
}
