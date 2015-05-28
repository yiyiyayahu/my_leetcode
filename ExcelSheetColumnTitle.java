/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
*/

/*
A的ascii码是65
*/
public class Solution {
    public String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();
        int tmp = 0;
        while(n != 0) {
            tmp = n%26;
            if(tmp != 0) sb.insert(0, getChar(tmp));
            else {
            	tmp = 26;
            	sb.insert(0, 'Z');
            }
            n = (n-tmp) / 26;
        }
        return sb.toString();
    }
    public static char getChar(int n) {
        char c = (char) (n + 64);
        return c;
    }
}
