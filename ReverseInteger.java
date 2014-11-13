/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321
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
            if(result > (Integer.MAX_VALUE - digit) / 10) {
                result = 0;
                x = 0;
            } else {
                result = result * 10 + digit;
                x = x / 10;
            }
        }
        if(isNeg) result = 0 - result;
        return result; 
    }
}
