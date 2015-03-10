/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/

/*
我自己测试的时候，觉得这个方法是OK的，就是有结果的，但是很显然时间复杂度很不理想
当然，因为刚刚学会NQueens的这种解法，想尝试一下
思路是，找第k个，那我把之前的组合都找出来，直到找到第k个为止
而且每次找1个组合的时候我的isValid判断都要遍历一下之前填好的元素，
这样的话，对于长度n，我生成一个permutation的比较字数就是1+2+3+...+n-1->O(n^2)
然后我还要一共找k个，时间复杂度是O(n^2*k)这样就过不了test
其实根据k应该是可以算出第一位是几的
*/

public class Solution {
	static int num;
	static String result;
	public String getPermutation(int n, int k) {
		num = 0;
		char[] arr = new char[n];
		result = "";
		for(int i = 1; i <= n; i++) {
			arr[0] = (char) (48+i);
			helper(n,1,k,arr);
		}
		return result;
  }
	
	public void helper(int n, int count, int k, char[] arr) {
		if(count == n) {
			num ++;
			if(num == k) {
				result = String.valueOf(arr);
			} 
			return;
	}
		
		for(int i = 1; i <= n; i++) {
			if(isValid(arr, i)) {
				char c = (char) (48+i);
				arr[count] = c;
				helper(n, count + 1, k, arr);
				arr[count] = ' '; //remember to reset!!!
			}
		}
	}
	
	public boolean isValid(char[] arr, int num) {
		for(int i = 0; i < arr.length; i++) {
			char c = (char) (48+num);
			if(arr[i] == c) return false;
		}
		return true;
	}
}
