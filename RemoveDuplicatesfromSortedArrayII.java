/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].
*/

/*
用一个index来作为新的array的index~ 和I差不多，只是这里两次的OK，就多加了一个times变量，细节要注意 
*/
public class Solution {
    public int removeDuplicates(int[] A) {
        if(A == null || A.length == 0) return 0;
		
	int index = 1, curr = A[0], result = A.length, times = 1;
	for(int i = 1; i < A.length; i++) {
		if(A[i] != curr) {
			A[index ++] = A[i];
			curr = A[i];
			times = 1;
		} else {
			if(times >= 2) {
				result --;
			} else {
				A[index ++] = A[i];
			}
			times ++;
		}
	}
	return result;

    }
}
