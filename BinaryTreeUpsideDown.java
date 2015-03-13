/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
   
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
    public TreeNode UpsideDownBinaryTree(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(root == null) return null;
		
        while(root.left != null) {
        	stack.push(root);
        	root = root.left;   	
        }
        
        TreeNode result = new TreeNode(root.val);
        TreeNode newRoot = result;
        while(!stack.isEmpty()) {
        	TreeNode preRoot = stack.pop();
        	if(preRoot.right != null) newRoot.left = new TreeNode(preRoot.right.val);
        	newRoot.right = new TreeNode(preRoot.val);
        	newRoot = newRoot.right;
        }      
        return result;
    }
}
