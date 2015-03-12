/*
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

/*
我觉得没有什么好方法
1）如果要求时间复杂度低的话就只能是hashmap了，这样就是O(n)但是space也是O(n)
2）要么就是brute force了，对于每个值，遍历所有的搜索，那时间是O(n^2)，空间是O(1)，不过这种做法过不了test
3）要不就先Arrays.sort一下，但是问题还要记得sort之后的array的每个值对应的index。。
*/
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    	if(numbers == null || numbers.length == 0) return null;
    	
    	int[] result = new int[2];
    	for(int i = 0; i < numbers.length; i++) {
    		map.put(numbers[i], i);
    	}
    	for(int i = 0; i < numbers.length; i++) {
    		int num = target - numbers[i];
    		if(map.containsKey(num)) {
    			if(map.get(num) < i) {
    				result[0] = map.get(num) + 1;
    				result[1] = i + 1;
    			} else if(map.get(num) > i) {
    				result[0] = i + 1;
    				result[1] = map.get(num) + 1;
    			} else {
    			    continue;
    			}
    		}
    	}
    	return result;
    }
}
