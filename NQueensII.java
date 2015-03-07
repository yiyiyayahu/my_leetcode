/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
*/


//思路就是::
/*
 * for(int value = 0; value < n; value ++) {
 * 		if(value is valid) {
 * 			A[index] = value;
 * 			call getNQueens(A, index + 1, n);
 * 		}
 * }
 */


/*
开始想的是把getNQueens return一个int[]，但是这个的问题是，
比如if(isValid) {A[index]=value;return getNQueens(A, index+1, n);} 那就只能返回一个结果
那如果是void，比如填index=0的时候，有n种可能，调用n次getNQueens(A, index+1, n),依次类推，如果最后每找到个valid的可能，count就+1
这种程序的写法要注意！！！
并且这里的count作为一个static的variable也很重要！毕竟java的int不是reference的，而且如果作为参数传进来是有问题的
比如如果初始化count是0，传进去就是0，那每次return之前，count都是0，最后的结果也就是1而已
*/
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
    	if(index == n) { count ++; return; }
    	for(int value = 0; value < n; value++) {
    		 boolean isvalid = true;
    		 for(int j = 0; j < index; j++) {
    			 int diff = Math.abs(index-j) - Math.abs(value-A[j]);
    			 if(diff == 0 || diff == Math.abs(index-j)) {
    				 isvalid = false; 
    				 break;
    			 }
    		 }
    		 if(isvalid) {
    			 A[index] = value;
    			 getNQueens(A, index+1, n);
    		 }
    	}
    }
}
