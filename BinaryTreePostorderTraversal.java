/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
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

//改进方法：用一个pre来记录之前遍历过的node
//判断条件要注意，我之前忽略了可能右子树为空的情况（curr.right == null && curr.left == pre）
public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();

        if(root == null) return list;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode pre = null;
        while(!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            
            if(curr.left == null && curr.right == null) {
                list.add(curr.val);
                stack.pop();
            }             
            else if(curr.right != null && curr.right == pre || curr.right == null && curr.left == pre) {
                list.add(curr.val);
                stack.pop();
            } else {           
            	if(curr.right != null) stack.push(curr.right);
            	if(curr.left != null) stack.push(curr.left);
            }  

            pre = curr;

        }
        return list;
    }
}
/**
用一个stack，先放右节点，再放左节点，如果左右都为空，就直接add到list里
我现在是用一个ArrayList来存之前左右子树都遍历过的节点，如果遇到了，就直接输出。但是不晓得这样做好不好，实在没想出其他好方法
时间复杂度，诶，貌似都是n^2级别的，因为我每次要判断下这个节点有没有之前遍历过。。。这，咋办
网上有个解法是遍历过就把这个节点的left和right都设为null，这样就把树改了呀，不太好吧？
喔喔，leetcode给出的答案是双指针，然后track到底是在向下遍历，还是已经左边遍历过了，还是已经右边遍历过了。。。
空间复杂度O(n)
*/
public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        List<TreeNode> helper = new ArrayList<TreeNode>();
        if(root == null) return list;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while(!stack.isEmpty()) {
            TreeNode n = stack.peek();
            if(helper.contains(n)) {
                list.add(n.val);
                stack.pop();
            } else {
                helper.add(n);
                if(n.right != null) stack.push(n.right);
                if(n.left != null) stack.push(n.left);
                
                if(n.left == null && n.right == null) {
                    list.add(n.val);
                    stack.pop();
                }
            }

        }
        return list;
    }
}
