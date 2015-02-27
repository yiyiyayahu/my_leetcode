/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
*/

//二分法，但是把这个二维数组转成一维的了，不晓得行不行，时间复杂度是O(log(nm))
public class Solution {
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0) return false;
		
        int rows = matrix.length, cols = matrix[0].length;
		return searchMatrixHelper(matrix, target, 0, rows * cols - 1);
	}
	
	public boolean searchMatrixHelper(int[][] matrix, int target, int start, int end) {
		if(start > end) return false;
		int mid = (start + end)/2;
		int i = mid / matrix[0].length;
		int j = mid % matrix[0].length;
		if(target > matrix[i][j]) return searchMatrixHelper(matrix, target, mid + 1, end);
		else if(target < matrix[i][j]) return searchMatrixHelper(matrix, target, start, mid - 1);
		else return true;
	}
}

//previous 时间复杂度是O(nm)
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        if(target < matrix[0][0] || target > matrix[rows-1][cols-1]) return false;
        
        for(int i = 0; i < rows; i++) {
            if(target == matrix[i][0]) return true;
            
	        if(target > matrix[i][0]) {
	        	if(i < rows - 1 && target < matrix[i+1][0] || i == rows - 1) {
	                if(target > matrix[i][cols-1]) return false;
	                if(target == matrix[i][cols-1]) return true;
	                for(int j = 0; j < cols - 1; j++) {
	                    if(target == matrix[i][j]) return true;
	                }
	            } 
	        }
        }
        return false;
    }
}

