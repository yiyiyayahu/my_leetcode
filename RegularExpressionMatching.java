/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

*/

public class Solution {
    public boolean isMatch(String s, String p) {
        if(p.length() == 0) return s.length() == 0;

        if(p.length() == 1) {
            if(s.length() == 1) {
                if(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
                    return true;
            }
            return false;
        }

        if(p.charAt(1) != '*') {
            if(s.length() < 1) return false;

            if(s.charAt(0) != p.charAt(0) && p.charAt(0) != '.')
                return false;

            return isMatch(s.substring(1), p.substring(1));
        } else {
            //no match
            if(isMatch(s, p.substring(2))) return true;

            //match 1 or more
            int i = 0;
            while(i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.') ) {
                if(isMatch(s.substring(i+1), p.substring(2))) return true;
                i++;
            }

        }
        return false;
    }
}
