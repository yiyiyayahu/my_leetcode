/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), 
the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
*/


/*
一个特别巧妙的做法！！！
因为要考虑这么几点：
1. 可能12+1，就是连着两个char都是数字，这样就要num*10+s.charAt(i)
2. -后面的数字相当于乘以-1
3. （）的问题，先计算括号里面的，虽然对这道题来说没有运算符优先级的问题
当然是用stack，可是如果stack把什么东西都push进去的话算起来不好算

开始想的是，
stack push进去，如果遇到)，stack就pop，同时计算，直到pop出（为止
遇到+号就只要把之前的number放进去，遇到-号就isNeg=true，后面的数字乘以-1放进去，但是如果stack只放Integer的话，（）怎么办

为什么我怎么改我自己的代码都是不对！！！郁闷了，这道题好难做啊，还是自己太弱了
*/
public class Solution {
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        Stack<Integer> stack = new Stack<Integer>();
        
        int ret = 0;
        int num = 0, sign = 1;
        
        for(int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if(Character.isDigit(c)) {
        		num = num*10 + (int)(c-'0');
        	} else if(c == '+') {
        		ret += sign * num;
        		sign = 1;
        		num = 0;
        	} else if(c == '-') {
        		ret += sign * num;
        		sign = -1;
        		num = 0;
        	} else if(c == '(') {
        		stack.push(ret);
        		stack.push(sign);
        		sign = 1;
        		ret = 0;
        	} else if(c == ')') {
        		ret += sign * num;
        		num = 0;
        		int prevSign = stack.pop();
        		int prevRet = stack.pop();
        		ret = ret * prevSign + prevRet;
        	}
        }
        if(num != 0) ret += sign * num;

        return ret;
    }
}
