/*
Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/
public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        if(S == null) return null;       
        Arrays.sort(S);
        return helper(S,S.length);
    }
    
    public List<List<Integer>> helper(int[] S, int n) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(n == 0) {
    		ArrayList<Integer> list = new ArrayList<Integer>();
    		result.add(list);
    		return result;
    	}
    	List<List<Integer>> pre = helper(S,n-1);
    	result = new ArrayList<List<Integer>>(pre);
    	int curr = S[n-1];
    	for(List<Integer> l : pre) { 		
    		List<Integer> newList = new ArrayList<Integer>(l);
    		newList.add(curr);
    		result.add(newList);
    	}
    	return result;
    }
}
