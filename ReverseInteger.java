/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

Spoilers:
Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
*/
public class Solution {
    public int reverse(int x) {
        if(x == 0) return x;
        boolean isNeg = false;
        if(x < 0) {
            isNeg = true;
            x = -x;
        }
        int result = 0, digit = 0;
        while(x != 0) {
            digit = x % 10;
            //To see if this is an overflow or not
            if(result > (Integer.MAX_VALUE - digit) / 10) {
                result = 0;
                break;
            } else {
                result = result * 10 + digit;
                x = x / 10;
            }
        }
        if(isNeg) result = 0 - result;
        return result; 
    }
}
