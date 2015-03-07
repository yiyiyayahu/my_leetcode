/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

/*
开始不是特别知道怎么做，后来觉得，诶，比如[1,2,3,4]，如果知道[1,2,3]的结果，那么4是新的元素，再往里面加就好了嘛
[1,2,3,4] k =2
[1,2,3] --> k=2的情况是[1,2] [1,3] [2,3]
那如果加入4，其实就是往[1,2,3]的k=1的里面加，也就是要加入[1,4] [2,4] [3,4]
*/
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(k > n) return result;

    	if(k == 1) {
    		List<Integer> list; 
    		for(int i = 1; i <= n; i ++) {
    			list = new ArrayList<Integer>();
    			list.add(i);
    			result.add(list);
    		}
    		return result;
    	}
    	
    	List<List<Integer>> pre = combine(n-1,k-1);  	
    	result = combine(n-1,k);
    	
    	for(List<Integer> l : pre) {
    		List<Integer> newList = new ArrayList<Integer>(l);
    		newList.add(n);
    		result.add(newList);
    	}

    	return result;
    }
}