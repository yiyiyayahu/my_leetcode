/*
DFS: 会出现RuntimeError当这个array很大的时候 
(这个是因为recursive要占额外内存，和时间复杂度没关系，其实不用加visited数组的，
而且也没意义，碰到O才处理嘛，#的就表示已经visited过了)
是这样想的，从最外面的一圈开始，如果碰到O，就把它周围的O标记成#
然后再遍历这个array，把O变成X，同时把#还原

或者试试BFS
*/
public class Solution {
    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0) return;
        
        for(int i = 0; i < board.length; i++) {        	
        	dfs(board, i, 0);  
        	dfs(board, i, board[0].length-1);
        }
        for(int j = 0; j < board[0].length; j++) {      	       		
        	dfs(board, 0, j);
        	dfs(board, board.length-1, j);
        }
        for(int i = 1; i < board.length; i++) {
        	for(int j = 1; j < board[0].length; j++) {
        		if(board[i][j] == 'O') board[i][j] = 'X';
        	}
        }
        
        for(int i = 0; i < board.length; i++) {
        	for(int j = 0; j < board[0].length; j++) {
        		if(board[i][j] == '#') board[i][j] = 'O';
        	}
        }
    }
    
    public void dfs(char[][] board, int i, int j) {
        if(board[i][j] != 'O') return;
    	board[i][j] = '#';    	
    	if(i >= 1) dfs(board, i-1, j);
    	if(i < board.length-1) dfs(board, i+1, j);
    	if(j >= 1) dfs(board, i, j-1);
    	if(j < board[0].length - 1) dfs(board, i, j+1);
    }
}
