/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
*/

/*
很巧妙诶，跟位运算有关！比如100/3，每次3<<1，然后ret*=2
但是要注意的是，比如90/3, 3第一次只能移到48，然后再来一次循环，所以用一个tmpCount，每次从1开始*2，ret+=tmpCount这种
这个overflow要注意一下！
首先，Integer.MIN_VALUE如果用Math.abs()或者-divisor的话还是负的，所以只能乘以-1了
而且要注意，我可能在divisor<<1的过程中就超了，所以就转成了long来操作
*/
public class Solution {
    public int divide(int dividend, int divisor) {
    	if(divisor == 0) return Integer.MAX_VALUE;
    	if(dividend == Integer.MIN_VALUE) {
    		if(divisor == -1) return Integer.MAX_VALUE;
    	}
    	
    	int ret = 0;   	
    	boolean isNeg = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
    	
    	long a = dividend > 0? (long)dividend : (long)dividend*(-1);
    	long b = divisor > 0 ? (long)divisor: (long)divisor*(-1);
    	
        while(a >= b) {
            long tmp = b;
            int tmpCount = 1;
            while((tmp<<1) <= a) { 
            	tmp <<= 1;
                tmpCount *= 2;               
            }
            ret += tmpCount;
            a -= tmp;
        }
        return isNeg? -ret : ret;
    }
}
