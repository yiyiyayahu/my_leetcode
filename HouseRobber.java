/*
You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that 
adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken 
into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.
*/

/*
很明显一道dp的题，我开始还是想recursive的调。。。思维定式吧。其实用数组很快的，code也很简洁
像咩咩说的，之前想好各种可能出现的情况再开始写就快多了
可能这道题比较简单，总之写的很快，而且一次就过了。还是要给自己一个小奖励的，嘻嘻
*/
public class Solution {
    public int rob(int[] num) {
        if(num == null) return 0;
        int len = num.length;
        if(len == 0) return 0;
        if(len == 1) return num[0];
        
        int[] result = new int[len];
        result[0] = num[0];
        result[1] = Math.max(num[0], num[1]);
        
        for(int i = 2; i < len; i++) {
            result[i] = Math.max(result[i-2] + num[i], result[i-1]);
        }
        return result[len-1];
    }
}
