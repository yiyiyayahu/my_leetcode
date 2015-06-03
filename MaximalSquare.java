/*
 Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 4. 
*/
public class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null) return 0;
        int result = 0;
        for(int i = 0 ; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1') {
                    int len = 2;
                    while(helper(matrix,i,j,len)) {
                        len ++;
                    }
                    len = len-1;
                    result = Math.max(result, len*len);
                }
            }
        }
        return result;
    }
    public boolean helper(char[][] matrix, int row, int col, int len) {
        if(row + len > matrix.length || col + len > matrix[0].length) return false;

        for(int i = row; i < row+len; i++) {
            for(int j = col; j < col+len; j++) {
                if(matrix[i][j] != '1') return false;
            }
        }
        return true;
    }
}
