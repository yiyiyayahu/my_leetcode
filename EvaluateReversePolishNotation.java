/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/
/*
挺简单的，用个stack就行。我这种解法应该可以的吧
*/
public class Solution {
    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length == 0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < tokens.length; i++) {
        	if(!isOperator(tokens[i])) {
        		stack.push(Integer.parseInt(tokens[i]));
        	} else {
        		int tmp1 = stack.pop();
        		int tmp2 = stack.pop();
        		
        		if(tokens[i].equals("+"))  stack.push(tmp1+tmp2);
        		if(tokens[i].equals("*"))  stack.push(tmp1*tmp2);
        		if(tokens[i].equals("-"))  stack.push(tmp2-tmp1);
        		if(tokens[i].equals("/"))  stack.push(tmp2/tmp1);
        	} 
        }
        return stack.pop();
    }
    
    public boolean isOperator(String s) {
    	if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) return true;
    	return false;
    }
}
