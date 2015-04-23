/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, 
replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), 
or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/

/*
很简单的一道题，但是开始不知道如果不是happy number怎么停下来return false
后来仔细审题，发现题目中已经明确说了，or it loops endlessly in a cycle which does not include 1
就是可能是4-16-37....-4-16-37...，就想到用一个set存一下之前遇到过的
*/

public class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<Integer>();
        while(n != 1) {
            set.add(n);
            n = getsquare(n);
            if(set.contains(n)) return false;
        }
        return true;
    }
    
    public int getsquare(int n) {
        int sum = 0;
        while(n > 0) {
            int digit = n%10;
            sum += digit * digit;
            n = (n-digit)/10;
        }
        return sum;
    }
}
