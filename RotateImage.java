/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

/*
这道题之前看cc的时候就有点懵，这次总算是自己做出来的
但是我开始还是错了，没有考虑到如果n是奇数，那其实在最中间那一列，也就是j=n/2这一列是不变的，因为我没有挪动过
改过之后就多加了一步判断，如果是奇数，那j是可以等于n/2的
*/
//refactor了一下，不用这么多变量存，开始只是没有特别考虑清楚
public class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int maxJ = (n%2 == 0) ? n/2 : n/2+1;
        for(int i = 0; i < n/2; i ++) {
            for(int j = 0; j < maxJ; j ++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = tmp;
            }
        }
    }
}

//old:
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
