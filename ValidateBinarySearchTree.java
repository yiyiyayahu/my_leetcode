/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
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
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE, false, false);
    }
    
    public boolean isValidBST(TreeNode root, int max, int min, boolean checkMax, boolean checkMin) {
        if(root == null) return true;
        
        if(checkMax && root.val >= max) return false;
        if(checkMin && root.val <= min) return false;
        
        return isValidBST(root.left, root.val, min, true, checkMin) && isValidBST(root.right, max, root.val, checkMax, true);
    }
}

/*
Different from before:
[2147483647] -- true
[2147483647,2147483647] --false

So if simply put 
if(root.val >= max || root.val <= min) return false
[2147483647] will return false since Integer.MAX_VALUE=2147483647

So add two booleans to keep track of which value is being checked
*/
