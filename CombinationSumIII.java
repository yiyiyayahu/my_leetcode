/*
Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]

*/

/*
我觉得我的recursion总算是有点懂了。虽然代码写的还是很慢
其实就是NQueens啊神马的都是递归而已
往里面填数字，1，2，6查完之后返回上一层找1，3对应的，1找完了之后找2，依次类推
需要注意的就是什么时候返回，要返回的时候怎么把结果传回去
还有就是递归完了记得还原，比如list.remove(list.size()-1);

要注意的一点是在返回的时候开始我是这样做的
    	if(k == 0) {
    		if(n == 0) {
    			lists.add(list); 
    		}
    		return;
    	}
这样是不对的。因为是reference的，而list里面的东西一直在改。所以最后lists也就成了这副样子[[]]。
我要注意这个错误，犯了好多次了
*/
public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
    	helper(k, n, 1, new ArrayList<Integer>(), lists);
    	
    	return lists;
    }
    public void helper(int k, int n, int curr, List<Integer> list, List<List<Integer>> lists) {
    	if(k == 0) {
    		if(n == 0) {
    			ArrayList<Integer> l = new ArrayList<Integer>(list);
    			lists.add(l); 
    		}
    		return;
    	} 
		for(int i = curr; i <= 9; i++) {
			if(i > n) continue;
			list.add(i);
			helper(k-1, n-i, i+1, list, lists);
			list.remove(list.size()-1);
		}
    }    
    
}
