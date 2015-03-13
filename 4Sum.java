/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
*/

//和3 Sum没有太大差别

public class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(num == null || num.length == 0) return result;
		
		int len = num.length;
		Arrays.sort(num);
		List<Integer> oneSet = new ArrayList<Integer>(4);
		
		for(int i = 0; i < len - 3; i++) {
			for(int j = i + 1; j < len - 2; j ++) {
				int newTarget = target - num[i] - num[j];
				
				int start = j + 1, end = len - 1;
				while(start < end) {
					int tmp = num[start] + num[end];
					if(tmp == newTarget) {
						oneSet = new ArrayList<Integer>(4);
						oneSet.add(num[i]);oneSet.add(num[j]);oneSet.add(num[start]);oneSet.add(num[end]);
						result.add(oneSet);
						start ++;
						end --;
						while(start < end && num[start] == num[start-1]) start ++;
						while(start < end && num[end] == num[end + 1]) end --;
					} 
					else if(tmp < newTarget) start ++;
					else end --;
				}
				while(j < len-1 && num[j] == num[j+1]) {
					j++;
				}
			}
			while(i < len-1 && num[i] == num[i+1]) {
				i++;
			}
		}
		return result;
    }
}
