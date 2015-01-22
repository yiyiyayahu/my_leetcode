/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/


/*O(m+n) space*/
public class Solution {
    public void setZeroes(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] col = new boolean[matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

/*constant space*/
/*need to add two booleans to check if the first row has zeros!!!*/
public class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;
        boolean first_row_has_zero = false;
        boolean first_col_has_zero = false;
        for(int j = 0; j < matrix[0].length; j++) {
            if(matrix[0][j] == 0) first_row_has_zero = true;
        }
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] == 0) first_col_has_zero = true;
        }

        if(matrix.length > 1 && matrix[0].length > 1) {
            for(int i = 1; i < matrix.length; i++) {
                for(int j = 1; j < matrix[0].length; j++) {
                    if(matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
            
            for(int i = 1; i < matrix.length; i++) {
                for(int j = 1; j < matrix[0].length; j++) {
                    if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
        if(first_row_has_zero) {
            for(int j = 0; j < matrix[0].length; j++) 
                matrix[0][j] = 0;
        }
        if(first_col_has_zero) {
            for(int i = 0; i < matrix.length; i++) 
                matrix[i][0] = 0;
        }
    }
}
