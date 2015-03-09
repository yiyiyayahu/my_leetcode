/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
*/

/*
和Nqueens类似，填的时候判断是不是valid，如果是，就往里面填
但是遇到点问题，比如我开始想的是一列一列的填嘛，程序是这样的
问题是，如果我填的过程中遇到一个'.'发现没东西填了肿么办。。。。这时候应该返回上一层重新来的对吧，但是我下面的程序没有办法达到啊
其实我觉得我这样就只能有一个循环，然后就截止了哇，就这样一直填一直填下去，注释的地方我不知道怎么弄
所以这个时候就只能让helper函数返回一个值了，如果按照当前board[i][j]=c的这个情况看能不能走下去，
如果不能return false, 然后board[i][j]置为'.'，再在这个for循环里面再弄一下，再试一次
public void helper(char[][] board, int i, int j) {
    if(j == 9) return;
    if(i >= 9) helper(board,0,j+1);
    else {
    	if(board[i][j] == '.') {
	    	for(char c = '1'; c <= '9'; c++) {					
			if(isValid(board, i, j, c)) {
				board[i][j] = c;
				helper(board, i+1, j);   
				//if has not found a solution, board[i][j]='.' and continue
			}
	    	}
    	} else {
    		helper(board, i+1, j);
    	}
    }	
}
*/
public class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9) return;
    	
    	helper(board, 0, 0);
    }
    public boolean helper(char[][] board, int i, int j) {
    	if(j == 9) return true;
    	if(i >= 9) return helper(board,0,j+1);
    	
		if(board[i][j] == '.') {
			for(char c = '1'; c <= '9'; c++) {				
				if(isValid(board, i, j, c)) {
					board[i][j] = c;
					if(helper(board,i+1,j)) return true;
				}
				board[i][j] = '.';
			}
		} else {
			return helper(board,i+1,j);
		}
    	
    	return false;
    }

    public boolean isValid(char[][] board, int i, int j, char value) {
    	for(int y = 0; y < board[0].length; y ++) {
    		if(y != j && board[i][y] == value) {
    			return false;
    		}
    	}
    	
    	for(int x = 0; x < board.length; x ++) {
    		if(x != i && board[x][j] == value) {
    			return false;
    		}
    	}    	
    	
    	for(int x  = i/3*3; x < i/3*3 + 3; x ++) {
    		for(int y = j/3*3; y < j/3*3 + 3; y ++) {
    			if( (x != i || y != j) && board[x][y]==value )
    				return false;
    		}
    	}   	
    	return true;
    }
}
