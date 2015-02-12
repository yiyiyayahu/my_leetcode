/*
Write a function to find the longest common prefix string amongst an array of strings.
*/

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        
        String common = getCommonPrefix(strs[0],strs[1]);
        for(int i = 2; i < strs.length; i++) {
            common = getCommonPrefix(common, strs[i]);
        }
        return common;
    }
    
    public String getCommonPrefix(String s1, String s2) {
        if(s1 == null || s2 == null) return null;
        StringBuilder sb = new StringBuilder();
        int len1 = s1.length();
        int len2 = s2.length();
        int len = len1 < len2 ? len1 : len2;
        
        for(int i = 0; i < len; i++) {
            if(s1.charAt(i) == s2.charAt(i) ) {
                sb.append(s1.charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
