/*
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/
//这道题可能因为是n*n的，所以就不存在I里面那些问题，就很好写
public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        int num = n * n;
        int elem = 1, i = 0, j = 0;;
        int start = 0, end = n-1;
        while(start <= end && elem <= num) {
        	if(start == end) {
        		matrix[i][j] = elem ++;
        		break;
        	}
        	while(j < end) {
        		matrix[i][j] = elem ++;
        		j ++;
        	}
        	while(i < end) {
        		matrix[i][j] = elem ++;
        		i ++;
        	}
        	while(j > start) {
        		matrix[i][j] = elem ++;
        		j --;
        	}
        	while(i > start) {
        		matrix[i][j] = elem ++;
        		i --;
        	}
            i ++; j ++;
            start ++; end --;
        }
        
        return matrix;
    }
}
