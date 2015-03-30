/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.
*/

/*
这个题我没有考虑到的一个case是s = "a "应该return 1；所以加了一步if(hasNext(s, i, slen)) result = 0;不然就直接return result
*/
public class Solution {
    public int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0) return 0;
        
        int result = 0;
        int slen = s.length();
        for(int i = 0; i < slen; i++) {
            if(s.charAt(i) != ' ') {
                result ++;
            } else {
                if(hasNext(s, i, slen)) result = 0;
                else return result;
            }
        }
        return result;
    }
    
    public boolean hasNext(String s, int index, int slen) {
        if(index >= slen) return false;
        for(int i = index; i < slen; i++) {
            if(s.charAt(i) != ' ') return true;
        }
        return false;
    }
}
