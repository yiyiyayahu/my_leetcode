/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*/

/*
开始还是不太知道应该怎么做。后来看了下别人思路，其实能装多少水，就是min(leftmost, rightmost)-current if(min > current)
比如[2,1,3]那就是min(2,3)-1
但是还有一点就是[2,1,0,1,3]这段应该返回4，也就是对于0这个点来说，leftmost是2，rightmost是3
所以遍历两次，分别找leftmost和rightmost的array，最后再遍历一次，得出result
*/

public class Solution {
    public int trap(int[] A) {
        if(A == null || A.length == 0) return 0;
        int len = A.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int result = 0;
        
        left[0] = A[0]; right[len-1] = A[len-1];
        //find leftmost
        for(int i = 1; i < len; i++) {
            left[i] = Math.max(left[i-1], A[i-1]);
        }
        
        //find rightmost
        for(int i = len-2; i >= 0; i--) {
            right[i] = Math.max(right[i+1], A[i+1]);
        }
        
        for(int i = 0; i < len; i++) {
            int height = Math.min(left[i], right[i]);
            if(height > A[i]) result += height - A[i];
        }
        return result;
    }
}
