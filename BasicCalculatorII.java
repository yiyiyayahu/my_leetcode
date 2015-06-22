/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, and / operators. 
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.
*/


/*
这道题的重点是，把prefix改成postfix！！！
怎么改明天再想想！！！
*/
public class Solution {
    public int calculate(String s) {
    	List<Object> postfix = infixToPostfix(s);
    	Stack<Integer> stack = new Stack<Integer>();
    	int a = 0, b = 0;
    	for(Object o : postfix) {
    		if(o instanceof Character){
                char c = (Character) o;
                b = stack.pop();
                a = stack.pop();
                switch (c) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    default : stack.push(a / b); 
                }
            }else { // instanceof Integer
                stack.push((Integer)o);
            }
    	}
        return stack.pop();
    }
    
    public int rank(char op){
        // the bigger the number, the higher the rank
        switch(op){
            case '+':return 1;
            case '-':return 1;
            case '*':return 2;
            case '/':return 2;
            default :return 0; // '(' 
        }
    }
    
    public List<Object> infixToPostfix(String s) {
        Stack<Character> operators = new Stack<Character>();
        List<Object> postfix = new LinkedList<Object>();

        int numberBuffer = 0;
        boolean bufferingOperand = false;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                numberBuffer = numberBuffer * 10 + c - '0';
                bufferingOperand = true;
            } else {
                if(bufferingOperand)
                    postfix.add(numberBuffer);
                numberBuffer = 0;
                bufferingOperand = false;

                if (c == ' '|| c == '\t')
                    continue;

                if (c == '(') {
                    operators.push('(');
                } else if (c == ')') {
                    while (operators.peek() != '(')
                        postfix.add(operators.pop());
                    operators.pop(); // popping "("
                } else { // operator
                    while (!operators.isEmpty() && rank(c) <= rank(operators.peek()))
                        postfix.add(operators.pop());
                    operators.push(c);
                }
            }

        }
        if (bufferingOperand)
            postfix.add(numberBuffer);

        while (!operators.isEmpty())
            postfix.add(operators.pop());

        return postfix;
    }
}
