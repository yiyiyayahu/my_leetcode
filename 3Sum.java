/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
For example, given array S = {-1 0 1 2 -1 -4},

A solution set is:
(-1, 0, 1)
(-1, -1, 2)
*/

/*
思路呢其实和two sum差不多，只是我其中一段思路是这样的：两个指针，一个头，一个尾，然后一个指针在头尾之间的元素中间扫，
找到了就OK，如果找不到问题来了，我是头前移还是尾前移呢，虽然头和尾在一起不行，但是有可能头或者尾和其他元素可以啊。。。

后来的想法是，从num[0]开始，在后面的数组中找two sum
这道题比较烦的是有重复，怎么跳过。。。可能是我写code比较少，总是写的乱七八糟的，而且总是有点问题
对于while怎么写比for循环清楚一点。其实想明白了也还好
*/

/*
时间复杂度，唉，自己好脑残
首先想想哈，固定第一位之后的two sum其实只遍历了数组一次，就是n这个数量级对吧（开始为啥觉得是n^2啊。。。其实每次就移一个指针而已，开始想的是n-1+n-2+n-3+...+1，这不太对吧，这是brute force的节奏啊。。。）
然后前面那个i的循环也是n的数量级，这样就是n^2
之前数组sort是nlogn
所以总体来讲是O(nlogn + n^2) -> O(n^2)    啊啊啊啊啊啊啊，我为啥觉得是乘法。。。又不是循环。。。还是躲去小角落好了。。。
*/

//改过之后的code
public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(num == null || num.length == 0) return result;
		
		int len = num.length;
		Arrays.sort(num);
		List<Integer> oneSet = new ArrayList<Integer>(3);
		
		for(int i = 0; i < len - 2; i++) {
			int target = 0 - num[i];
			
			int start = i+1, end = len-1;
			while(start < end) {
				int tmp = num[start] + num[end];
				if(tmp == target) {
					oneSet = new ArrayList<Integer>(3);
					oneSet.add(num[i]);oneSet.add(num[start]);oneSet.add(num[end]);
					result.add(oneSet);
					start ++;
					end --;
					while(start < end && num[start] == num[start-1]) start ++;	
					while(start< end && num[end] == num[end + 1]) end --;	
				}
				else if(tmp < target) {
					start ++;					
				}
				else {
					end --;						
				}
			}			
			while(i < len-1 && num[i] == num[i+1]) {
				i++;
			}
		}
		return result;
    }
}

//code写的乱七八糟的，要refactor一下
public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(num == null || num.length == 0) return result;
		
		int len = num.length;
		Arrays.sort(num);
		List<Integer> oneSet = new ArrayList<Integer>(3);
		
		int i = 0;
		while(i < len - 2) {
			int target = 0 - num[i];
			
			int start = i+1, end = len-1;
			while(start < end) {
				int tmp = num[start] + num[end];
				if(tmp == target) {
					oneSet = new ArrayList<Integer>(3);
					oneSet.add(num[i]);oneSet.add(num[start]);oneSet.add(num[end]);
					result.add(oneSet);
					start ++;
					while(start > 0 && start < len && num[start] == num[start-1]) start ++;	
				}
				else if(tmp < target) {
					start ++;
					while(start > 0 && start < len && num[start] == num[start-1]) start ++;	
					//only start<end is enough, and this is not quite necessary, handling this in if(tmp==target) is enough			
				}
				else {
					end --;
					while(end > 0 && end < len && num[end] == num[end + 1]) end --;	
				}
			}
			i++;
			while(i < len && i > 0 && num[i] == num[i-1]) {
				i++;
			}
		}
		return result;
    }
}
