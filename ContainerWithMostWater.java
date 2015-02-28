/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/

public class Solution {
    
    public int maxArea(int[] height) {
		if(height == null || height.length == 0) return 0;
        int i = 0, j = height.length - 1;
        int max = 0, tmp = 0;
        while(i < j) {
            tmp = Math.min(height[i], height[j]) * (j - i);
            if(tmp > max) max = tmp;
            if(height[i] < height[j]) {
                i ++;
            } else {
                j --;
            }
        }
        return max;
    }
}

/*
开始想的是DP，就是max = Math.max{max(n-1), (for i=0;i<n-1;i++)Math.min(a_i,a_n-1)*(n-i-1)}
后来觉得不对呀，这个比的次数也太多了。但是又想不出什么好方法。

这个解法是网上看来的。。。汗
就是当左边的height比右边的大的时候，左边的i就前移一位，反之，右边的j就往里面移一位
原理是：当height[i]比较小的时候，那在jj<j这个区间里面，没有比当前更大的container了，所以这个时候j--没有作用
同理，当height[j]比较小的时候，i++也没有作用，但是如果j--可能就会发现更大的container
*/
