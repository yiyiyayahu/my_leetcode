/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/

/*
之前iterative的做法不是特别清楚应该怎么改 T.T

recursive我觉得我理解的还不够，应该再多多练习，就是这种NP的题
*/

public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(num == null || num.length == 0) return result;
    	List<Integer> list = new ArrayList<Integer>();
    	boolean[] visited = new boolean[num.length];
    	
    	Arrays.sort(num);
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
    		    if(i > 0 && num[i] == num[i-1] && visited[i-1]) continue;
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
Only added some lines of code from I:
1) sort the array first
2) if the digit is seen before: 加到重复元素的后面
但是这个code是有问题的，如果[0,0,1]那没有问题，但是如果[0,0,1,1]的话，情况就是：
[0,0,1] -> [[1,0,0], [0,1,0], [0,0,1]]
对于[1,0,0] -> [1,1,0,0], [1,0,1,0], [1,0,0,1]
对于[0,1,0] -> [1,0,1,0], [0,1,1,0], [0,1,0,1]
这样就出现重复了。。。如何避免呢

找到一篇比较好的blog
http://blog.csdn.net/linhuanmars/article/details/21569031

*/
   
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(num == null || num.length == 0) return result;
        
        Arrays.sort(num);
        
        List<Integer> list = new ArrayList<Integer>();
        list.add(num[0]);
        result.add(list);
        
        List<List<Integer>> tmp = new ArrayList<List<Integer>>();
        
        for(int i = 1 ; i < num.length; i++) {
            int digit = num[i];
            boolean sameDigit = false;
            if(digit == num[i-1]) {
                sameDigit = true;
            }
            
            for(List<Integer> l : result) {
                for(int j = 0; j <= l.size(); j++) {
                    List<Integer> arr = new ArrayList<Integer>(l);
                    
                    if(sameDigit) {
                        if(j < l.size() && arr.get(j) == digit) continue;
                    }
                    arr.add(j, digit);
                    tmp.add(arr);
                }
            }
            result = tmp;
            tmp = new ArrayList<List<Integer>>();
        }
        return result;
    }
