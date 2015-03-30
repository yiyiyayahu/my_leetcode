/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

//while(i >= start && A[i] == target) i--; 这里要注意先判断i是不是在index范围里，再判断A[i]== target，不然就indexOutOfBoundException了

public class Solution {
    public int[] searchRange(int[] A, int target) {
        if(A == null || A.length == 0) {
            int[] result = {-1,-1};
            return result;
        }

        return helper(A, target, 0, A.length - 1);
    }
    
    public int[] helper(int[] A, int target, int start, int end) {
        int[] result = new int[2];
        if(start > end) {
            result[0] = -1; result[1] = -1;
            return result;
        }
        int mid = (start + end)/2;
        
        if(A[mid] < target) {
            return helper(A, target, mid + 1, end);
        } else if(A[mid] > target) {
            return helper(A, target, start, mid - 1);
        } else {
            int i = mid, j = mid;
            while(i >= start && A[i] == target) i--;
            while(j <= end && A[j] == target) j++;
            
            result[0] = i < mid ? i + 1 : mid;
            result[1] = j > mid ? j - 1 : mid;

            return result;
        }
    }
}
