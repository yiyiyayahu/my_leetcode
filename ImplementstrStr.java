/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/


public class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack == null || needle == null) return -1;
        
        int h_len = haystack.length(); 
        int n_len = needle.length();
        
        if(h_len < n_len) return -1;
        if(n_len == 0) return 0;
        
        for(int i = 0; i < h_len; i++) {
            if( haystack.charAt(i) == needle.charAt(0) ) {
                int tmp = i; int j = 0;
                if(h_len - i + 1 > n_len) {
                    while(tmp < h_len && j < n_len && haystack.charAt(tmp) == needle.charAt(j) ) {
                        tmp++;
                        j++;
                    }
                    if(j == n_len) return i;
                }
            } 
        }
        return -1;
    }
}
