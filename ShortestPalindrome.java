/*
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".
*/

/*
经典做法貌似要用KMP algorithm, 就是s先reverse一下，然后把s和reverse后的string用#号连起来，然后KMP找match
但是我没太弄懂，明天再看看
用的另外一种和longest palindrome比较像的从中间往两边找的方法。但是中间可能是一个也可能是两个
*/
public class Solution {
    public String shortestPalindrome(String s) {
        if(s == null || s.length() <= 1) return s;
        int len = s.length();       
        int mid = len/2+1>=len?len/2:len/2+1;
        String ret = null;
        for(int i = mid; i > 0; i--) {
        	if(s.charAt(i) == s.charAt(i-1)) {
        		if((ret=helper(s, i-1, i)) != null) 
        			return ret;
        	} else {
        		if((ret=helper(s, i-1, i-1)) != null) 
        			return ret;
        	}       	
        }
        return ret;
    }
    public String helper(String s, int left, int right) {
    	while(left >= 0 && right < s.length() ) {
    		if(s.charAt(left) != s.charAt(right)) 
    			break;
    		else {
    			left --;
    			right ++;
    		}
    	}
    	if(left >= 0) return null;
    	StringBuilder sb = new StringBuilder(s.substring(right));
    	sb.reverse();
    	return sb.append(s).toString();
    }   
}
