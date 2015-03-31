/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
*/


/*
应该想考察的不是这样的，可是trim和split明明很方便嘛。。。

官方给出的解法是：
One simple approach is a two-pass solution: 
First pass to split the string by spaces into an array of words, then second pass to extract the words in reversed order.

We can do better in one-pass. While iterating the string in reverse order, we keep track of a word’s begin and end position. 
When we are at the beginning of a word, we append it.
*/
public class Solution {

    public String reverseWords(String s) {
        if(s == null || s.length() == 0) return s;
        
        s = s.trim();
        String[] arr = s.split("\\s+");
        
        StringBuilder result = new StringBuilder();
        
        for(int i = arr.length - 1; i >= 0; i--) {
            result.append(arr[i]);
            if(i!=0) result.append(" ");  //注意不要在末尾再填个space进去
        }
        return result.toString();
    }
}
