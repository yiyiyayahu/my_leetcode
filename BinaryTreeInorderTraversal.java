/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].
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
 
/* 
 First, we need to figure out who is the succeed node to be visited. Given the root of a tree,
   If it has left child, visit the left-most leaf of the root;
   If it has no left child, visit the root itself and then go to its right subtree;
   repeat until all nodes have been visited.
 To avoid recursion, we need a stack to keep track of the parent nodes along the way.
*/

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode tmp = root;
        
        while(!stack.isEmpty() || tmp != null) {
            if(tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            } else {
                tmp = stack.pop();
                result.add(tmp.val);
                tmp = tmp.right;
            }
        }
        
        return result;
    }
}
