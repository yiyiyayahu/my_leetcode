/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
For example, given array S = {-1 0 1 2 -1 -4},

A solution set is:
(-1, 0, 1)
(-1, -1, 2)
*/

//code写的乱七八糟的，要refactor一下
public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(num == null || num.length == 0) return result;
		
		int len = num.length;
		Arrays.sort(num);
		List<Integer> oneSet = new ArrayList<Integer>(3);
		
		int i = 0;
		while(i < len - 2) {
			int target = 0 - num[i];
			
			int start = i+1, end = len-1;
			while(start < end) {
				int tmp = num[start] + num[end];
				if(tmp == target) {
					oneSet = new ArrayList<Integer>(3);
					oneSet.add(num[i]);oneSet.add(num[start]);oneSet.add(num[end]);
					result.add(oneSet);
					start ++;
					while(start > 0 && start < len && num[start] == num[start-1]) start ++;	
				}
				else if(tmp < target) {
					start ++;
					while(start > 0 && start < len && num[start] == num[start-1]) start ++;					
				}
				else {
					end --;
					while(end > 0 && end < len && num[end] == num[end + 1]) end --;	
				}
			}
			i++;
			while(i < len && i > 0 && num[i] == num[i-1]) {
				i++;
			}
		}
		return result;
    }
}
