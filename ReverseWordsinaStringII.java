/*
Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

Related problem: Rotate Array
*/

/*
in place呢，一种方法就是先把这个array整个reverse，然后再对每个单词reverse回来
"the sky is blue" -> "eulb si yks eht" -> "blue is sky the"
*/
public class Solution {
    public void reverseWords(char[] s) {
        if(s == null || s.length == 0) return;
        int len = s.length;
        
        int i = 0, j = len - 1;
        while(i <= j) {
            swap(s, i++, j--);
        }
        
        int start = 0;
        for(int k = 0; k < len; k++) {
            if(s[k] == ' ') {
                int tmp = k - 1;              //这里注意不能直接对k进行操作，不然就和外面的循环冲突，进入死循环
                while(start <= tmp) {
                    swap(s, start ++, tmp --);
                }
                start = k + 1;
            }
        }
        int end = len - 1;
        while(start <= end) {
            swap(s, start ++, end--);
        }
        
    }
    
    public void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}
/*
code写的有点乱，而且没有想好怎么才能in place的换
*/

public class Solution {
    public void reverseWords(char[] s) {
        if(s == null || s.length == 0) return;
        int len = s.length;
        char[] tmp = new char[len];
        
        int j = len - 1;
        int start = 0;
        for(int i = 0; i < len-1; i++) {
            if(s[i] == ' ') {
            	tmp[j-(i-start)] = ' ';
                for(int k = start; k < i; k++) {
                    tmp[j - (i-k-1)] = s[k];
                }
                j = j - (i-start) - 1;
                start = i + 1;
            }
        }
        
        while(start < len) {
        	tmp[j-(len-1-start)] = s[start++];
        }
        	        
        for(int i = 0; i < len; i++) {
            s[i] = tmp[i];
        }
    }
}
