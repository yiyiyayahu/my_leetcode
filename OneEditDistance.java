/*
Given two strings S and T, determine if they are both one edit distance apart.
*/

/*
这样code就简洁一些了，但是网上的比我写的还简洁
*/

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if(s == null || t == null) return false;

        if(s.length() > t.length()) return helper(t, s);
        else return helper(s, t);
    }
    
    public boolean helper(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        
        if(len2 - len1 > 1) return false;
        
        boolean isOneDiff = false;
        for(int i = 0, j = 0; i < len1; i++, j++) {
            if(s.charAt(i) != t.charAt(j)) {
                if(isOneDiff) return false;
                isOneDiff = true;
                if(len1 < len2) i --;
            }
        }
        return isOneDiff || (len1 < len2);
    }
}


public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if(s == null || t == null) return false;

        if(s.length() > t.length()) return helper(t, s);
        else return helper(s, t);
    }
    
    public boolean helper(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        
        if(len2 - len1 > 1) return false;
        
        boolean isOneDiff = false;
        for(int i = 0, j = 0; i < len1; i++, j++) {
            if(s.charAt(i) != t.charAt(j)) {
                if(isOneDiff) return false;
                isOneDiff = true;
                if(len1 < len2) {
                	j ++;           	
                	if(j < len2 && s.charAt(i) != t.charAt(j)) return false;
                }
            }
        }
        if(!isOneDiff && len1 < len2) return true;
        if(isOneDiff) return true;
        else return false;
    }
}
/*
code写的特别乱啊。要refactor一下
s.substring(0,i-1).equals(t.substring(0,i-1)这里又忘记先检查是不是i>1了
*/
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if(s == null || t == null) return false;
        
        int len1 = s.length(), len2 = t.length();
        if(Math.abs(len1 - len2) > 1) return false;
        if(len1 == 0 && len2 == 1) return true;
        if(len1 == 1 && len2 == 0) return true;
        
        int minLen = Math.min(len1, len2);
        if(len1 == len2) {
        	int diff = 0;
        	for(int i = 0; i < len1; i++) {
        		if(s.charAt(i) == t.charAt(i)) continue;
        		else diff ++;
        	}
        	if(diff != 1) return false;
        	else return true;
        } else {

        	if(s.substring(0, minLen).equals(t.substring(0, minLen))) return true;
        	for(int i = 0; i < minLen; i++) {
        		if(s.charAt(i) == t.charAt(i)) continue;
        		else {
        			if(i > 1 && !s.substring(0,i-1).equals(t.substring(0,i-1))) return false;
        			if(len1 > len2) return s.substring(i+1,len1).equals(t.substring(i,len2));
        			else return s.substring(i,len1).equals(t.substring(i+1,len2));
        		}
        	}
        }
        return false;
    }
}
