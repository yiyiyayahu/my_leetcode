/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
*/


public class Solution {
    public int maxSubArray(int[] A) {
    	if(A == null || A.length == 0) return 0;
    	
     	int tmp = 0;
    	int max = Integer.MIN_VALUE;
    	
    	for(int i = 0; i < A.length; i++) {
    	    tmp += A[i];
    		if(tmp > max) {
    			max = tmp; 
    		} 
    		if(tmp < 0) {
    			tmp = 0;
    		}
    	}
    	return max;
    }
}

/*
TODO: divide and conquer approach
*/
