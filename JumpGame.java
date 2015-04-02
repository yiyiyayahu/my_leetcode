/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/

/*
我觉得下面的解法应该是可以，当然还是最好用dp，这样写会出time limit exceeded
*/
public class Solution {
    public boolean canJump(int[] A) {
        if(A == null || A.length == 0) return true;
        return canJump(A, A.length - 1);
    }
    
    public boolean canJump(int[] A, int end) {
        if(end == 0) return true;
        
        for(int i = end-1; i >= 0; i--) {
            int steps = A[i];
            if(end - i <= steps) {
                if(canJump(A, i)) return true;
            }
        }
        return false;
    }
}
