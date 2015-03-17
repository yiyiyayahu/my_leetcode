/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/

/*
赶脚好难想。最开始想错了，后来这个也是参考人家的才做粗来的。
| 1               |     1
| 1，1            |     1，1
| 1，2，1         |     1，1，1     ——   1，2，1
| 1，3，3，1      |     1，2，1，1  ——   1，3，3，1
其实这里是每次都给最后一位补了个1，然后再从后往前算
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

/*错误版本*/
/*这个版本的问题是从后面往前面算，但是往往我的第n位已经更新过了，这样再算第n-1位的结果就是错的。。。不晓得怎么改
原来想法是如果rowIndex=3：结果变成了
|               1 | 
|            1，1 |
|         1，2，1 |        ->   2，2，1
|      1，3，3，1 |        ->5，5，3，1 
*/
	public static List<Integer> getRow(int rowIndex) {
		if(rowIndex < 0) return null;
		
		List<Integer> list = new ArrayList<Integer>(rowIndex + 1);
		
		for(int i = 0; i < rowIndex+1; i++) {
			list.add(i, 0);
		}
		list.set(rowIndex, 1);
		
		for(int i = 0; i < rowIndex; i++) {
			int tmp = 0;
			for(int j = rowIndex - 1; j >= rowIndex-1-i; j--) {
				tmp = list.get(j) + list.get(j+1);				
				list.set(j, tmp);
			}			
		}
		return list;
	}
	
/*
和I差不多。。。但是我的空间复杂度不好啊，比如rowIndex=k，那用掉的空间其实是1+2+3+...+k应该是O(k^2)
应该可以这个array in-place的滚动向前得到，再想想
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
