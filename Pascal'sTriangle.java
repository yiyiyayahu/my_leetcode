/*
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(numRows == 0) return result;
    	List<Integer> list = new ArrayList<Integer>();
    	list.add(1);
    	result.add(list);
    	for(int i = 1; i < numRows; i++) {
    		list = helper(list);
    		result.add(list);
    	}
    	
    	return result;
    }
    public List<Integer> helper(List<Integer> prelist) {	
		int len = prelist.size();
		List<Integer> result = new ArrayList<Integer>(len + 1);
		result.add(0, 1);
		for(int i = 0; i < len-1; i++) {
			int sum = 1;
			sum = prelist.get(i) + prelist.get(i+1);
			result.add(i+1,sum);
		}
		result.add(len, 1);
		return result;
	}
}
