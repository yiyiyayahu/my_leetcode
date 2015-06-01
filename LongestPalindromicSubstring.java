/*
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
*/

/*
dp的方法OK的，时间复杂度是O(n^2)空间也是O(n^2)，和找到一个center找周围的差距在于空间上
所以之前是自己代码的问题
*/
public class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return s;
        int len = s.length();
        boolean[][] d = new boolean[len][len];
        String result = "";
        int maxLen = 0;
        
        for(int i = len-1; i >= 0; i--) {
            for(int j = i; j < len; j++) {
                if(s.charAt(i) == s.charAt(j) && (j-i<=2 || d[i+1][j-1]== true)) {
                    d[i][j] = true;
                    if(j - i >= maxLen) {
                    	result = s.substring(i, j+1);
                    	maxLen = j - i + 1;
                    }
                } 
            }
        }
        return result;
    }
}
/*
我想的是dp，但是会出现TLE
思路是：
1) d[i][i] = true
2) d[i][i+1] = true if(s[i] == s[i+1])
3) d[i][j] = true if(s[i]==s[j] && d[i+1][j-1]==true)
*/
public class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return s;
        int len = s.length();
        boolean[][] d = new boolean[len][len];
        String result = "";
        int maxLen = 0;
        
        for(int i = len-1; i >= 0; i--) {
            for(int j = i; j < len; j++) {
                if(i == j) d[i][j] = true;
                else if(j == i+1 && s.charAt(i) == s.charAt(j)) d[i][j] = true;
                else {
                    if(i + 1 >= len || j - 1 <= 0) continue;
                    if(s.charAt(i) == s.charAt(j) && d[i+1][j-1]== true) {
                        d[i][j] = true;
                    } 
                }
                if(d[i][j] == true && j - i >= maxLen) {
                	result = s.substring(i, j + 1);
                	maxLen = j - i;
                }
             }
        }
        return result;
    }
}
