/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
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
小肥羊教导我说，在分析一道题之前，把可能遇到的case列一下，这样就知道这种思路好不好了，而且在写的时候也会自然考虑到这些corner case
比如这道题，我开始的想法，一种是DFS，但是因为思路不清晰，不晓得怎么做
还有一种想和level order traversal差不多的思路，就是比如我有一个queue，存着TreeNode和当前这个TreeNode的值，之后再一点点加下去
这个想法的缺点就是，如果我有一支很短，另外一支特别长，那我的截止条件是，这个queue里面所有的TreeNode都到底了，这样的话就循环很多次，解法就特别差

关于DFS，开始没有考虑清楚怎么写
小肥羊又教导我，可以先考虑一个node怎么办，要传进来什么，要怎么处理之后的，想清楚就好了
那比如这道题，一个node，我知道到它的sum，和node，如果node.left != null，那么就把sum*10+node.left.val给它，接着调，同理，右边也是一样的
如果left和right都是null，那么就到底了，就可以加到result里面了。
*/
public class Solution {
    
    private int result;
    
    public int sumNumbers(TreeNode root) {
        result = 0;
        if(root == null) return 0;
        sumHelper(root, root.val);
        return result;
    }
    
    public void sumHelper(TreeNode n, int sum) {

        if(n.left == null && n.right == null) 
            result += sum;
        
        if(n.left != null) {
            sumHelper(n.left, sum * 10 + n.left.val);
        }
        if(n.right != null) {
            sumHelper(n.right, sum * 10 + n.right.val);
        }
    }
}
