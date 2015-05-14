/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/*
这道题终于是自己想出来的一道题，肯定是用dp嘛
max = Maths.max(dp[0,i] + dp[i,n-1]) 0<i<n-1
然后想到两个arr，一个来存[0,i]的maxprofit，一个来存[i,n-1]的maxprofit
比如       [2,3,1,2,4,100,7]
fromFront: [0,1,1,1,3,99,6]
fromEnd:   [99,99,99,98,96,0,0]
然后两者加起来，取最大的那个，就是100
prices这个arr相当于扫描了三次，所以时间复杂度还是O(n)
*/
public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int max = 0;
        int len = prices.length;
        int[] fromFront = new int[len];
        int[] fromEnd = new int[len];
        
        fromFront[0] = 0; fromEnd[len-1] = 0;
        int minPrice = prices[0];
        for(int i  = 1; i < len; i++) {
            if(prices[i] > minPrice) {
                int p = prices[i] - minPrice;
                if(p > fromFront[i-1]) fromFront[i] = p;   
                else fromFront[i] = fromFront[i-1];       //要注意这里，第一次直接没判断，写成fromFront[i] = p;
            } else {
                fromFront[i] = fromFront[i-1];
                minPrice = prices[i];
            }
        }
        int maxPrice = prices[len-1];
        for(int i = len-2; i >= 0; i--) {
        	if(prices[i] > maxPrice) maxPrice = prices[i];
        	else {
        		int profit = maxPrice - prices[i];
        		if(profit > fromEnd[i+1]) fromEnd[i] = profit;
        		else fromEnd[i] = fromEnd[i+1];
        	}
        }
        for(int i = 0; i < len; i++) {
        	int tmp = fromFront[i] + fromEnd[i];
        	if(tmp > max) max = tmp;
        }
        return max;
    }
}
