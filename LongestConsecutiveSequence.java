/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/

//这个解法还是会TLE

public class Solution {
    public int longestConsecutive(int[] num) {
        int result = 0;
    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for(int i = 0; i < num.length; i++) {
    		map.put(num[i], 1);
    	}
    	
    	for(int i = 0; i < num.length; i++) {
    		int curr = num[i];
    		int increment = 1;
    		int length = 1;
    		while(map.containsKey(curr + increment)) {
    			increment ++;
    			length ++;
    		}
    		if(length > result) result = length;
    	}
        return result;
    }
}
