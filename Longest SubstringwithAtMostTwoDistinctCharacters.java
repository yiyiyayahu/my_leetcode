/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
*/

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() == 0) return 0;
        
        int len = s.length();
        
        int result = 0;
        for(int i = 0; i < len; i++) {
            char curr = s.charAt(i);
            int j = i + 1, tmp = 1, numOfLetters = 1;
            char next = curr;
            
            while(j < len && numOfLetters <= 2) {
                if(s.charAt(j) != next && s.charAt(j) != curr) {
                    numOfLetters ++;
                    if(s.charAt(j) != next) next = s.charAt(j);
                } 
                if(numOfLetters <= 2) {
                	tmp ++;
                	j ++;
                }
            }
            if(tmp > result) result = tmp;
        }
        return result;
    }
}
