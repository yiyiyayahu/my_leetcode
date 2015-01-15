/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
confused what "{1,#,2,3}" means?
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(root == null) return result;
        
        boolean inorder = true;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<TreeNode> tmp = new LinkedList<TreeNode>();
        queue.add(root);
        
        List<Integer> list = new ArrayList<Integer>();
        while(true) {
            while(!queue.isEmpty()) {
                TreeNode n = queue.remove();
                if(n.left != null) tmp.add(n.left);
                if(n.right != null) tmp.add(n.right);
                
                if(inorder) {
                    list.add(n.val);
                }else {
                    list.add(0,n.val);
                }
            }
            result.add(list);
            if(tmp.isEmpty()) return result;
 
            inorder = !inorder;           
            queue = tmp;
            list = new ArrayList<Integer>();
            tmp = new LinkedList<TreeNode>();
        }
    }
}
