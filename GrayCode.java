/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/

 /*
 开始没什么思路，后来发现，诶，很简单
 n=2, 00 -> 01 -> 11 -> 10
 n=3, 000 -> 001 -> 011 -> 010
      100 <- 101 <- 111 <- 110
 */
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        
        if(n == 0) {
            result.add(0); 
            return result;
        }
        if(n == 1) {
            result.add(0);
            result.add(1);
            return result;
        }
        
        List<Integer> pre = grayCode(n-1);
        result = new ArrayList<Integer>(pre);
        for(int i = pre.size() - 1; i >= 0; i--) {
            int num = (int) (pre.get(i) + Math.pow(2,n-1));
            result.add(num);
        }
        return result;
    }
}
