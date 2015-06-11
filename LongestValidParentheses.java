/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) 
parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4. 
*/

/*
这个做法很巧妙诶
开始我想到了遍历一遍就可以，然后用个stack，思路类似maximum subarry，但是有两个问题我想不太清楚：
1）如何舍弃之前的作为一个新的开始
首先，比如())，每次遇到）并且这个stack是空的时候，那就肯定继续不下去了，就从当前的）的下一位开始
但是问题来了，这种情况怎么办啊()((()我前面那两个还是要舍弃的呀。诶，或者不舍弃，直接更新就好了嘛，
比如这时候我之前max已经是2了对吧，然后到最后一个)的时候，我pop出来栈顶，两个一相减就是当前的最大值，再更新max就好了
2）()()这种情况怎么办，怎么和之前的累加？记得之前算新的开始要用到一个start变量，keep track of当前的start的值，
如果stack已经空了，就i-start+1
不然的话，要注意(()()，这时候pop完了stack里面还有一个（，所以其实是-stack.peek()
我之前写的是：(!stack.isEmpty()): int top = stack.pop(); i-top+1，这样是不对的，因为会返回2
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
