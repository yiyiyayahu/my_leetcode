public class Solution {
    static int count;
    public static int totalNQueens(int n) {
        int[] A = new int[n];
    	count = 0;
    	getNQueens(A, 0, n);
    	return count;
    }
    public static void getNQueens(int[] A, int index, int n) {
    	//fill out A[index] to complete the array(NQueens result), suppose A[index-1] is complete and valid
    	if(index == n) {count ++;return;}
    	for(int value = 0; value < n; value++) {
    		 boolean isvalid = true;
    		 A[index] = value;
    		 for(int j = 0; j < index; j++) {
    			 int diff = Math.abs(index-j) - Math.abs(value-A[j]);
    			 if(diff == 0 || diff == Math.abs(index-j)) {
    				 isvalid = false; 
    				 break;
    			 }
    		 }
    		 if(isvalid) {
    			 getNQueens(A, index+1, n);
    		 }
    	}
    }
}
