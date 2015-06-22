/*
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.
*/

/*
只是有好多细节注意一下，题目不难
*/

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
    	List<String> list = new ArrayList<String>();
    	if(words == null || words.length == 0) return list;
    	
    	int count = 0, last = 0;
    	for(int i = 0; i < words.length; i++) {  		
    		if(count + words[i].length() + (i-last) > maxWidth) {
    			int spaceNum = 0;
    			int extraNum = 0;
    			int wordsNum = i - last - 1;
    			if(wordsNum > 0) {
    				spaceNum = (maxWidth-count) / wordsNum;
    				extraNum = (maxWidth-count) % wordsNum;
    			}
    			StringBuilder sb = new StringBuilder();
    			for(int j = last; j < i; j++) {
    				sb.append(words[j]);
    				if(j < i-1) {
	    				for(int k = 0; k < spaceNum; k++) {
	    					sb.append(" ");
	    				}
	    				if(extraNum > 0) {
	    					sb.append(" ");
	    					extraNum --;
	    				} 
    				}
    			}
    			//pad " " if only one word
    			for(int k = sb.length(); k < maxWidth; k++) sb.append(" ");
    			list.add(sb.toString());
    			count = 0;
    			last = i;
    		}
    		count += words[i].length();
    	}
    	StringBuilder sb = new StringBuilder();
    	for(int k = last; k < words.length; k++) {
    		sb.append(words[k]);
    		if(sb.length() < maxWidth) sb.append(" ");
    	}
    	for(int k = sb.length(); k < maxWidth; k++) sb.append(" ");
    	list.add(sb.toString());
        return list;       
    }
}
