/*
Given inorder and postorder traversal of a tree, construct the binary tree.

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
 
/*要注意一些小细节，比如left和right的时候post_start和post_end的值要注意，最开始写的时候就错了*/
public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null) return null;
        if(inorder.length != postorder.length || inorder.length == 0) return null;

        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    
    public TreeNode helper(int[] inorder, int in_start, int in_end, int[] postorder, int post_start, int post_end) {
        if(in_start > in_end) return null;
    	if(in_start == in_end) return new TreeNode(inorder[in_start]);
    	TreeNode n = new TreeNode(postorder[post_end]);
    	
    	int i = in_start;
    	while(i <= in_end) {
    		if(inorder[i] == postorder[post_end]) break;
    		else i++;
    	}
    	int left_len = i - in_start;
    	n.left = helper(inorder, in_start, i-1, postorder, post_start, post_start + left_len - 1);
    	int right_len = in_end - i;
    	n.right = helper(inorder, i+1, in_end, postorder, post_end - right_len, post_end - 1);  		
    	return n;
    }
}
