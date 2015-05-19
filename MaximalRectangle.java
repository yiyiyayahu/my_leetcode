/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
*/

/*
参考largest rectangle，每一层其实都可以转换为largest rectangle，然后计算，再算下一层
要注意matrix.length==0的时候要返回0，matrix=[]的话，在int cols = matrix[0].length;会出错
时间复杂度是O(m*n)，空间复杂度是O(n)
*/
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int[] arr = new int[cols];
        int maxRec = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == '1') {
                    arr[j] += 1;
                } else {
                    arr[j] = 0;
                }
            }  
            maxRec = Math.max(maxRec, helper(arr));
        }
        return maxRec;
    }
    
    public int helper(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] arr = new int[nums.length + 1];
        arr = Arrays.copyOf(nums, nums.length+1);
        
        Stack<Integer> stack = new Stack<Integer>();
        int ret = 0;
        for(int i = 0; i < arr.length; ) {
            if(stack.isEmpty() || arr[i] >= arr[stack.peek()]) {
                stack.push(i++);
            } else {
                int index = stack.pop();
                int tmp = arr[index] * (stack.isEmpty() ? i : i - stack.peek() - 1);
                ret = Math.max(ret, tmp);
            }
        }
        return ret;
    }
}
