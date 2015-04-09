/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
*/
/*
确定这是一道Hard难度的题吗？还是我打开方式不对？时间复杂度不好？
我能想到的优化就是if(i > 0 && s.charAt(i) == s.charAt(i-1)) continue;
也就是这种情况abbbbbbcccc，a是要往后找的，b呢，也是要往后找的，但是第二个b就不用再bother的找了
其他的我也想不出了啊。复杂度应该是O(n)吧
*/
public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() == 0) return 0;
        
        int len = s.length();
        
        int result = 0;
        for(int i = 0; i < len; i++) {
            if(i > 0 && s.charAt(i) == s.charAt(i-1)) continue;
            
            char curr = s.charAt(i);
            int j = i + 1, tmp = 1, numOfLetters = 1;
            char next = curr;
            
            while(j < len && numOfLetters <= 2) {
                if(s.charAt(j) != next && s.charAt(j) != curr) {
                    numOfLetters ++;
                    if(s.charAt(j) != next) next = s.charAt(j);
                } 
                if(numOfLetters <= 2) {
                	tmp ++;
                	j ++;
                }
            }
            if(tmp > result) result = tmp;
        }
        return result;
    }
}
