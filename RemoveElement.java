/*
Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/
public class Solution {
    public int removeElement(int[] A, int elem) {
        if(A == null || A.length == 0) return 0;
        
        int i = 0;
        int j = A.length - 1;
        
        while(i <= j) {
            while (A[i] != elem) {
                if(i == A.length - 1) return A.length;
                i ++;
            }
            
            while(A[j] == elem) {
                if(j == 0) return 0;
                j --;
            }
            
            if(i > j) break;
            
            A[i] = A[j];
            A[j] = elem;
            i ++;
            j --;
        }
        return i;
    }
}

/*Follow up: if not change the relative order of original array?*/
public class Solution {
    public int removeElement(int[] A, int elem) {
        if(A == null) return 0;
        int j = 0;
        
        for(int i = 0; i < A.length; i++) {
            if(A[i] != elem) {
                A[j++] = A[i];
            }
        }
        return j;
    }
}
