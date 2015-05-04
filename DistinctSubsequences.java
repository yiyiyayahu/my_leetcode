/*
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/

public class Solution {
    public int numDistinct(String s, String t) {
        if(s == null || t == null) return 0;
        if(s.length() < t.length()) return 0;
        
        int slen = s.length(), tlen = t.length();
        int[][] d = new int[tlen][slen];
        
        for(int i = 0; i < tlen; i++) {
            for(int j = i; j < slen; j++) {
                if(t.charAt(i) == s.charAt(j)) {
                    if(i == 0 && j == 0) 
                        d[i][j] = 1;
                    else if(i == 0)
                        d[i][j] = d[i][j-1] + 1;
                    else
                        d[i][j] = d[i-1][j-1] + d[i][j-1];
                } else {
                    if(j == 0) d[i][j] = 0;
                    else d[i][j] = d[i][j-1];
                }
            }
        }
        return d[tlen-1][slen-1];
    }
}
