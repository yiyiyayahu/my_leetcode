/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
*/

/*
注意以下几点：
1. 负数
2. 溢出情况，如果是Integer.MIN_VALUE，用Math.abs没用，要转成long再计算
3. 循环肿么办

思路是整数分开考虑，小数部分一位一位除
每次都是remainder*10再除一次，直到remainder为0返回
但是，如果有循环的话，那就是看到相同的remainder，用一个hashmap存这个remainder第一次出现的位置，
然后再出现这个remainder的话，就一定是循环出现，只要在这个循环的前面插入(,后面插入)就可以了。

要注意的是，可能不是一位循环，比如1/99就是0.(01)这样，所以最后的那个）要插入到sb.length()的位置
*/
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";
    	if(denominator == 0) return "";
    	
    	boolean isNeg = (numerator < 0) ^ (denominator < 0);
    	
    	StringBuilder sb = new StringBuilder();
    	if(isNeg) sb.append("-");
    	
    	long numer = (long)numerator, denom = (long)denominator;
    	numer = Math.abs(numer);
    	denom = Math.abs(denom);
    	
    	long result = numer/denom;
    	sb.append(result);
    	
    	long remainder = (numer%denom)*10;
    	if(remainder == 0) return sb.toString();
    	
    	sb.append(".");
    	HashMap<Long, Integer> map = new HashMap<Long, Integer>();
    	
    	while(remainder != 0) {
    		if(map.containsKey(remainder)) {
    			int pos = map.get(remainder);
    			sb.insert(pos-1, '(');
    			sb.insert(sb.length(), ')');
    			break;   			
    		} 		
			result = remainder/denom;
			sb.append(result);
			map.put(remainder, sb.length());
			remainder = (remainder%denom)*10;   		
    	}
    	
        return sb.toString();
    }
}
