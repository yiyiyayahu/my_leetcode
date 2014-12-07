/*
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
 
 /*Bad solution..... Did not use constant extra space*/
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.add(root);
        
        Queue<TreeLinkNode> tmp = new LinkedList<TreeLinkNode>();
        
        while(!queue.isEmpty()) {
            TreeLinkNode n = queue.remove();
            n.next = queue.peek();

            if(n.left != null) tmp.add(n.left);
            if(n.right != null) tmp.add(n.right);
            
            if(queue.peek() == null) {
                queue = tmp;
                tmp = new LinkedList<TreeLinkNode>();
            }
        }
    }
}

/*Updated: using only one extra space*/
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        if(root.left == null || root.right == null) return;
        
        TreeLinkNode p = root;

        while(p != null) {
            p.left.next = p.right;
            if(p.next != null) {
                p.right.next = p.next.left;
            } else {
                p.right.next = null;
            }
            p = p.next;
        }
        connect(root.left);
    }
}
