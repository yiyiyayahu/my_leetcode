/*
 Given a string s and a dictionary of words dict, 
 add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"]. 
*/

/*
还是一样的recursion，只是加了一步剪枝，并且优化了一下代码，没有用List<String>来存中间的变量
用一个boolean array来记录。开始notFound都是false，但是循环过一圈之后要是还没找到就设成true，下次碰到这个就不找了，避免了一些重复工作
TLE的那个case是一个比较tricky的case，string很长，但其实是找不到wordbreak的
*/
public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<String>();
        if(s == null || s.length() == 0) return result;
        if(wordDict == null || wordDict.size() == 0) return result;
        boolean[] notFound = new boolean[s.length()+1];
        helper(s, wordDict, 0, new StringBuilder(), result, notFound);
        return result;
    }
    public void helper(String s, Set<String> wordDict, int index, StringBuilder sb, List<String> result, boolean[] notFound) {
        if(index == s.length()) {
            sb.deleteCharAt(sb.length()-1);
            result.add(sb.toString());
            return;
        }
        for(int i = index; i < s.length(); i++) {
            String s1 = s.substring(index, i+1);
            StringBuilder tmp = new StringBuilder(sb);
            if(wordDict.contains(s1) && !notFound[i+1]) {
                sb.append(s1).append(" ");
                int preSize = result.size();
                helper(s, wordDict, i+1, sb, result, notFound);
                if(result.size() == preSize) notFound[i+1] = true;
                sb = tmp;
            }
        }
    }
}

/*
TLE了。。。杯具，呜呜
*/
public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<String>();
        if(s == null || s.length() == 0) return result;
        if(wordDict == null || wordDict.size() == 0) return result;

        helper(s, wordDict, 0, new ArrayList<String>(), result);
        return result;
    }
    public void helper(String s, Set<String> wordDict, int index, List<String> list, List<String> result) {
        if(index == s.length()) {
            StringBuilder sb = new StringBuilder();
            for(String str : list) {
                sb.append(str + " ");
            }
            sb.deleteCharAt(sb.length()-1);
            result.add(sb.toString());
            return;
        }
        for(int i = index; i < s.length(); i++) {
            String s1 = s.substring(index, i+1);
            if(wordDict.contains(s1)) {
                list.add(s1);
                helper(s, wordDict, i+1, list, result);
                list.remove(list.size()-1);
            }
        }
    }
}
