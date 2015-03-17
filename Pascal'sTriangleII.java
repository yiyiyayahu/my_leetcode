/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/

/*
和I差不多。。。但是我不确定我的空间复杂度OK么。。。。
应该可以这个array in-place的滚动向前得到，再想想
*/

public class Solution {
    public List<Integer> getRow(int rowIndex) {
	if(rowIndex < 0) return null;
	List<Integer> list = new ArrayList<Integer>(rowIndex + 1);
	
	for(int i = 0; i < rowIndex+1; i++) {
		list.add(i, 0);
	}
	list.set(0, 1);
	
	for(int i = 1; i <= rowIndex; i++) {
		list.set(i,1);
		for(int j = i-1; j > 0; j--) {
			int tmp = list.get(j) + list.get(j-1);	
			list.set(j, tmp);
		}
	}
	return list; 
    }
}

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
