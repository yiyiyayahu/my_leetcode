/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/

/*
开始一直没理解iterator的意思
其实这道题不是说随便给你这个tree的任何一个节点，让你返回next smallest number
而是其实相当于是inorderTraversal，只不过用一个iterator的形式
比如      2
        /   \ 
      1      3
其实是iterator开始在1，然后hasNext()的话，调用next()就返回2，然后再返回3这样
又由于题目要求average O(1) time and uses O(h) memory
这个average O(1) time其实是每个节点遍历1遍（或者constant time），这样一个tree调用n次，平均下来就是O(1)

可以参考inordertraversal的写法，用一个stack（但是这个当时想了很久也一直不太熟，要再想想）

http://yuanhsh.iteye.com/blog/2173429
这篇blog写的也很棒，特别喜欢里面的两个连接：
Reference:
http://classes.engr.oregonstate.edu/eecs/spring2012/cs261/lectures/BST_Iterator.pdf
http://n00tc0d3r.blogspot.com/2013/08/implement-iterator-for-binarytree-i-in.html

再想一下这道题
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

public class BSTIterator {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    public BSTIterator(TreeNode root) {
        TreeNode tmp = root;
        while(tmp != null) {
            stack.push(tmp);
            tmp = tmp.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        TreeNode tmp = node;
        tmp = tmp.right;
        while(tmp != null) {
            stack.push(tmp);
            tmp = tmp.left;
        }
        
        return node.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
