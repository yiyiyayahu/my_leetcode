/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/
   
/*
这个解法显然是不好的，上面的array是sorted，而且还只rotate一次，要改进
这道题应该和Search in Rotated Sorted Array这个差不多
*/
public class Solution {
    public int findMin(int[] num) {
        if(num == null || num.length == 0) return Integer.MIN_VALUE;
        
        int min = num[0];
        
        for(int i = 1; i < num.length; i++) {
            if(num[i] < num[i-1]) {
                if(num[i] < min) min = num[i];
            }
        }
        return min;
    }
}
