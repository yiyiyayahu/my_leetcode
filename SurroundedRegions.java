/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/

/*
BFS就是可以的
*/
class Pair {
     int i;
     int j;
     Pair() { i = 0; j = 0; }
     Pair(int i, int j) { this.i = i; this.j = j; }
}
public class Solution {
    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0) return;
        int rows = board.length, cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        
        Queue<Pair> queue = new LinkedList<Pair>();
        for(int i = 0; i < rows; i++) { 
        	if(board[i][0] == 'O') {
        		queue.add(new Pair(i, 0));
        	}
        	if(board[i][cols-1] == 'O') {
        		queue.add(new Pair(i, cols-1));
        	}       	
        }
        for(int j = 0; j < cols; j++) {      	       		
        	if(board[0][j] == 'O') {
        		queue.add(new Pair(0,j));
        	}
        	if(board[rows-1][j] == 'O') {
        		queue.add(new Pair(rows-1, j));
        	}
        }
        
        while(!queue.isEmpty()) {
        	Pair p = queue.remove();
        	board[p.i][p.j] = '#';
        	if(p.i>=1 && board[p.i-1][p.j] == 'O') queue.add(new Pair(p.i-1,p.j));
        	if(p.i<rows-1 && board[p.i+1][p.j] == 'O') queue.add(new Pair(p.i+1,p.j));
        	if(p.j>=1 && board[p.i][p.j-1] == 'O') queue.add(new Pair(p.i,p.j-1));
        	if(p.j<cols-1 && board[p.i][p.j+1] == 'O') queue.add(new Pair(p.i,p.j+1));
        }
        
        for(int i = 0; i < rows; i++) {
        	for(int j = 0; j < cols; j++) {
        		if(board[i][j] == 'O') board[i][j] = 'X';
        		if(board[i][j] == '#') board[i][j] = 'O';
        	}
        } 
    }
}
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
        
        //这里没必要分开弄，一个循环就好了嘛。反正就是O,X,#三种，而且每个元素也就遍历一遍
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
