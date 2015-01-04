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


/*
Solution:
O(nm) runtime, O(1) space – Brute force:

You could demonstrate to your interviewer that this problem can be solved using known efficient algorithms such as Rabin-Karp algorithm, KMP algorithm, and the Boyer- Moore algorithm. Since these algorithms are usually studied in an advanced algorithms class, it is sufficient to solve it using the most direct method in an interview – The brute force method.

The brute force method is straightforward to implement. We scan the needle with the haystack from its first position and start matching all subsequent letters one by one. If one of the letters does not match, we start over again with the next position in the haystack.

The key is to implement the solution cleanly without dealing with each edge case separately.
*/
