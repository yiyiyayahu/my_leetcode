/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, 
and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.
*/

/*
这道题肯定不会是O(n)这么简单。那就应该是O(logn)
对于tree来说就应该是遍历左边，遍历右边，recursive这种的
想法是：
        root
      /     \
    left   right
如果level(left) 和 level(right) 一样的话，那就是complete tree，就是2^n-1 (n是整个tree的level）
但是如果level(left) > level(right)，就再recursive调用左边和右边，最后的结果是1+countNodes(root.left)+countNodes(root.right)
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int countNodes(TreeNode root) {
    	if(root == null) return 0;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftLevel = 0, rightLevel = 0;
        while(left != null) {
            left = left.left;
            leftLevel ++;
        }
        while(right != null) {
            right = right.right;
            rightLevel ++;
        }
        if(leftLevel == rightLevel) return (int) (Math.pow(2, leftLevel+1)-1);
        
        return 1+countNodes(root.left)+countNodes(root.right);
    }
}
