/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) 
parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4. 
*/
public class Solution {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<Integer>();

        int max = 0, start = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if(stack.isEmpty()) {
                    start = i+1;
                } else {
                    int top = stack.pop();
                    max = Math.max(max,stack.isEmpty()?i-start+1:i-stack.peek());
                }
            }
        }
        return max;
    }
}
