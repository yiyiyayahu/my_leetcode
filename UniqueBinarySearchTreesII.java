/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }
    
    public List<TreeNode> helper(int left, int right) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        if(left > right) {
            TreeNode n = null;
        	list.add(n);
        	return list;
        }
        
        for(int i = left; i <= right; i++) {
            List<TreeNode> leftList = helper(left, i-1);
            List<TreeNode> rightList = helper(i+1, right);
            
            for(int m = 0; m < leftList.size(); m++) {
                for(int n = 0; n < rightList.size(); n++) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftList.get(m);
                    node.right = rightList.get(n);
                    list.add(node);
                }
            }
            
        }
        return list;
    }
}
