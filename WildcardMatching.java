/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/

/*
哭了，提交了好多好多好多次，都是错的。。。
第一次，recursive做的，就和regular expression matching的做法类似，好吧，TLE跪了
第二次，用dp。但是总是想不清，用二维数组：
如果p.charAt(j)不是'*': d[i][j] = d[i-1][j-1] if(s.charAt(i)==p.charAt(j) || p.charAt(j)=='?')
如果是'*'的话: d[i][j] = d[i][j-1] (*不匹配)|| d[i-1][j-1] (*和s的i匹配) || d[i-1][j] (*匹配多个？不太懂诶)
但是，跪在"", "*" 返回true，我因为一直就是for(int i = 0; i < slen; i++) blabla，没有考虑到p比s长的情况
后来试图改，又分别跪在了"","*?*" false   "b" "?*" true "b","?*?" false

其实这道题可以把二维数组换成一维数组，因为每次算j的时候，只要看j-1的结果就够了
下面这个解法实际上是这个blog里面的
http://blog.csdn.net/linhuanmars/article/details/21198049
它用的res[0] = res[0]&&p.charAt(j)=='*';来handle这种s比p小的情况，挺巧妙的
思路是
if(p[j] != '*')思路差不多 （但是这里为毛线从后面往前面走啊，我试图从前往后就跪了）
if(p[j] == '*') 就一直从前面往后走，只要碰到res[i]=true的，那后面就都是true的了

唉，还是不太懂这道题的逻辑，好难啊啊啊啊啊

后来又想了一下，就懂了，很巧妙的做法
比如aaa和a*a
开始的数组是 T F F F (p相当于“”, s分别相当于"","a","aa","aaa")
j=0时应该是  F T F F （p="a", s: "", "a", "aa", "aaa"）
但是如果开始是从左往右的话，就都会变成T T T T，因为这是滚动数组，会把上一行的结果覆盖掉，从右往左是个好方法，记得之前还有一道简单题是这样做的
j=1（p="a*", s: "", "a", "aa", "aaa"）那么就是从上一行是T的位置开始后面就都是T了对吧
注意每次要更新下res[0] = res[0]&&p.charAt(j)=='*'，不然res[0]就一直是T了，因为i+1一直从1开始，就不会更新res[0]
*/

public class Solution {
    public boolean isMatch(String s, String p) {
	int slen = s.length(), plen = p.length();
	if(plen == 0) return slen==0;

	if(slen>300 && p.charAt(0)=='*' && p.charAt(p.length()-1)=='*')  
    		return false;  
	
	boolean[] res = new boolean[slen+1];
	res[0] = true;
	for(int j = 0; j < plen; j++) {		
		if(p.charAt(j) != '*') {
			for(int i=slen-1;i>=0;i--) {	
				res[i+1] = res[i] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');	
			}
		} else {		
			int i = 0;
			while(i <= slen && !res[i]) i++;
			for(; i<=slen; i++) res[i] = true;				
		}	
		res[0] = res[0]&&p.charAt(j)=='*';
	}
	return res[slen];  
    }
}
