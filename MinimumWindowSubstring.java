/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/


/*开始用HashSet，后来发现T中的字母可以是重复的
改用HashMap之后会出现TLE
*/

public class Solution {
    public String minWindow(String S, String T) {
        if(S == null || T == null || S.length() < T.length()) return "";
        
        StringBuilder result = new StringBuilder();
        HashSet<Character> set = new HashSet<Character>();
        
        for(int i = 0; i < T.length(); i++) {
            set.add(T.charAt(i));
        }
        int slen = S.length();
        for(int i = 0; i < slen; i++) {
            char curr = S.charAt(i);
            
            if(set.contains(curr)) {
                HashSet<Character> tmpSet = new HashSet<Character>(set);
                tmpSet.remove(curr);
                int j = i + 1;
                StringBuilder tmpsb = new StringBuilder();
                tmpsb.append(curr);
                while(j < slen && !tmpSet.isEmpty())  {
                    char c = S.charAt(j);
                    tmpsb.append(c);
                    if(!tmpSet.isEmpty() && tmpSet.contains(c)) {
                        tmpSet.remove(c);
                    }
                    j++;
                }
                if(tmpSet.isEmpty()) {
                	if(result.length() == 0 || tmpsb.length() < result.length() ) {
                    	result = tmpsb;
                	} 
                } 
            }
        }
        return result.toString();
    }
}
