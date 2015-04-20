/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
*/


/*
这道题不难，有时候不要怀疑自己的思路
其实就是把string分割开，然后看看是不是palindrome，是的话，看看remaining是不是，如果都是，就存入list
一个recursive调用的过程
*/

public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        if(s == null || s.length() == 0) return result;
        
        int slen = s.length();
        for(int i = 1; i <= slen; i++) {
            String sub = s.substring(0,i);
            if(isPalindrome(sub)) {
            	if(i == slen) {
            		ArrayList<String> l = new ArrayList<String>();
            		l.add(sub);
            		result.add(l);
            	} else {
	                String remaining = s.substring(i);	                	                
	                List<List<String>> preList = partition(remaining);
	                if(preList.size() == 0) continue;
	                
	                for(List<String> list : preList) {
	                    list.add(0, sub);
	                    result.add(list);
	                }
            	}
            }
        }
        return result;
    }
    
    public boolean isPalindrome(String s) {
        if(s==null || s.length() == 0) return true;
        
        int i = 0;
        int j = s.length() - 1;
        
        while(i <= j) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            if(c1 != c2) return false;
            i++; 
            j--;
        }
        return true;
    }
}
