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

/*
这个解法不太好，会重复调用，时间复杂度不理想
比如            (n,k)
          /               \
    (n-1,k-1)            (n-1,k)
      /    \               /   \
  (n-2,k-2)(n-2,k-1) (n-2,k-1) (n-2,k)
这样，相当于重复调用两次(n-2,k-1)实际上是很不好而且也是没必要的  
*/


/*
如果按照NQueens的那种思路来做的话，其实是往k个位置里面填数字，填好了就返回，不然就接着填
比如[1,2,3] k=2 就把这三个数字填到2个位置上面去
为了避免重复，可以规定顺序
*/
public class Solution {
    static List<List<Integer>> result;
    public List<List<Integer>> combine(int n, int k) {
    	result = new ArrayList<List<Integer>>();
    	if(k > n) return result;
    	
    	List<Integer> A = new ArrayList<Integer>(k);
    	getNext(A, n, 0, k, 1);
    	return result;
    }
    
    public void getNext(List<Integer> A, int n, int index, int k, int pre) {
    	if(index == k) {
    		result.add(A);
    		return;
    	}
    	for(int i = pre; i <= n; i ++) {

    		List<Integer> list = new ArrayList<Integer>(A);
    		list.add(i);
    		getNext(list, n, index + 1, k, i+1);
    	}
    }
}
/*
和NQueens不同的是，如果int[] array可以直接A[index] = i; 但是这里list因为每次都是add，而不是改index上面的数字，所以就只能新建一个object
初始化的时候虽然给了ArrayList A一个k的初始值，但是这个是capacity不是A的size，所以如果A的size是0的话，我没有办法通过A.set(index,i)的方法来实现类似A[index]=i的功能,会有IndexOutOfBoundsException
而且因为ArrayList是pass by reference的，如果A.set(index,i)最后都A的index的位置都会变成n

You're confusing the size of the array list with its capacity:

the size is the number of elements in the list;
the capacity is how many elements the list can potentially accommodate without reallocating its internal structures.
When you call new ArrayList<Integer>(10), you are setting the list's initial capacity, not its size. 
In other words, when constructed in this manner, the array list starts its life empty.
*/

//previous solution
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

