/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

/*
开始时候TLE是因为，我这里是有循环的
[a,a,a,a]
[a,a,a,a]
[a,a,a,b]
我很有可能转了一圈还在搜
所以要加入一个boolean[][] visited数组
当在helper里面发现visited[i][j]=true的时候就说明进入了一个循环，应该返回false了
记得recursion完了之后再把visited[i][j]设回false
*/
public class Solution {
    public boolean exist(char[][] board, String word) {
        if(word == null || word.length() == 0) return false;
        int rows = board.length, cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        
        for(int i = 0; i < rows; i++) {
        	for(int j = 0; j < cols; j++) {
        	    if(helper(board, word, i, j, 0, visited)) return true;
        	}
        }
        return false;
    }
    
    public boolean helper(char[][] board, String word, int i, int j, int wIndex, boolean[][] visited) {
        if(wIndex == word.length()) return true;
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        if(visited[i][j] || board[i][j] != word.charAt(wIndex)) return false;
        
        visited[i][j] = true;
        if(helper(board, word, i-1, j, wIndex+1, visited)) return true;
        if(helper(board, word, i+1, j, wIndex+1, visited)) return true;
        if(helper(board, word, i, j-1, wIndex+1, visited)) return true;
        if(helper(board, word, i, j+1, wIndex+1, visited)) return true;
        visited[i][j] = false;
        return false;
    }
}
