/*
 You are given a string, s, and a list of words, words, that are all of the same length. 
 Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once 
 and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter). 
*/
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<Integer>();

        if(words == null || words.length == 0) return ret;
        int m = words.length, n = words[0].length();

        if(s.length() < m*n) return ret;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < m; i++) {
            if(!map.containsKey(words[i])) map.put(words[i], 1);
            else {
                int num = map.get(words[i]);
                map.put(words[i], num+1);
            }
        }
        for(int i = 0; i <= s.length() - m*n; i++) {
            String tmp = s.substring(i, i+m*n);
            HashMap<String, Integer> tmpMap = new HashMap<String, Integer>(map);
            for(int j = 0; j < m*n; j+=n) {
                String w = tmp.substring(j, j+n);

                if(tmpMap.containsKey(w)) {
                    int num = tmpMap.get(w);
                    num --;
                    if(num == 0) tmpMap.remove(w);
                    else tmpMap.put(w, num);
                } else {
                    break;
                }
            }
            if(tmpMap.size() == 0) ret.add(i);
        }
        return ret;
    }
}
