/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
*/

/*
先写的O(n)的解法，为了保证记录之前node的顺序，所以也弄了一个ListNode<TreeNode>
用InorderTraversal遍历。就直接写的recursive的简单解法
O(1)的想不粗。。。
注意两个地方：1. sort arraylist： Collections.sort(valList);而不是Arrays.sort(valList)
              2. InorderTraversal忘记判断if(root == null) return；了。这个Node调用left,right,val之前一定要看是不是null
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
    public void recoverTree(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        List<Integer> valList = new ArrayList<Integer>();
        
        InorderTraversal(root, nodeList, valList);
        Collections.sort(valList);
        for(int i = 0; i < valList.size(); i++) {
            nodeList.get(i).val = valList.get(i);
        }
    }
    
    public void InorderTraversal(TreeNode root, List<TreeNode> nodeList, List<Integer> valList) {
        if(root == null) return;
        
        if(root.left != null) {
            InorderTraversal(root.left, nodeList, valList);
        }
        nodeList.add(root);
        valList.add(root.val);
        if(root.right != null) {
            InorderTraversal(root.right, nodeList, valList);
        }
    }
}
