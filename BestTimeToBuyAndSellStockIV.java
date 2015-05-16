/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
*/

/*
唉，当年google的面试题，如果放到现在，至少我前三个理解透彻了。但是这个，说实话，还是想不粗，好难
比III多了一个at most k transactions
当时没有想清楚，肯定还是要dp，但是这种情况与其想着和III一样，maintain k个数组，不如想着变成二维dp来维护对吧
f[i][k]表示在前i天交易最多k次的maxprofit
f[i][k] = max |- f[i][k-1]
              |- max |- f[i-1][k]
                     |- f[j-1][k-1] + prices[i] - prices[j]   0<j<i
                     
f[i][k] = Max(f[i][k-1], Max(f[i-1][k], f[j-1][k-1]+prices[i]-prices[j]))
然后在算f[i][k]的过程中可以用一个变量来update f[j-1][k-1]-prices[j]的值

还要考虑一种情况，就是如果k>=prices.length的话（还是k>=N/2就行），和II是一样的，就是transaction数目没限制了
应该是k>=N/2就行了。因为transaction必然是有两个元素involve进去的。如果是i,i-1和i-1,i-2，那其实可以合成i和i-2嘛，就还是两个元素
那这样看的话，最多进行N/2次transaction

还是很难想的诶！！！这个递推公式我就想不出。
为什么把Max(f[i-1][k], f[j-1][k-1]+prices[i]-prices[j]))这个放在一起，然后f[i][k-1]单独拿出来
想了一下，应该是这样的：
f[i][k] ： 1）只进行k-1次交易
           2）进行k次交易：a) i is not involved b) i is involved: prices[i] - prices[j]

http://www.devhui.com/2015/02/23/Best-Time-to-Buy-and-Sell-Stock/
这篇博客总结的蛮好的
*/ 

/*
下面那个code TLE的原因是，里面那层算f[j-1][k-1]-prices[j]是不必要的，在扫描i的过程中，完全可以maintain这个值
所以其实O(kN)就可以解决的，而自己原来的做法要O(kN^2)
但是这道题maint这个maxCurr也出了一点问题，我开始外循环是i，内循环是j，fail的地方是
比如[6,1,3,2,4,7]到i==5，也就是7的这个位置的时候，这个maxCurr只算了f[i-2][j-1]-prices[i-1]，没有考虑i-1前面的
后来换了一下顺序就好了
唉，还是code写的太差，多多练习
*/
public class Solution {
    public int maxProfit(int k, int[] prices) {
            if(prices == null || prices.length == 0) return 0;
            
            int len = prices.length;
            if(k >= len/2) return profitNoLimit(prices);
                
            int[][] f = new int[len][k+1];
            
            for(int j = 1; j <= k; j++) {     
            	int maxCurr = 0 - prices[0];
            	for(int i = 1; i < len; i++) {
                   maxCurr = Math.max(maxCurr, i > 1 ? f[i-2][j-1]-prices[i-1] : 0-prices[i-1]);
                   int tmp = maxCurr + prices[i];
                   if(tmp < f[i-1][j]) tmp = f[i-1][j];
                   f[i][j] = Math.max(f[i][j-1], tmp); 
            	}
            }
            
            return f[len-1][k];
        }
        
        public int profitNoLimit(int[] prices) {
            int profit = 0;
            for(int i = 1; i < prices.length; i++) {
                if(prices[i] > prices[i-1]) {
                    profit += prices[i] - prices[i-1];
                }
            }
            return profit;
        }
}
/*
我觉得这个code应该是对的，但是TLE了
*/
public class Solution {
    public int maxProfit(int k, int[] prices) {
            if(prices == null || prices.length == 0) return 0;
            
            int len = prices.length;
            if(k >= len/2) return profitNoLimit(prices);
                
            int[][] f = new int[len][k+1];
            
            for(int i = 1; i < len; i++) {
            	for(int j = 1; j <= k; j++) {
            		int maxCurr = 0 - prices[0];
            		for(int m = i-1; m > 0; m--) {
            			maxCurr = Math.max(maxCurr, f[m-1][j-1] - prices[m]);
            		}
            		maxCurr = maxCurr + prices[i];
            		if(maxCurr < f[i-1][j]) maxCurr = f[i-1][j];
            		f[i][j] = Math.max(f[i][j-1], maxCurr); 
            	}
            }
            
            return f[len-1][k];
        }
        
        public int profitNoLimit(int[] prices) {
            int profit = 0;
            for(int i = 1; i < prices.length; i++) {
                if(prices[i] > prices[i-1]) {
                    profit += prices[i] - prices[i-1];
                }
            }
            return profit;
        }
}
