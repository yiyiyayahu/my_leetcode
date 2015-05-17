/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
*/

/*
这道题O(n)的解法特别特别不好想，我是看了这个文章
http://www.cnblogs.com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html

思路是这样的：
1. stack里面存递增的index
[2,1,5,6,2,3]
这样做的原因是，比如i=4，也就是到2的位置，它不可能和前面的组成很大的，除非是以长度取胜
所以前面的那部分就可以算一个值，然后把stack清空重新开始算
i=0: stack是空的，就把0放进去
i=1: 这个时候遇到小的元素了，stack.pop()，largest里面应该存2*1=2，这个时候stack空了，应该把1放进去
i=2: 递增，放入2
i=3: 递增，放入3
i=4: 元素比stack.peek()小了，然后一点点pop
     先弹出index=3，算面积6*1=6
     再弹出index=2，算面积5*2=10


小trick：
可能到最后一个元素的时候，stack里面还有值，这样还要算一下，但是算的方法和前面一样。
所以可以copy一个数组，只是在height.length这个地方放一个0，这样就可以像之前那样，让stack里面的元素都pop出来
这里不知道leetcode为什么int[] h = Arrays.copyOf(height, height.length + 1);就会出TLE，在数组很长并且都是1的情况下
一定要让我把h初始化一下
*/
public class Solution {
    public int largestRectangleArea(int[] height) {
        if(height == null) return 0;
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, height.length + 1);
        
        Stack<Integer> stack = new Stack<Integer>();
        int largest = 0;
        for(int i = 0; i < h.length; ) {
            if(stack.isEmpty() || h[i] >= h[stack.peek()]) {
                stack.push(i++);
            } else {    
                int index = stack.pop();
                int tmp = h[index] * (stack.isEmpty() ? i : i-stack.peek()-1);
                largest = Math.max(largest, tmp);
            }
        }
        return largest;
    }
}
