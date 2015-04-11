/*
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
For "bbbbb" the longest substring is "b", with the length of 1.
*/

/*
想了想肿么能不重复的搜，我这个做法应该每个元素只遍历了一遍，双指针
q,w,n,f,e,n,g,p,g,l,q,d,q
第一次是qwnfe，然后end到了n，这个时候就到了重复的，那我start直接跳到重复字母后面一位就好啦，也就是f，end再往后推
这之前的没必要再搜了，因为不可能longer

在毛老师的提示下，用int[256]存index要比存出现1没出现0的信息量更大一些
后来考虑到因为初始化都是0，所以第0位要存1，也就是存的都是index+1

没考虑到的一点是if(arr[c] < start) 的时候还是要更新arr[c]的： arr[c] = end + 1;
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        
        int[] arr = new int[256];
        int slen = s.length(), longest = 0;
        
        int start = 0, end = 0;
        
        while(start < slen) {
            arr[s.charAt(start)] = start + 1;
            if(end <= start) end = start + 1; 
            int tmpLen = 0;
            while(end < slen) {                
                char c = s.charAt(end);
                if(arr[c] == 0) {
                    arr[c] = end + 1;
                } else {
                    if(arr[c] < start) {
                        arr[c] = end + 1;
                    	end ++; 
                    	continue;
                    }
                    tmpLen = end - start;
                    if(tmpLen > longest) longest = tmpLen;
                    start = arr[c];
                    arr[c] = end + 1;
                }
                end ++;
            }
            if(end == slen) {
            	tmpLen = end - start;
            	if(tmpLen > longest) longest = tmpLen;
            	break;
            }
        }
        return longest;
    }
}
