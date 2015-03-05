/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

/*
3/4更新
和search in rotated sorted array类似，判断这个array哪部分是sort过的，然后减少遍历次数，时间复杂度O(logn)
但是还是犯了很多错误，导致弄了好久才过
比如如果num=[1]和[2,1]的时候
*/
public class Solution {
    public int findMin(int[] num) {
		if(num == null || num.length == 0) return Integer.MAX_VALUE;
		return findMinHelper(num, 0, num.length - 1);
    }
	
	public int findMinHelper(int[] num, int start, int end) {
	    if(start == end) return num[start];
		int mid = (start + end)/2;
		int left_min = Integer.MAX_VALUE, right_min = Integer.MAX_VALUE;
		if(num[start] <= num[mid]) {
			left_min = num[start];
			if(mid < end) right_min = findMinHelper(num, mid+1, end);
		} else {
			left_min = findMinHelper(num, start+1, mid);
			if(mid < end) right_min = findMinHelper(num, mid+1, end);
		}
		return Math.min(left_min, right_min);
	}
}

/*
这个解法显然是不好的，上面的array是sorted，而且还只rotate一次，要改进
这道题和Search in Rotated Sorted Array这个差不多
*/
public class Solution {
    public int findMin(int[] num) {
        if(num == null || num.length == 0) return Integer.MIN_VALUE;
        
        int min = num[0];
        
        for(int i = 1; i < num.length; i++) {
            if(num[i] < num[i-1]) {
                if(num[i] < min) min = num[i];
            }
        }
        return min;
    }
}
