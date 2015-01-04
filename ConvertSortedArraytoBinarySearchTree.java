/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
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
public class Solution {
    public TreeNode sortedArrayToBST(int[] num) {
        if(num == null || num.length == 0) return null;
        return sortedArrayBST(num, 0, num.length-1);
    }
    
    public TreeNode sortedArrayBST(int[] num, int begin, int end) {
        if(begin > end) return null;
        if(begin == end) return new TreeNode(num[begin]);
        
        int mid = begin + (end - begin) / 2 ;
        TreeNode n = new TreeNode(num[mid]);
        
        n.left = sortedArrayBST(num, begin, mid-1);
        n.right = sortedArrayBST(num, mid+1, end);
        return n;
    }
}
