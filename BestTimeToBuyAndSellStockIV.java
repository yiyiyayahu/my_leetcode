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

还是很难想的诶！！！这个递推公式我就想不出。
明天写出来。然后想想，为什么把Max(f[i-1][k], f[j-1][k-1]+prices[i]-prices[j]))这个放在一起，然后f[i][k-1]单独拿出来

http://www.devhui.com/2015/02/23/Best-Time-to-Buy-and-Sell-Stock/
这篇博客总结的蛮好的
*/ 
