/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
*/


public class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[] appeared = new boolean[9];
    	
    	//check if each row has the numbers 1-9 occurring just once.
    	for(int i = 0; i < board.length; i++) {
    		appeared = new boolean[9];
    		for(int j = 0; j < board[0].length; j++) {
    			char value = board[i][j];
    			if(value != '.' ) {
        			if(appeared[value-'1'] == true) return false;
        			else appeared[value-'1'] = true;
    			}
    		}
    	}
    	
    	//check if each column has the numbers 1-9 occurring just once.
    	for(int j = 0; j < board[0].length; j++) {
    		appeared = new boolean[9];
    		for(int i = 0; i < board.length; i++) {
    			char value = board[i][j];
    			if(value != '.' ) {
        			if(appeared[value-'1'] == true) return false;
        			else appeared[value-'1'] = true;
    			}
    		}
    	}
    	
    	//check if the numbers 1-9 occur just once in each of the 9 sub-boxes of the grid
    	for(int i = 0; i < board.length-2; i += 3) {
    		for(int j = 0; j < board[0].length - 2; j += 3) {
    			appeared = new boolean[9];
    			for(int x = i; x < i + 3; x ++) {
    				for(int y = j; y < j + 3; y ++) {
    					char value = board[x][y];
            			if(value != '.' ) {
                			  if(appeared[value-'1'] == true) return false;
                		      else appeared[value-'1'] = true;
            			}
    				}
    			}
    		}
    	}	
        return true;
    }
}
