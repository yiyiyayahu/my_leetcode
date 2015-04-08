/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/

/*改进后的*/
public class Solution {
    public int longestConsecutive(int[] num) {
        int result = 0;
    	HashSet<Integer> set = new HashSet<Integer>();
    	for(int i = 0; i < num.length; i++) {
    		set.add(num[i]);
    	}
    	
    	for(int i = 0; i < num.length; i++) {
    		int curr = num[i];
    		if(!set.contains(curr)) continue;
    		int increment = 1;
    		int length = 1;
    		
    		boolean checkLarger = true;
    		boolean checkSmaller = true;
    		while(set.contains(curr + increment) || set.contains(curr - increment) ) {  
    			if(!set.contains(curr + increment)) checkLarger = false;
    			if(!set.contains(curr - increment)) checkSmaller = false;
    			if(checkLarger) {
    			    length ++;
    			    set.remove(curr + increment);
    			}
    			if(checkSmaller) {
    			    length ++;
    			    set.remove(curr - increment);
    			}
    			increment ++;
    		}
    		
    		if(length > result) result = length;
    	}
        return result;
    }
}

/*
这个解法还是会TLE
跟杨老师打了个电话，感觉思路清晰好多
1. 首先考虑这个解法为什么不好呢，worst case的时间复杂度是什么呢
worst case就是这个array是降序排列的，那复杂度就是O(n^2) 
2. 是什么导致了这个worst case呢
就是重复找，比如5,4,3,2,1 -> 4要找一次，3要找2次，2要找3次，1要找4次
还有，这个一直只是按照+1的顺序找，是不是可以把-1的同时也找了呢
3. 如何避免这种情况
就是比如1，2，3，4，5在1的时候已经都找过了，那可以直接从map里面把这些删掉
这样就可以是O(n)了，因为每个元素我没有出现上述所说的重复找的情况，我只找了一次
4. 为何要用map，既然value没有用到 -> 改成HashSet

还有就是不要一开始就否定自己的解法。既然HashMap或者HashSet可以提供O(1)的，又实在想不出其他的解法，这样是OK的
然后接下来就按照上面的这几点依次想下去，慢慢地就会有思路了

话说杨老师还是很牛的诶。思路超级清晰。而且每次我一跟他解释他就懂，都不用怎么看我代码的。。。
我要练到他这种程度诶
*/
public class Solution {
    public int longestConsecutive(int[] num) {
        int result = 0;
    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for(int i = 0; i < num.length; i++) {
    		map.put(num[i], 1);
    	}
    	
    	for(int i = 0; i < num.length; i++) {
    		int curr = num[i];
    		int increment = 1;
    		int length = 1;
    		while(map.containsKey(curr + increment)) {
    			increment ++;
    			length ++;
    		}
    		if(length > result) result = length;
    	}
        return result;
    }
}
