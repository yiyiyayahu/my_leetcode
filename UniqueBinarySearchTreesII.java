/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
 
 /*
 开始想太多，其实就是和1一样的，确定一个root，左边的构造left，右边的构造right就好了
 这个没法儿dp啊。只是recursive的调用就好了嘛
 
 然后开始一直卡在一个地方，纠结如果leftList.size()==0或者rightList.size()==0咋办
 后来发现n=0的case一直过不了，显示应该返回[{}]，我返回的是[]
 原来left>right的时候也放一个null到list里面，这样就不存在leftList或者rightList的size是0的情况了
 */
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }
    
    public List<TreeNode> helper(int left, int right) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        if(left > right) {
            TreeNode n = null;
        	list.add(n);
        	return list;
        }
        
        for(int i = left; i <= right; i++) {
            List<TreeNode> leftList = helper(left, i-1);
            List<TreeNode> rightList = helper(i+1, right);
            
            for(int m = 0; m < leftList.size(); m++) {
                for(int n = 0; n < rightList.size(); n++) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftList.get(m);
                    node.right = rightList.get(n);
                    list.add(node);
                }
            }
            
        }
        return list;
    }
}
