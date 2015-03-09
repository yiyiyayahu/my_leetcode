/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
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
