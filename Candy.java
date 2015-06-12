/*
 There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

    Each child must have at least one candy.
    Children with a higher rating get more candies than their neighbors.

What is the minimum candies you must give? 
*/

/*
我觉得这道题挺难的，虽然是dp吧，但是要考虑下面这几种情况
比如candy[i]表示在i这个点的candy的个数
http://fisherlei.blogspot.com/2013/11/leetcode-candy-solution.html
水中的鱼里面的配图特别好

如果这样想：
if(candy[i] > candy[i-1) candy[i] = candy[i-1] + 1
else if(candy[i] == candy[i-1) candy[i] = 1
else candy[i] = candy[i-1] - 1
但是有几个问题
比如ratings是[1,4,3,2,1,2]这种的
这样做出来的就是[1,2,1,0,-1,0]，显然是invalid的，应该转成[1,4,3,2,1,2]
另外一种是：[1,2,3,4.3,2,3]
这样做出来的是[1,2,3,4.3,2,3]，但是其实这样就多了，应该是[1,2,3,4.2,1,2]

但是如何解决这个问题呢，我开始想的是，只要找到这个1的位置就好了。但是怎么找也成了问题
后来看人家的blog说可以像trapping rain water那样，第一次从左往右，第二次从右往左
这样就保证了，最小的那里总是1，既不会出现<0，也不会>1
很巧妙诶，唉，这种题自己总是做不出
不晓得是不是左右边界有关联的都可以扫描两次
*/
public class Solution {
    public int candy(int[] ratings) {
        int result = 0;
        int len = ratings.length;
        int[] candy = new int[len];

        candy[0] = 1;
        for(int i = 1; i < len; i++) {
            if(ratings[i] > ratings[i-1]) candy[i] = candy[i-1]+1;
            else candy[i] = 1;
        }
        result = candy[len-1];
        for(int i = len-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]) candy[i] = Math.max(candy[i], candy[i+1]+1);
            result += candy[i];
        }
        return result;
    }
}
