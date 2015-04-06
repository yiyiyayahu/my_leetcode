/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
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
 做法比较简单粗暴，直接把level order traversal的拿过来用的，然后把每个level的最后一个加到result里面
 肯定有更优的解法，我现在用了两个queue，两个arraylist，空间复杂度还是很大的O(n)吧？
 时间复杂度，就是遍历了这个tree的所有节点，也是O(n)
 */
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

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
            
            if(level_list.size()>0) result.add(level_list.get(level_list.size()-1));
            if(tmp.isEmpty()) return result;
            
            level_list = new ArrayList<Integer>();
            queue = tmp;
            tmp = new LinkedList<TreeNode>();
        }
    }
}
