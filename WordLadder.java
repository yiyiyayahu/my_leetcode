/*
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

/*
最开始的写法是这样的，但是会Time Limit Exceeded
我觉得是因为我这样做其实算是DFS，因为对于queue里面的每一个string，我都要去找一个长度，返回。这样当dict特别大，queue特别大的时候，就特别慢
*/
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if(start == null) return 0;
        if(start.length() != end.length()) return 0;
        
        Queue<String> queue = new LinkedList<String>();
      
        int result = 0;
        if(isOneLetterDiff(start, end)) return 2;
        if(dict.isEmpty()) return 0;
        Set<String> tmpDict = new HashSet<String>(dict);
        for(String s : dict) {
            if(isOneLetterDiff(start, s)) {          	
                tmpDict.remove(s);
                queue.add(s);
            }
        }

        for(String s : queue) {
            if(result == 0) result = ladderLength(s, end, tmpDict) + 1;
            else result = Math.min( ladderLength(s, end, tmpDict) + 1, result);
        }
        return result;
    }
    
    public boolean isOneLetterDiff(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        int diff = 0;
        int slen = s1.length();
        for(int i = 0; i < slen; i++) {
            if(s1.charAt(i) != s2.charAt(i)) diff ++;
        }
        if(diff == 1) return true;
        return false;
    }
}
