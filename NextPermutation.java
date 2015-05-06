/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

/*
这道题自己想不出，看了答案的，而且code应该还要refactor一下
http://fisherlei.blogspot.com/2012/12/leetcode-next-permutation.html
http://blog.csdn.net/linhuanmars/article/details/20434115

1) 先从后往前找到第一个不是依次增长的数，记录下位置p。比如例子中的3，对应的位置是1;
2) 接下来分两种情况：
    (1) 如果上面的数字都是依次增长的，那么说明这是最后一个排列，下一个就是第一个，其实把所有数字反转过来即可
    (比如(6,5,4,3,2,1)下一个是(1,2,3,4,5,6));
    (2) 否则，如果p存在，从p开始往后找，找到下一个数就比p对应的数小的数字，然后两个调换位置，比如例子中的4。
    调换位置后得到(2,4,6,5,3,1)。最后把p之后的所有数字倒序，比如例子中得到(2,4,1,3,5,6), 这个即是要求的下一个排列。
以上方法中，最坏情况需要扫描数组三次，所以时间复杂度是O(3*n)=O(n)，空间复杂度是O(1)。
*/

public class Solution {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length == 0) return;
        
        int n = nums.length;
        int p = Integer.MAX_VALUE;
        int index = -1;        
       
        for(int i = n-2; i >= 0; i--) {
            if(nums[i] < nums[i+1]) {
                p = nums[i];
                index = i;                
                break;
            }
        }
        if(p == Integer.MAX_VALUE) {
            index = -1;
        }
        
        for(int i = n - 1; i > index; i--) {
            if(nums[i] > p) {
                nums[index] = nums[i];
                nums[i] = p;
                break;
            }
        }
        reverseArr(nums, index+1, n-1);
    }
    
    public void reverseArr(int[] nums, int start, int end) {
    	int diff = (end - start)/2;
    	for(int i = 0; i <= diff; i++) {
    		int tmp = nums[end - i];
    		nums[end - i] = nums[start + i];
    		nums[start + i] = tmp;
    	}
    }
}
