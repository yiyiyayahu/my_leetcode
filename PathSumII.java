/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(root == null) return result;
        if(sum == root.val && root.left == null && root.right == null) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(root.val);
            result.add(list);
            return result;
        }
        
        List<List<Integer>> left_list = pathSum(root.left,sum-root.val);
        List<List<Integer>> right_list = pathSum(root.right, sum-root.val);
        
        for(List<Integer> left : left_list) {
            left.add(0, root.val);
            result.add(left);
        }
        for(List<Integer> right : right_list) {
            right.add(0, root.val);
            result.add(right);
        }
        return result;
    }
}
