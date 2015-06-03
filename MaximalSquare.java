/*
 Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 4. 
*/

/*
简化为一维数组dp[]和一个int pre，其实只需要知道上面的和左边的值
很巧妙诶，参考别人写出来的
*/
public class Solution {
    public int maximalSquare(char[][] matrix) {
      if(matrix == null || matrix.length == 0) return 0;
      int rows = matrix.length, cols = matrix[0].length;
      int maxSize = 0, pre = 0;
    
      int[] dp = new int[rows+1];
      for(int j = 0; j < cols; j++) {
        for(int i = 1; i <= rows; i++) {
          int tmp = dp[i];
          if(matrix[i-1][j] == '1') {
            dp[i] = Math.min(dp[i], Math.min(dp[i-1], pre)) + 1;
            maxSize = Math.max(dp[i], maxSize);
          } else {
            dp[i] = 0;
          }
          pre = tmp;
        }
      }
      return maxSize * maxSize;
    }
}

/*
dp的做法：
dp[0][j]= matrix[0][j]-'0'
dp[i][0] = matrix[i][0]-'0'
dp[i][j] = 0 if matrix[i][j]=='0'
         = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
但是可以优化成一维数组来维护。再想想
*/
public class Solution {
    public int maximalSquare(char[][] matrix) {
      if(matrix == null || matrix.length == 0) return 0;
      int rows = matrix.length, cols = matrix[0].length;
      int[][] dp = new int[rows][cols];
      int maxSize = 0;
    
      for(int j = 0; j < cols; j++) {
        dp[0][j] = matrix[0][j] - '0';
        maxSize = Math.max(maxSize, dp[0][j]);
      }
      for(int i = 0; i < rows; i++) {
        dp[i][0] = matrix[i][0] - '0';
        maxSize = Math.max(maxSize, dp[i][0]);
      }
      for(int i = 1; i < rows; i++) {
        for(int j = 1; j < cols; j++) {
          if(matrix[i][j] == '1') {
            dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
            maxSize = Math.max(maxSize, dp[i][j]);
          }
        }
      }
      return maxSize*maxSize;
    }
}

/*
简单粗暴
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
