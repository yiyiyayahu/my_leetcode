/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/

/*
这道题我觉得应该是思路不难，但是要考虑的地方比较多的那种
我提交了好多次才过。。。囧
主要问题在于[1,2,3]，如果我没有加 if(r_start == r_end && j == c_end) break; 这句话的话返回的是1,2,3,2
还有数组最要考虑的就是array index是不是OutOfBound
*/
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0) return list;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int i = 0, j = 0;
        int r_start = 0, r_end = m-1;
        int c_start = 0, c_end = n-1;
        
        while(r_start <= r_end && c_start <= c_end) {
        	
            if(r_start == r_end && c_start == c_end) {
            	list.add(matrix[r_start][c_start]);
            }
            while(j < c_end) {
                list.add(matrix[i][j]);
                j++;
            }
            
            while(i < r_end) {
                list.add(matrix[i][j]);
                i++;
            }
            
            while(j > c_start) {
                list.add(matrix[i][j]);
                if(r_start == r_end && j == c_end) break;
                j --;
            }
            
            while(i > r_start) {
                list.add(matrix[i][j]);
                if(c_start == c_end && i == r_end) break;
                i --;
            }
            
            i ++; j ++;
            
            r_start ++; r_end --;
            c_start ++; c_end --;
        }
       
        return list;
    }
}
