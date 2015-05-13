/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction 
(ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
*/

/*
开始想的是arr[i] = max(prices[i] - prices[j]) 0 <= j < i, if prices[i] > prices[j]
但是这样就是两个循环，时间复杂度是O(n^2)
其实完全不用，只要maintain一个min的值就行了，[5,4,3,9,2]，开始min=5，然后指针后移的过程中更新这个min，
最后的max就是从当前值减去它之前最小的那个这些里面取最大的，这样就是O(n)
*/
public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        
        int max_profit = 0;
        int low = prices[0];
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < low) {
                low = prices[i];
            } 
            if( (prices[i] - low) > max_profit) {
                max_profit = prices[i] - low;
            }
        }
        return max_profit;
    }
}
