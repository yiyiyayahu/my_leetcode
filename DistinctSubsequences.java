/*
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/

/*
开始用一维数组的dp发现不行
原来这种题其实有点类似convert s to t要几步的那种，要用二维数组
之后就是递推公式的问题了
然后之后我又理解错了，比如"aceace" "ace"我以为应该返回2，就是选第一个ace还是第二个ace。
但实际上应该返回4啊。123,456,126,156这四种组合

好，关于递推公式：
i: t的index（短的）
j: s的index
d[i][j]就是s的前j个string里面有多少个distinct subsequences of t的前i个substring
if(t[i] != s[j]) d[i][j] = d[i][j-1] 就是这个二维数组的第i行，如果不等的话，其实就和前面的那个数字结果一样
else            d[i][j] = d[i][j-1] + d[i-1][j-1] (这里肯定不能单纯加1啊，但是加什么呢, 就是s的前j-1个找到了多少t的前i-1个) 
比如rab，rabb：rab里面找到了一个rab，rab里面找到了一个ra，那么rabb里面就能找到1+1=2个rab
    rabb, rabbb: rabb里面找到了一个rabb， rabb里面找到了两个rab，总共就是3个
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
