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

/*
开始只能想出遍历的方法，也就是O(n)。但是提示是O(logn)，应该就是要二分的那种
其实这道题有很多peak嘛，只要返回一个就可以。那如果mid满足条件，OK，返回mid
如果mid小于左边的元素，那在[start,mid-1]之间肯定有一个peak，同理，如果mid小于右边的元素，就在[mid+1,end]之间找
比如[x,x,2,3,4,x,x]，3现在小于4，只能在4后面找，如果4后面的小于4，OK，4是peak。
如果4后面的是5，那5后面是6的话，6是peak，如果5后面是3，5是peak。所以要么后面是递增的，最后一位是peak，要么有一位小，中间那个就是peak
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

/*
原来写的特别复杂。。。后来refactor了一下成了上面的情况。
其实仔细想想，如果不在(mid - 1 >= start && num[mid] < num[mid-1])的情况下，start到mid-1的区间根本用不上，右边同理
那是不是所有其他情况都可以返回mid呢，我觉得是的
比如如果mid - 1 >= start，然后num[mid] > num[mid-1]那这种情况要么调用右边，要么返回mid
如果mid - 1 < start, 也是要么调用右边，要么返回mid
所以调用左边和调用右边的情况都考虑清楚以后，所有的都应该返回mid了

    	if(mid-1 >= start && mid + 1 <= end) {
    		if(num[mid] > num[mid-1] && num[mid] > num[mid+1]) return mid;
    		else if(num[mid] < num[mid-1]) return helper(num, start, mid-1);
    		else if(num[mid] < num[mid+1]) return helper(num, mid+1, end);
    	}
    	else if(mid - 1 < start) {
    		if(mid + 1 <= end) {
    			if(num[mid] > num[mid+1]) return mid;
    			else return helper(num, mid + 1, end);
    		} else {
    			return mid;
    		}
    	} else {
    		if(mid -1 >= start) {
    			if(num[mid] > num[mid-1]) return mid;
    			else return helper(num, start, mid-1);
    		} else {
    			return mid;
    		}
    	}
    	return -1;
*/
