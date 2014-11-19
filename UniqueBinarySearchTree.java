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

/*
f(0) = f(1) = 1
f(2) = 2       1         2
              /           \
             2              1
f(3) [1,2,3] if 1 is the root: then left 2--f(2); if 2 is the root then left 1 -- f(1), if 3 is the root then left 2 --f(2)
f(4) [1,2,3,4] f(3) + f(2) + f(2) + f(3)
f(5) [1,2,3,4,5] f(4) + f(3) + f(2) * f(2) + f(3) +f(4)
f(n) = f(0) * f(n-1) + f(1) * f(n-2) + ... + f(n-1) * f(0)
*/
