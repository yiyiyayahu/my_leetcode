/*
 Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence 
 of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code". 
*/

/*
很明显的dp题。递推公式是：
d[i]代表s的[0,i]是否可以wordbreak
d[i] = true if: 1) s.substring(0,i+1) 在dict里面
                2) 存在这样一个j (0,i)区间内，d[j]==true并且s.substring(j+1,i+1)在dict里面
otherwise d[i]=false
*/
public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        if(s == null || s.length() == 0) return false;
        if(wordDict == null || wordDict.size() == 0) return false;
        int len = s.length();
        boolean[] d = new boolean[len];
        for(int i = 0; i < len; i++) {
            String s1 = s.substring(0, i+1);
            if(wordDict.contains(s1)) d[i] = true;
            else {
                for(int j = 0; j < i; j++) {
                    if(d[j] == true && wordDict.contains(s.substring(j+1,i+1))) 
                        d[i]=true;
                }
            }
        }
        return d[len-1];
    }
}
