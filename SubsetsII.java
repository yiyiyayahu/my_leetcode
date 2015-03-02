/*
Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        if(num == null) return null;       
        Arrays.sort(num);
        return helper(num, num.length);
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
    		boolean isUnique = true;
    		//if newList does not in pre
    		for(List<Integer> tmp : pre) {
    			if(tmp.size() == newList.size() && tmp.equals(newList)) {
    				isUnique = false;
    			} 
    		}
    		if(isUnique) result.add(newList);
    	}
    	return result;
    }
}

/*
只是比I来说加了个判断条件，判断我加进那个新的元素之后会不会有duplicates发生。但是不知道这样做好不好
*/
