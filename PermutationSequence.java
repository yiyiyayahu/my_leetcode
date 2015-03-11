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
这道题后来写了半天才写对
其实对于k，可以一次递归求出每一位应该放什么，但是最开始总是想错了，而且还有好多边界情况
比如n=4的情况吧，那也是填位置[x,x,x,x]，组合的可能个数分别是n!,(n-1)!,(n-2)!,...,1
以1开头的一共有这么多种情况[1,2,3,4],[1,2,4,3],[1,3,2,4],[1,3,4,2],[1,4,2,3],[1,4,3,2]...
那第一位的startElem很有可能就是k/(n-1)!填第二位的时候k=k%(n-1)!,startElem=k/(n-2)!
这里的问题是，如果k=5，那startElem=0; k=6, startElem=1,之后k就置为0了，这很显然是不对的
其实startElem应该取k/(n-1)!的上线！也就是说对于5和6来说都应该是1
而且对于整除的这种情况，应该是开头为x的最后一种可能，所以k不用改变成k%(n-1)!而应该等于(n-2)!
剩下要考虑的就是j=n的时候，(n-j)!=1
*/

public class Solution {

	public String getPermutation(int n, int k) {
		char[] arr = new char[n];
		int numOfPerms = 1;
		for(int i = 1; i < n; i++) {
			numOfPerms *= i;
		}
		
		int start = 0;
		int j = 0;
		while(j < n) {
			start = k/numOfPerms;         			
			if(k%numOfPerms != 0) {
				start = start + 1;
				k = k%numOfPerms;	
			}
			else k = numOfPerms;
			
			int count = 0;
			for(int i = 1; i <= n; i++) {
				if(isValid(arr, i, j)) {
					if(start == 0) {arr[j] = (char)(48+i); break;}
					count ++;
					if(count == start) {
						arr[j] = (char) (48+i);
						break;
					}
				}
			}
			if(j < n-1 && numOfPerms > 1) numOfPerms = numOfPerms/(n-1-j);
			else numOfPerms = 1;
			j++;
		}
		return String.valueOf(arr);
    }

	public boolean isValid(char[] arr, int num, int level) {
		for(int i = 0; i < level; i++) {
			char c = (char) (48+num);
			if(arr[i] == c) return false;
		}
		return true;
	}
}

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
