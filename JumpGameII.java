/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
*/

/*
因为这道题只要求最小跳数，其实没必要把怎么跳的算出来
 [2,3,1,1,4] 那3，1都在2跳到的范围内，只要算这两个之间谁能跳最远就好了
 只是要注意可能跳不到的情况
*/
public class Solution {
    public int jump(int[] A) {
        if(A == null || A.length == 0 || A.length == 1) return 0;
        
        int prev = 0, curr = 0;
        int result = 0;
        curr += A[0];
        result ++;
        
        while(curr < A.length - 1) {
	        int steps = 0;
	        for(int i = prev + 1; i <= curr; i++) {
	        	if(i + A[i] > steps) steps = i + A[i]; 
	        }
	        result ++;
	        prev = curr;
	        curr = steps;
	        if(prev == curr) return 0;
        }
        return result;
    }
}
/*
这种dp的时间复杂度应该是O(n^2)，但是有TLE
*/
public class Solution {
    public int jump(int[] A) {
        if(A == null || A.length == 0) return 0;
        
        int len = A.length;
        int[] result = new int[len];
        result[0] = 0;
        
        for(int i = 1; i < len; i ++) {
            int tmp = Integer.MAX_VALUE;
            for(int j = 0; j < i; j++) {
                int steps = A[j];
                if(i - j <= steps && result[j] != -1) {
                    if(result[j] + 1 < tmp) {
                        tmp = result[j] + 1;
                        break;
                    }
                }
            }
            if(tmp == Integer.MAX_VALUE) result[i] = -1;
            else result[i] = tmp;
        }
        return result[len-1];
    }
}
