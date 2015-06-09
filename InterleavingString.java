/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
*/

/*
一道dp的题，是自己做出来的
用了个二维数组，多加了一行一列作为base case
其实可以用两个一维数组节约空间的，有时间想着再优化下
*/
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if(len3 != len1+len2) return false;
        
        boolean[][] dp = new boolean[len1+1][len2+1];
        
        dp[0][0] = true;
        for(int i = 1; i <= len1; i++) {
            if(s3.charAt(i-1) == s1.charAt(i-1)) dp[i][0] = dp[i-1][0];
        }
        
        for(int j = 1; j <= len2; j++) {
             if(s3.charAt(j-1) == s2.charAt(j-1)) dp[0][j] = dp[0][j-1];
        }
        
        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                char c3 = s3.charAt(i+j-1);
                if(c3 == s1.charAt(i-1) && dp[i-1][j]) dp[i][j] = true;
                else if(c3 == s2.charAt(j-1) && dp[i][j-1]) dp[i][j] = true;
            }
        }
        return dp[len1][len2];
    }
}
