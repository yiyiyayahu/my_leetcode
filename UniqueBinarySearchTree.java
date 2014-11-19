/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

public class Solution {
    public int numTrees(int n) {
        int[] arr = new int[n+1];
        return numTrees(n, arr);
    }
    public int numTrees(int n, int[] arr) {
        if(n == 0 || n == 1) {
            return 1;
        } else if(arr[n] > 0) {
            return arr[n];
        } else {
            for(int i = 0; i < n; i++) {
                arr[n] += numTrees(i, arr) * numTrees(n-1-i,arr);
            }
            return arr[n];
        }
    }
}
