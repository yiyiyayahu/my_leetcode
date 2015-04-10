/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/


/*开始用HashSet，后来发现T中的字母可以是重复的
改用HashMap之后会出现TLE，那肯定就是时间复杂度不好，很有可能是O(n^2)。还是之前的那个思路
1. worst case呢就是S="aaaabc" T="abc" 对于第一个a我找了一遍n，第二个a我也找了一遍n-1。。。所以其实是O(n^2)
2. 做的重复工作如何避免呢？唉，有点想不出。如果T是aabc，那其实我前两个a可以跳过不找？
3. 用双指针？双指针其实就是来解决这个问题的。就是比如对于第一个a我得出的是aaaabc，然后我看看能不能缩小范围来满足都在T里的条件
那我得到aabc，之后再从最后一个a开始search，这时候剩余长度比T小应该就可以直接停止search了
*/

/*
因为是string可以看成char array，然后char 又可以对应int[256]，所以不用hashmap这么麻烦
双指针，两个条件退后start
1） S[start]不在T里
2）退后start之后的start-j之间的str还包括T中所有character
*/
public class Solution {
    public String minWindow(String S, String T) {
        if(S == null || T == null || S.length() < T.length()) return "";
        StringBuilder result = new StringBuilder();
        int slen = S.length(), tlen = T.length();
        
        int[] strT = new int[256];
        int[] foundT = new int[256];
        
        int start = 0, end = 0;
        
        for(int i = 0; i < tlen; i++) {
        	strT[T.charAt(i)] ++;
        }
        while(start <= slen - tlen) {
        	StringBuilder tmpSB = new StringBuilder();
        	int foundNum = 0;
        	for(int j = start; j < slen; j++) {       		
        		char c = S.charAt(j);
        		if(strT[c] > 0) {
        			foundT[c] ++; 
        			if(foundT[c] <= strT[c]) foundNum ++; 
        		}
        		if(foundNum == tlen) {
        			end = j;
        			break;
        		}
        	}
			while(start <= end) {
				char c = S.charAt(start);
				if(strT[c] == 0) start ++;
				else if(foundT[c] > strT[c]) {
					start ++;
					foundT[c] --;
				} else {
					break;
				}
			}
			if(foundNum == tlen) {
				for(int i = start; i <= end; i++) {
					tmpSB.append(S.charAt(i));
				}
			}
        	if(result.length() == 0 || tmpSB.length() < result.length() ) {
            	if(tmpSB.length() > 0) result = tmpSB;
        	} 
        	start ++;
        	foundT = new int[256];
        }
        return result.toString();
    }
}

public class Solution {
    public String minWindow(String S, String T) {
        if(S == null || T == null || S.length() < T.length()) return "";
        
        StringBuilder result = new StringBuilder();
        HashSet<Character> set = new HashSet<Character>();
        
        for(int i = 0; i < T.length(); i++) {
            set.add(T.charAt(i));
        }
        int slen = S.length();
        for(int i = 0; i < slen; i++) {
            char curr = S.charAt(i);
            
            if(set.contains(curr)) {
                HashSet<Character> tmpSet = new HashSet<Character>(set);
                tmpSet.remove(curr);
                int j = i + 1;
                StringBuilder tmpsb = new StringBuilder();
                tmpsb.append(curr);
                while(j < slen && !tmpSet.isEmpty())  {
                    char c = S.charAt(j);
                    tmpsb.append(c);
                    if(!tmpSet.isEmpty() && tmpSet.contains(c)) {
                        tmpSet.remove(c);
                    }
                    j++;
                }
                if(tmpSet.isEmpty()) {
                	if(result.length() == 0 || tmpsb.length() < result.length() ) {
                    	result = tmpsb;
                	} 
                } 
            }
        }
        return result.toString();
    }
}
