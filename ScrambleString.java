/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/

/*
只能用递归了
不能过的case比如abcd和bdac
base case的时候其实(s1.length() <= 3)就是true的了
但是要考虑s1 1+7 s2 1+7/7+1
就是有可能s1的第1位对应的是s2的第7位
*/
public class Solution {
    public boolean isScramble(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        int len = s1.length();
        int[][] arr = new int[26][1];
        for(int i = 0; i < len; i++) {
            int index = s1.charAt(i) - 'a';
            arr[index][0] ++;
        }
        for(int i = 0; i < len; i++) {
            int index = s2.charAt(i) - 'a';
            if(arr[index][0] <= 0) return false;
            arr[index][0] --;
        }
        if(s1.equals(s2)) return true;
        if(len <= 3) return true;
        
        for(int i = 1; i < len; i++) {
        	String first1 = s1.substring(0,i);
        	String second1 = s1.substring(i);
        	        	
        	String first2 = s2.substring(0, i);
        	String second2 = s2.substring(i);
        	
        	String first22 = s2.substring(len-i);
        	String second22 = s2.substring(0, len-i);
        	
        	if(isScramble(first1, first2) && isScramble(second1, second2)) return true;
        	if(isScramble(first1, first22) && isScramble(second1, second22)) return true;
        }
        
        return false;
    }
}
