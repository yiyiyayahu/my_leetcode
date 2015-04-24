/*
 Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1]. 
*/

/*
网上的recursive做法：类似NQueens的解题思路
*/
public class Solution {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(num == null || num.length == 0) return result;
    	List<Integer> list = new ArrayList<Integer>();
    	boolean[] visited = new boolean[num.length];
    	helper(num, list, result, visited);
		return result;
    }
    
    public static void helper(int[] num, List<Integer> list, List<List<Integer>> result, boolean[] visited) {
    	if(list.size() == num.length) {
    		result.add(new ArrayList<Integer>(list));
    		return;
    	}
    	
    	for(int i = 0; i < num.length; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
	    		list.add(num[i]);
	    		helper(num, list, result, visited);
	    		list.remove(list.size() - 1);
	    		visited[i] = false;
    		}
    	}
    }
}

/*
iterative
*/
public class Solution {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(num == null || num.length == 0) return result;
        
        List<Integer> list = new ArrayList<Integer>();
        list.add(num[0]);
        result.add(list);
        
        List<List<Integer>> tmp = new ArrayList<List<Integer>>();
        
        for(int i = 1 ; i < num.length; i++) {
            int digit = num[i];
            
            for(List<Integer> l : result) {
                for(int j = 0; j <= l.size(); j++) {
                    List<Integer> arr = new ArrayList<Integer>(l);
                    arr.add(j, digit);
                    tmp.add(arr);
                }
            }
            result = tmp;
            tmp = new ArrayList<List<Integer>>();
        }
        return result;
    }
}
