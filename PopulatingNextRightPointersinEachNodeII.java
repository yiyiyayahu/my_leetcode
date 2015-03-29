/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
 
/*
这道题写了好久才写对，好多corner case木有考虑到
比如tmp==null的情况，还有下面这种这种情况，也就是我后面只是调用
        if(root.left != null) connect(root.left);
        else if(root.right != null) connect(root.right);
可是-7没有孩子，所以这一层就断了，8和9就没有办法连在一起了
       -1
      /  \
    -7    9
         / \
       -1   -7
        \   /
         8 9
         
我觉得肯定还是有其他方法，我这种方法写出来的code很乱，而且我要写很久才写对，面试肯定是不行的呀
*/
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        TreeLinkNode p = root;
        TreeLinkNode tmp = null;
        while(p != null) {
            if(p.left != null && p.right != null) {
                p.left.next = p.right;
                tmp = p.right;
            } else {
                if(tmp == null) tmp = (p.left!=null) ? p.left : p.right;
            }
            
            if(p.next != null) {
                if(p.next.left != null) {
                    if(tmp == null) tmp = p.next.left;
                    else {
                        tmp.next = p.next.left;
                        tmp = tmp.next;
                    }
                }
                else if(p.next.right != null) {
                    if(tmp == null) tmp = p.next.right;
                    else {
                        tmp.next = p.next.right;
                        tmp = tmp.next;
                    }
                }
            } else {
                if(tmp != null) tmp.next = null;
                tmp = null;
            }
            p = p.next;
        }
        while(root != null && !hasChild(root)) root = root.next;
        if(root == null) return;
        if(root.left != null) connect(root.left);
        else if(root.right != null) connect(root.right);
    }
    public boolean hasChild(TreeLinkNode root) {
        if(root == null) return false;
        if(root.left == null && root.right == null) return false;
        return true;
    }
}
