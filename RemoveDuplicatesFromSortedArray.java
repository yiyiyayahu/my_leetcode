/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
*/


public class Solution {
    public int removeDuplicates(int[] A) {
        if(A == null || A.length == 0) return 0;
        int newLength = A.length;

        int index = 1;
        int tmp = A[0];
        for(int i = 1; i < A.length; i++) {
            if(A[i] != tmp) {
                A[index++] = A[i];
                tmp = A[i];
            } else {
                newLength --;
            }
        }
        return newLength;
    }
}
