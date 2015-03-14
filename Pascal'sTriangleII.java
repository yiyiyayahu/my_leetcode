/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/

/*
和I差不多。。。但是我不确定我的时间复杂度OK么。。。。
*/

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
    	if(rowIndex < 0) return list;
    	list.add(1);
    	for(int i = 0; i < rowIndex; i++) {
    		list = helper(list);
    	}
    	return list;
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
