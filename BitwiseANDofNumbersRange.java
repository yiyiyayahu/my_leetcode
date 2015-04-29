/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.

Credits:
Special thanks to @amrsaqr for adding this problem and creating all test cases.
*/

/*
唉，一道bit manipulation的题。开始没想太多，只是觉得，诶，比如5到7对吧，
0101，0110，0111，那如果某一位有0的话这位就是0，但是这个不晓得怎么找诶，想半天

结果网上写的特别好，太巧妙了，根本想不出
http://www.cnblogs.com/grandyang/p/4431646.html
又是一道考察位操作Bit Operation的题，相似的题目在LeetCode中还真不少，比如Repeated DNA Sequences 求重复的DNA序列， 
Single Number 单独的数字,   Single Number II 单独的数字之二 ， Grey Code 格雷码，和 Reverse Bits 翻转位 等等，
那么这道题其实并不难，我们先从题目中给的例子来分析，[5, 7]里共有三个数字，分别写出它们的二进制为：
101　　110　　111
相与后的结果为100，仔细观察我们可以得出，最后的数是该数字范围内所有的数的左边共同1的部分，如果上面那个例子不太明显，
我们再来看一个范围[26, 30]，它们的二进制如下：
11010　　11011　　11100　　11101　　11110
发现了规律后，我们只要写代码找到左边公共1的部分即可

直接平移m和n，每次向右移一位，直到m和n相等，记录下所有平移的次数i，然后再把m左移i位即为最终结果
*/
public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while(m != n) {
            m >>= 1;
            n >>= 1;
            i ++;
        }
        return (m << i);
    }
}
