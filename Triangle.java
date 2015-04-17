/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

/*
一维数组的dp
我开始是这样想的，如果从上面往下面走，不是每一步的最小能保证整个路径最小，我还要记录是走到哪里了，这一层的这个值能到下面的哪里
但是这道题并不需要求出路径，只需要返回一个int，这种思路显然是不对的
然后想到了dp，但是不知道怎么做
那后来看提示，喔，原来可以换个思路，从下往上试试
比如4，1，8，3这一层推到上面的6，5，7，我知道下一层的哪些值可以指向他们
也就是dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1]) + A[i][j]
所以之后6，5，7就更新成7，6，10
        3，4   更新成  9，10
        2      更新成  11
但是其实这个二维数组是可以压缩成一维的，所以就变成了一维dp的问题
唉，真是没想到原来可以从下面往上面做的
*/

public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null) return 0;
        
        int size = triangle.size();
        int[] arr = new int[size];
        for(int i = size - 1; i >= 0; i--) {
            List<Integer> list = triangle.get(i);
            if(i == size - 1) {
                for(int j = 0; j < size; j++) {
                    arr[j] = list.get(j);
                }
            } else {
                for(int j = 0; j < list.size(); j++) {
                    arr[j] = Math.min(arr[j],arr[j+1]) + list.get(j);
                }
            }
        }
        return arr[0];
    }
}
