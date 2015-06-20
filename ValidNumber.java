/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. 
You should gather all requirements up front before implementing one.
*/

/*
和atoi有点像，想清楚了就简单了。但是这道题我还是看了leetcode写的那本书
其实就是从前往后面遍历，先想好有几种情况，就好写了：
	/*
	 * check from the beginning
	 * 1. leading whitespaces 
	 * 2. contains +/- sign
	 * 3. all digits -> valid, if has other than numeric characters a,b-> false
	 * 4. may have one "." followed by digits
	 * 5. may have one "e", before "e" there should be numbers, after "e", there could be +/- or none, followed by numbers
	 * 6. all digits/tailing whitespaces -> valid
	 *    if has other characters in the end -> false
	 */
*/
public class Solution {
    public boolean isNumber(String s) {
        int i = 0, n = s.length();
        boolean isNumber = false;
        while(i < n && Character.isWhitespace(s.charAt(i))) i++;
        if(i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
        while(i < n && Character.isDigit(s.charAt(i))) {
            i++;
            isNumber = true;
        }
        if(i < n && s.charAt(i) == '.') {
            i++;
            while(i < n && Character.isDigit(s.charAt(i))) {
                i++;
                isNumber = true;
            }
        }
        
        if(isNumber && i < n && s.charAt(i) == 'e') {
            i++;
            isNumber = false;
            if(i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
            while(i < n && Character.isDigit(s.charAt(i))) {
                i++;
                isNumber = true;
            }
        }
        while(i < n && Character.isWhitespace(s.charAt(i))) i++;
        return isNumber&&(i==n);
    }
}
