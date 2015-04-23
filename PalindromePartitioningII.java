/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

/*
这种题，只要返回一个integer，就不用想着怎么样去cut
然后求最优解的这种题，可以想想dp
但是递推公式怎么算，这个我当时没想出来
定义函数
D[i,n] = 区间[i,n]之间最小的cut数，n为字符串长度

 a   b   a   b   b   b   a   b   b   a   b   a
                     i                       n
如果现在求[i,n]之间的最优解？应该是多少？简单看一看，至少有下面一个解


 a   b   a   b   b   b   a   b   b   a   b   a
                     i       j   j+1         n

此时  D[i,n] = min(D[i, j] + D[j+1,n])  i<=j <n。这是个二维的函数，实际写代码时维护比较麻烦。所以要转换成一维DP。
如果每次，从i往右扫描，每找到一个回文就算一次DP的话，就可以转换为
D[i] = 区间[i,n]之间最小的cut数，n为字符串长度， 则,
D[i] = min(1+D[j+1](if str(i-j) is palindrome), D[i])    i<=j <n

然后就要像，如何判断str(i-j) is palindrome
还是DP：二维数组
定义函数
P[i][j] = true if [i,j]为回文
那么
P[i][j] = str[i] == str[j] && (P[i+1][j-1] || j - i < 2);


啊，好难想啊，其实我还有一点没有相通。。。为什么cuts是int[slen + 1]; 然后返回值是cuts[0]-1啊
这个再想想
*/
public class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0) return 0;
        
        int slen = s.length();
        int[] cuts = new int[slen + 1];
        boolean[][] p = new boolean[slen][slen];
        
        //initiate: worst case: cut by letter
        for(int i = 0; i <= slen; i++) {
            cuts[i] = slen - i;
        }
        
        for(int i = slen - 1; i >= 0; i--) {
            for(int j = i; j < slen; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    if(j - i < 2 || p[i+1][j-1] == true) {
                        p[i][j] = true;
                        cuts[i] = Math.min(cuts[i], cuts[j+1] + 1);
                    }
                }
            }
        }
        return cuts[0] - 1;
    }
}
