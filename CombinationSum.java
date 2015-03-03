/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the 
candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
*/

/*
感觉这种需要没有duplicates的题要找的时候规定一个顺序，那个end的参数实际上就是防止重复找的，从后面往前的顺序找
*/
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(candidates == null || candidates.length == 0) return result;
        Arrays.sort(candidates);
    	return helper(candidates, target, candidates.length - 1);
    }
    public static List<List<Integer>> helper(int[] num, int target, int end) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	
    	for(int i = end; i >= 0; i--) {
    		if(target - num[i] == 0) {
    			List<Integer> list = new ArrayList<Integer>();
    			list.add(num[i]);
    			result.add(list);
    		} else if(target - num[i] > 0) {
    			List<List<Integer>> pre = helper(num, target-num[i], i);
    			if(pre != null && pre.size() > 0) {
    				for(List<Integer> l : pre) {
    					List<Integer> newList = new ArrayList<Integer>(l);
    					newList.add(num[i]);
    					result.add(newList);
    				}
    			}
    		}
    	}
    	return result;
    }
}
