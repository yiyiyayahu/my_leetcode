/*
Given a m x n grid filled with non-negative numbers, 
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
*/

public class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null) return 0;
        
        int m = grid.length, n = grid[0].length;
        int[][] result = new int[m][n];
        
        if(m==1 && n==1) return grid[0][0];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) result[i][j] = grid[0][0];
                else if(j == 0) result[i][j] = result[i-1][j] + grid[i][j];
                else if(i == 0) result[i][j] = result[i][j-1] + grid[i][j];
                else
                result[i][j] = Math.min( result[i][j-1], result[i-1][j] ) + grid[i][j];
            }
        }
        return result[m-1][n-1];
    }
}
