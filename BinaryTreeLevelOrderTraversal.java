/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        /*important!!! Cannot initiate like this: List<List<Integer>> result = new ArrayList<ArrayList<Integer>>(); 
        List<> is interface here, index 0 can be LinkedList, index 1 can be ArrayList ...*/
        if(root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<TreeNode> tmp = new LinkedList<TreeNode>();
        List<Integer> level_list = new ArrayList<Integer>();
        
        queue.add(root);
        while(true) {
            while(!queue.isEmpty()) {
                
                TreeNode n = queue.remove();
                level_list.add(n.val);
                
                if(n.left != null) tmp.add(n.left);
                if(n.right != null) tmp.add(n.right);
            }
            
            result.add(level_list);
            if(tmp.isEmpty()) return result;
            
            level_list = new ArrayList<Integer>();
            queue = tmp;
            tmp = new LinkedList<TreeNode>();
        }
    }
}


/*
Binary Tree Level Order Traversal II
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its bottom-up level order traversal as:

[
  [15,7],
  [9,20],
  [3]
]
*/

Solution:
change line52 result.add(level_list);
to result.add(0, level_list);
