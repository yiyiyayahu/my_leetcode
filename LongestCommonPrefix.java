/*
Write a function to find the longest common prefix string amongst an array of strings.
*/

/*
1. 对于strs[0]的每一个字符，在所有string里面查找，看是不是一致，一致就append到result里面
复杂度是 N: strs.length L:strs[0].length() -> O(N*L)     这个是worst case吧？？？ 我觉得说O(n)就差不多了？因为毕竟乘以的是个constant
*/
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        
        StringBuilder sb = new StringBuilder();
        String s0 = strs[0];
        for(int i = 0; i < s0.length(); i++) {
            char c = s0.charAt(i);
            for(int j = 1; j < strs.length; j++) {
                if(i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return sb.toString();
                } 
            }
            sb.append(c);
        }
        return sb.toString();
    }
}

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        
        String common = getCommonPrefix(strs[0],strs[1]);
        for(int i = 2; i < strs.length; i++) {
            common = getCommonPrefix(common, strs[i]);
        }
        return common;
    }

/*
2. 两个两个比，0和1的common，和2比，得到的common和3比
比第一种差，因为可能前面几个commonprefix比较长，那么比的位数就比第一种解法多
赶脚复杂度也是O(n), 只是可能比较的位数比第一种的多一些，比如这个是O(N*M) M>=L
*/
    
    public String getCommonPrefix(String s1, String s2) {
        if(s1 == null || s2 == null) return null;
        StringBuilder sb = new StringBuilder();
        int len1 = s1.length();
        int len2 = s2.length();
        int len = len1 < len2 ? len1 : len2;
        
        for(int i = 0; i < len; i++) {
            if(s1.charAt(i) == s2.charAt(i) ) {
                sb.append(s1.charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
