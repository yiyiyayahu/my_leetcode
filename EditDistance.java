/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/

/*
    1. 既然这道题只要求min distance，就不用把怎么转换的想到
    2. 那么应该用dp，因为考虑word1到word2的转化嘛，比如word1的前i位赚到word2的前j位，要如何recursive，那想到了二维数组
    3. 如果要用dp就要想到如何用前一种情况推出当前情况：如何用a[i-1][j], a[i][j-1], a[i-1][j-1]推出a[i][j]
       如果word1的第i位和word2的第i位相等，那么就是a[i-1][j-1]
       考虑hello->halle，最后一位o->e
       1）如果是replace，那就是我已知hell到hall的值-> a[i-1][j-1] + 1
       2) 如果是insert，那就是已知hello到hall，那么只要在最后一位insert一个e就可以了 -> a[i][j-1] + 1
       3) 如果是delete，那就是已知hell到halle，只要把最后的o delete掉 -> a[i-1][j] + 1
       综上，就是a[i][j] = Math.min(a[i-1][j-1] + 1, a[i][j-1] + 1, a[i-1][j] + 1); (if c1 != c2)
    4. dp的初始条件 （这个我开始就写错了）
       考虑到i-1 < 0, j-1 < 0的情况有点像是转化为空的string：
            e -> "" ea -> "" eat -> ""  s -> "" se -> "" sea -> ""
       其实想想哈，eat -> sea，到a[0][1]的时候，也就是e->se的时候，因为e和e相等，那要找的是a[i-1][j-1] 其实像是""到s的距离
          s  e  a             0 1 2 3
       e  1  1  2             1 1 1 2
       a      ...                    
       t      ...
       所以最后用的数组其实是(len1+1) * (len2+1)的
*/

public class Solution {
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null) return 0;
        
        int len1 = word1.length(), len2 = word2.length();
        if(len1 == 0) return len2;
        if(len2 == 0) return len1;
        int[][] dist = new int[len1 + 1][len2 + 1];
        
        for(int i = 0; i <= len1; i++) dist[i][0] = i;
        for(int j = 0; j <= len2; j++) dist[0][j] = j;
        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                char c1 = word1.charAt(i-1);
                char c2 = word2.charAt(j-1);
                
                if(c1 == c2) dist[i][j] = dist[i-1][j-1];
                else {
                    int tmp1 = dist[i-1][j-1] + 1; //replace
                    int tmp2 = dist[i][j-1] + 1; //insert
                    int tmp3 = dist[i-1][j] + 1; //delete
                    dist[i][j] = Math.min(tmp1, tmp2);
                    dist[i][j] = Math.min(dist[i][j], tmp3);
                }
            }
        }
        return dist[len1][len2];
    }
}
