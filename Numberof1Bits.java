/*
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.

Credits:
Special thanks to @ts for adding this problem and creating all test cases
*/

/*
test没过，问题应该出在这个unsigned int上面了，不是特别懂，unsigned int应该就是range比较大，因为1开头的数字不再是负数。。。
那我这咋办，把负数的情况单独考虑？
貌似第一个fail的数字是2147483648，应该返回1的，我就直接返回0了。。。
*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n>0) {
        	if((n^1) < n) {
        		count ++;
        	}
        	n = n >> 1;
        }
        return count;
    }
}
