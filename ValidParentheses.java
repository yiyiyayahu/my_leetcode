/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/
public class Solution {
    public boolean isValid(String s) {
        if(s == null || s.length() == 0) return true;
        Stack stack = new Stack();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if( c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if(c == ')') {
                    if(!stack.isEmpty() && stack.peek().equals('(')) stack.pop();
                    else return false;
                }
                else if(c == ']') {
                    if(!stack.isEmpty() && stack.peek().equals('[')) stack.pop();
                    else return false;
                }
                else if(c == '}') {
                    if(!stack.isEmpty() && stack.peek().equals('{')) stack.pop();
                    else return false;
                }
                else return false;
            }
        }
        if(stack.isEmpty()) return true;
        return false;
    }
}
/*
time complexity O(n)
space complexity O(n)
*/
