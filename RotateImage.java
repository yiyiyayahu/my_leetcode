/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

public class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int maxJ = (n%2 == 0) ? n/2 : n/2+1;
        for(int i = 0; i < n/2; i ++) {
            for(int j = 0; j < maxJ; j ++) {
                int tmp1 = matrix[i][j];
                int tmp2 = matrix[j][n-1-i];
                int tmp3 = matrix[n-1-i][n-1-j];
                int tmp4 = matrix[n-1-j][i];

                matrix[j][n-1-i] = tmp1;
                matrix[n-1-i][n-1-j] =tmp2;
                matrix[n-1-j][i] = tmp3;
                matrix[i][j] = tmp4;
            }
        }
    }
}
