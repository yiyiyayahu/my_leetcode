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


完完全全想错了。。。其实你想啊，如果这个程序走不下去的话，我根本不会hit到最后的j==9诶，因为如果isValid没找到的话，我i无法加1，程序就执行不下去啊，这时候就会重新来嘛
我这里少了一步，就是我调用完board[i][j] = c; helper(board, i+1, j);之后我应该把board[i][j]重置为'.'不然真的就是只能走一次
但是为什么这样改了之后还是不行呢，因为当我找到了这样一个valid的board之后，到了if(j==9)了，我就直接return了！！！
return意味着什么，又回到上一层了，又重置为'.'了，所以输出的结果还是初始的board啊
这里我只要能找到一个方式记录下来当前board的信息，就成了。
想想NQueens和后来改的Combinations，都是这样做的啊，在return那里用static记录当前的信息，或者做一些什么其他操作！！！

看答案的坏处就是以为只有答案才是对的，自己想的是错的，自己怎么就没想到答案那样子呢。。。。应该像答案学习嘛。。。其实就是不理解
以后要试着在脑中把程序走一遍，这样就清晰多了！！！
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
//可以这样改，但是其实也没有必要存到一个result的array里面，我想要的效果只是在j==9return到上一层的时候不要把board[i][j]重置为'.'就可以了
    static char[][] result;
    public static void solveSudoku(char[][] board) {
    	if(board == null || board.length != 9 || board[0].length != 9) return;
    	 	
    	helper(board, 0, 0);
    }
    public static void helper(char[][] board, int i, int j) {
    	if(j == 9) {
    		result = new char[9][9];
    		for(int x = 0; x < 9; x ++) {
    			for(int y = 0; y < 9; y++) {
    				result[x][y] = board[x][y];   				
    			}
    		}   
		return;
    	}
    	if(i >= 9) helper(board,0,j+1);
    	else {
	    	if(board[i][j] == '.') {
	    		for(char c = '1'; c <= '9'; c++) {					
					if(isValid(board, i, j, c)) {
						board[i][j] = c;
						helper(board, i+1, j);  
						if(result == null) board[i][j]='.';
					}
					
	    		}
	    	} else {
	    		helper(board, i+1, j);
	    	}
    	}	
    }
//所以用个boolean记录就可以的，重点是这一步if(finished == false) board[i][j]='.';
    static boolean finished;
    public static void solveSudoku(char[][] board) {
    	if(board == null || board.length != 9 || board[0].length != 9) return;
    	 	
    	helper(board, 0, 0);
    }
    public static void helper(char[][] board, int i, int j) {
    	if(j == 9) {
    		finished = true;
			return;
    	}
    	if(i >= 9) helper(board,0,j+1);
    	else {
	    	if(board[i][j] == '.') {
	    		for(char c = '1'; c <= '9'; c++) {					
					if(isValid(board, i, j, c)) {
						board[i][j] = c;
						helper(board, i+1, j);  
						if(finished == false) board[i][j]='.';
					}
					
	    		}
	    	} else {
	    		helper(board, i+1, j);
	    	}
    	}	

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
