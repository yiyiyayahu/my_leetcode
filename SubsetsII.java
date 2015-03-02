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

/*
改进了一下code，后面那个多比较了好多，其实是不必要的
如果看到这个元素和前面一个元素重复，那其实只要在之前的那个result list里面的后面一半添加上新的元素就可以了
想一下，[1,2,2,2]
[1]  --> [],[1]         
[1,2]--> [],[1] + [2],[1,2]
[1,2,2]的时候就不用再往[],[1]里面加元素了，因为在2第一次出现的时候已经这样做过了
[1,2,2] --> [],[1],[2],[1,2] + [2,2],[1,2,2]            从preList的index=2的元素开始加，这样preList的前四位就都加过了
[1,2,2,2] --> [],[1],[2],[1,2],[2,2],[1,2,2] + [2,2,2],[1,2,2,2]  从preList的index=4的元素开始加
注意不是每次都是start=pre.size()/2,而是前面一次的pre.size()的大小，所以新传入了一个参数
*/
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        if(num == null) return null;       
        Arrays.sort(num);
        ArrayList<Integer> size = new ArrayList<Integer>(0);
        return helper(num, num.length, size);
    }
    
    public List<List<Integer>> helper(int[] S, int n, ArrayList<Integer> size) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(n == 0) {
    		ArrayList<Integer> list = new ArrayList<Integer>();
    		result.add(list);
    		return result;
    	}
    	List<List<Integer>> pre = helper(S,n-1,size);
    	result = new ArrayList<List<Integer>>(pre);
    	int curr = S[n-1];
    	
    	int start = 0;
    	if(n >= 2 && S[n-1] == S[n-2]) {
    		start = size.get(0);
    	}
    	for(int i = start; i < pre.size(); i++) { 		
    		List<Integer> newList = new ArrayList<Integer>(pre.get(i));
    		newList.add(curr);
    		result.add(newList);
    	}
    	size.add(0, pre.size());
    	return result;
    }
}

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
