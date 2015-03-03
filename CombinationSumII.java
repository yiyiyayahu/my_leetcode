/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/

/*
和第一个不同的在于不允许数组中同一个元素重复出现，那循环调用helper的时候我用的是i-1而不是i
但是这个还是不解决问题，比如题目中出的这个数组[1,1,2,5,6,7,10]有两个1对吧
如果仅仅改那里，就会出现两个[1,7]和两个[1,2,5]
开始我把下面这个循环放在了if(target - num[i] == 0)这个条件里面，但是是错的
      while(i > 0 && num[i] == num[i-1]) {
				  i --;
			  }
仔细想下[2,2,2,2] target=4
如果按我的代码的话，就是在第四个2那里往前面找，返回一个结果[2,2]，然后第三个2返回[2,2]，第二个2又返回[2,2]
所以实际上上面那个循环应该放在最后，这样，如果找到第i个元素，发现第i-1个元素和它一样，那就不用再来一次了
*/
public class Solution {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(num == null || num.length == 0) return result;
        Arrays.sort(num);
    	return helper(num, target, num.length - 1);
    }
    public static List<List<Integer>> helper(int[] num, int target, int end) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	
    	for(int i = end; i >= 0; i--) {
    		if(target - num[i] == 0) {
    			List<Integer> list = new ArrayList<Integer>();
    			list.add(num[i]);
    			result.add(list);
    		} else if(target - num[i] > 0) {
    			List<List<Integer>> pre = helper(num, target-num[i], i-1);
    			if(pre != null && pre.size() > 0) {
    				for(List<Integer> l : pre) {
    					List<Integer> newList = new ArrayList<Integer>(l);
    					newList.add(num[i]);
    					result.add(newList);
    				}
    			}
    		}
    		while(i > 0 && num[i] == num[i-1]) {
			i --;
		}
    	}
    	return result;
    }
}
