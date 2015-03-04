/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null) return null;
        if(preorder.length == 0 || preorder.length != inorder.length) return null;
        
        return helper(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    public TreeNode helper(int[] preorder, int pre_start, int pre_end, int[] inorder, int in_start, int in_end) {
    	if(in_start > in_end) return null;
    	if(in_start == in_end) return new TreeNode(inorder[in_start]);
    	
    	TreeNode n = new TreeNode(preorder[pre_start]);
    	
    	int i = in_start;
    	while(i <= in_end) {
    		if(inorder[i] == preorder[pre_start]) break;
    		else i++;
    	}
    	int left_len = i - in_start;
    	int right_len = in_end - i;
    	n.left = helper(preorder, pre_start+1, pre_start+left_len, inorder, in_start, i-1);
    	n.right = helper(preorder,pre_end-right_len+1, pre_end, inorder, i+1, in_end);
    	return n;
    }
}
