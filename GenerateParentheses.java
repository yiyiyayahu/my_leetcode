/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/

public class Solution {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if(n == 0) return result;
        if(n == 1) {result.add("()"); return result;}
        
        List<String> list = generateParenthesis(n-1);
        for(String s : list) {
            StringBuilder sb = new StringBuilder(s);
            sb.insert(0,"(");
            
            StringBuilder tmp = new StringBuilder(sb);
            tmp.insert(1,")");
            result.add(tmp.toString());
            
            Stack<String> stack = new Stack<String>();
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '(' ) stack.push("(");
                if(s.charAt(i) == ')' ) {
                    stack.pop();
                    if(stack.isEmpty()) {
                    	tmp = new StringBuilder(sb);
                        tmp.insert(i+1,")");
                        result.add(tmp.toString());
                    }
                }
            }
        }
        return result;
    }
}
