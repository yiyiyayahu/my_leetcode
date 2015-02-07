/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/

public class Solution {
    public boolean isPalindrome(String s) {
        if(s==null || s.length() == 0) return true;
        
        int i = 0;
        int j = s.length() - 1;
        
        while(i <= j) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            
            if(!isAlpha(c1)) { i++; continue; }
            if(!isAlpha(c2)) { j--; continue; }
            
            else {
                if(c1 == c2 || c1 == c2 + 32 || c1 == c2  - 32) {
                    i ++; 
                    j --;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isAlpha(char c) {
    	  if(c >= '0' && c <= '9') return true;
        if(c >= 'a' && c <= 'z') return true;
        if(c >= 'A' && c <= 'Z') return true;
        return false;
    }
}
