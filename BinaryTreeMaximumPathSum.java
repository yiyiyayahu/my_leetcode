/*
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
*/

/*
1. 几种情况：
root.val, root.val + left, root.val + right, root.val + left + right
2. 要注意这里的path sum，首先必须是一个path
         4
       /  \
      1    -1
     / \
    2   3
    
  首先，4下面的1返回的应该是4而不是6对吧，这样不能构成一条path
  但是也很有可能root不是4，而是-1啊之类的值，所以也要有一个变量来maintain中间的这个最大值
  一种是全局变量，一种是一个element的数组，或者是自己定义一个class，就只要是pass by reference的就行，因为int没有这个功能
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int maxPathSum(TreeNode root) {
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        helper(root, res);
        return res[0];
    }
    public int helper(TreeNode root, int[] res) {
        if(root == null) return 0;
        
        int left = helper(root.left, res);
        int right = helper(root.right, res);
        int single = Math.max(root.val, Math.max(left, right) + root.val);
        int all = left + root.val + right;
        
        res[0] = Math.max(res[0], Math.max(single, all));
        return single;
    }
}
