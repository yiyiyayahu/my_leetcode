/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.
*/

/*
其实这里的关键点是：把infix的表达改成postfix的，然后就转换成了Evaluate Reverse Polish Notation这道题
也就是3+2*2变成322*+
1. infix怎么转成postfix呢，这里要注意一下优先级的问题，所以用一个stack来存operator
比如把一个string parse成一个postfix的List<Object>（这里用object的话就Integer和Character都可以存进去了，就不用转来转去的）
遇到是数字的话，直接add到list里面（注意，有可能两个char连着的都是数字）
遇到是运算符的话，如果stack是空，直接push进去，
                  如果stack不是空的，和stack里面的元素比，如果优先级高，就直接push进去，
                                      不然的话，就把stack里面的高于或等于当前运算符都pop出来，加到list里，再把当前的push进去
比如3+2*2-5
List: 3,      stack: +
      3,2            +,*
      3,2,2         pop*,+ add-
  ->  3,2,2,*,+        -
      3,2,2,*,+,5,-
注意最后的处理，要把最后一个数字加进去，同时stack里面要是有运算符的话也要都加到list里面

最后就是和Evaluate Reverse Polish Notation一样的做法啦
*/
public class Solution {
    public int calculate(String s) {
		if(s == null || s.length() == 0) return 0;
		List<Object> list = infixToPostfix(s);
		Stack<Integer> stack = new Stack<Integer>();
		for(Object o : list) {
			if(o instanceof Integer) stack.push((Integer) o); 
			else {
				int a = stack.pop();
				int b = stack.pop();
				if(o.equals('+')) stack.push(a+b);
				if(o.equals('-')) stack.push(b-a);
				if(o.equals('*')) stack.push(a*b);
				if(o.equals('/')) stack.push(b/a);
			}
		}
		return stack.pop();
	}

	public List<Object> infixToPostfix(String s) {
		s = s.replaceAll(" ", "");
		
		Stack<Character> stack = new Stack<Character>();
		List<Object> list = new ArrayList<Object>();
		int number = 0;
		boolean isNumberBefore = false;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if(Character.isDigit(c)) {
				number = number * 10 + c - '0';
				isNumberBefore = true;
				if(i == s.length() - 1) list.add(number);
			} else {
				if(isNumberBefore) {
					list.add(number);
					number = 0;
					isNumberBefore = false;
				}
				if(stack.isEmpty()) stack.push(c);
				else {					
					while(!stack.isEmpty() && getCode(stack.peek()) >= getCode(c)) {
						list.add(stack.pop());
					}
					stack.push(c);
				}
			}
		}
		while(!stack.isEmpty()) {
			list.add(stack.pop());
		}
		return list;
	}

	public int getCode(char c) {
		switch(c) {
			case '+': return 1;
			case '-': return 1;
			case '*': return 2;
			case '/': return 2;
			default: return 0;
		}
	}
}
