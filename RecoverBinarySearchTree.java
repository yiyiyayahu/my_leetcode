/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
*/

/*
好难啊，其实我也想到用两个node，first和second来保存哪里出错了，然后这里的traversal应该是inorder的
找到这两个node之后只要值换一下就行了
但是，但是，怎么找着两个node不晓得诶
http://yucoding.blogspot.com/2013/03/leetcode-question-75-recover-binary.html
http://fisherlei.blogspot.com/2012/12/leetcode-recover-binary-search-tree.html

其实用双指针遍历，prev和curr，prev是curr的inorder之前的node。因为BST的inorder出来的结果应该是一直increasing的，
那么如果prev.val > curr.val, 就是有问题的。
yucoding的blog用的是recursive的做法。我自己没写出，再好好体会下。
*/
public class Solution {
    TreeNode first;
    TreeNode second;
    TreeNode prev;
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        first = null; second = null; prev = null;
        inorder(root);
        if(first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }
    public void inorder(TreeNode curr) {
        if(curr == null) return;
        //prev is the inorder previous node from curr
        inorder(curr.left);
        if(prev == null) prev = curr;
        else {
            if(prev.val > curr.val) {
                if(first == null) first = prev;
                second = curr;
            }
            prev = curr;
        }
        inorder(curr.right);
    }
}
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
