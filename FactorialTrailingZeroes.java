/*
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/

public class Solution {
    public int trailingZeroes(int n) {
        int result = 0;
        for(int tmp = n; tmp/5 > 0; tmp = tmp/5) {
            result += tmp/5;
        }
        return result;
    }
}
