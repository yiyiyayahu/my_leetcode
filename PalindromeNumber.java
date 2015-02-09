/*
Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
*/

public class Solution {
    public boolean isPalindrome(int x) {
    	if(x < 0) return false;
    	
        int digit = x;        
        int div = 1;
        while(digit / 10 > 0) {
             digit = digit/10;
             div = div * 10;
        }
        
        int i = div;
        int j = 10;
        
        digit = x;
        while(i >= j) {
            int front = digit / i;
            int end = digit % j;
            if(front >= 10) front = front%10;   //don't forget the equal condition
            while(end >= 10) end = end/10;
            
            if(front != end) return false;
            
            digit = digit - end * (j/10);     //consider the case 1001, without minus, the last second end is 1 not 0
            i = i / 10;
            j = j * 10;
        }
        return true;
    }
}
