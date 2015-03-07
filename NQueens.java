/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/


/* 把这个想成填位置？有点像DFS诶
 * 首先NQueens可以表示成一维数组，然后记得判断对角线是不是可以的，那判断方法是|i1-i2|!=|a[i1]-a[i2]|，并且不能和之前填过的数字重复
 * 只能一位一位往里面填数字，填下一位的时候判断是不是valid的，再往下做，类似于往n个位置里面填数字
 * recursive可以是记录下上一个填到了哪个index，然后接着填
 * 我之前对于recursive的理解有点问题，recursive其实是指重复的调用自己，因为每次计算的步骤是一样的
 * 但是并不是说，已知了第n-1个情况的结果，就一定能推出第n个
 * 其实recursive有点像，比如你填4个，填到第四个，得到一个结果，return，然后再返回上一层，填下一个可能的第四个。。。依次类推，然后改第三层的数，再填第四层。。。。
 * 所谓的返回上一层，实际上其实是，比如我做到index这一步，想得到index+1的结果的时候，就跳到了一个新的函数里面执行，得到结果之后再继续做而已
 */
public class Solution {
    static List<String[]> NQueensList;
    public List<String[]> solveNQueens(int n) {
    	NQueensList = new ArrayList<String[]>();
    	int[] A = new int[n];
    	getNQueens(A, 0, n);
    	return NQueensList;
    }
    public void getNQueens(int[] A, int index, int n) {
    	if(index == n) { 
    		String[] s = printNQueens(A, n);
    		NQueensList.add(s);
    		return; 
    	}

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
    public String[] printNQueens(int[] A, int n) {
    	String[] result = new String[n];
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < n; i++) {   		
    		for(int j = 0; j < n; j++) {
    			if(j == A[i]) sb.append("Q");
    			else sb.append(".");
    		}
    		result[i] = sb.toString();
    		sb = new StringBuilder();
    	}
    	return result;
    }
}
