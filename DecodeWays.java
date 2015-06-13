/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

/*
唉，终于做完了，自己还是好多情况考虑不周啊
开始直接recursive来着，果断TLE，囧
之后用dp
我是这样想的：dp[i]表示s的前i个字母能组成多少个
dp[i] = dp[i-1] (如果i和i-1组合起来的字母超过26了)
dp[i] = dp[i-1]+dp[i-2] (如果和i-1可以组合起来)

但是但是: 我没有考虑到这么几种情况：
1. string是invalid的，比如0,100,1270这种，怎么都组合不起来的，要返回0
2. 还有就是10，120这种的，遇到0只能和前面组合的，不能再+1了

总之就是0惹的祸！！！
*/
public class Solution {
    public int numDecodings(String s) {
    	if(s == null || s.length() == 0) return 0;
    	if(s.startsWith("0")) return 0;
    	int len = s.length();
    	int[] dp = new int[len];    	
    	dp[0] = 1;
    	
    	for(int i = 1; i < len; i++) {   
    		String sub = s.substring(i-1,i+1);
    		if(s.charAt(i) == '0' && (sub.compareTo("1") < 0 || sub.compareTo("26") > 0)) {
    			return 0;
    		} 
    		if(s.charAt(i-1) != '0' && sub.compareTo("26") <= 0) {
    			if(s.charAt(i) == '0') dp[i] = i>=2 ? dp[i-2] : 1;
    			else dp[i] = i>=2 ? dp[i-1]+dp[i-2] : dp[i-1]+1;
    		} else {
    			dp[i] = dp[i-1];
    		}
    	}
    	return dp[len-1];
    }
}
