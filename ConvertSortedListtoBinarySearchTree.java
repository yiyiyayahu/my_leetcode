 /*
 Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
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
O(N)的解法，但是不是自己想出来的，实在太难想
其实是bottom-up的解法，就是从左到右一点点建立起来的这个list，这样遍历一遍就能得到这个tree了
注意C++可以直接用指针，然后把head当参数穿进去，但是在java里这样做是不行的，必须要用一个变量head来维护它
比如{1,2,3}
helper(0,2)
left = helper(0,0) -> left = helper(0,-1)=null, root = new TreeNode(1), head = head.next指向2, root.right = helper(1,0)=null
root = new TreeNode(2)
head = head.next 指向3
root.right = helper(2,2) -> left = helper(2,1)=null, root = new TreeNode(3), head = head.next指向null, root.right=helper(3,2)=null
*/

/*
Leetcode上面的解答里面说这样写有助于防止overflow----》 int mid = start + (end - start) / 2;
*/
public class Solution {
    static ListNode head;
    public TreeNode sortedListToBST(ListNode head) {
    	if(head == null) return null;
    	int size = 0;
    	this.head = head;
    	ListNode n = head;
    	while(n != null) {
    		n = n.next;
    		size ++;
    	}
    	return helper(0, size-1);
    }
    
    public TreeNode helper(int start, int end) {
    	if(start > end) return null;
    	int mid = (start + end)/2;

    	TreeNode left = helper(start, mid-1);
    	TreeNode root = new TreeNode(head.val);
    	head = head.next;
    	root.left = left;
    	root.right = helper(mid + 1, end);
    	return root;
    }
}

/*
这个解法肯定是不好的，因为每次都移动指针挪到中间的位置
那比如第一次找root，移动N/2次，
然后找左边的移动N/4次，找右边的移动N/4次，加起来还是N/2次
一共要找logN次，所以复杂度就是O(NlogN)
*/
    public static TreeNode sortedListToBST(ListNode head) {
    	if(head == null) return null;
    	int size = 0;
    	ListNode n = head;
    	while(n != null) {
    		n = n.next;
    		size ++;
    	}
    	return helper(head, 0, size-1);
    }
    
    public static TreeNode helper(ListNode head, int start, int end) {
    	if(start > end) return null;
    	int mid = (start + end)/2;
    	ListNode n = head;
    	for(int i = 0; i < mid; i++) {
    		n = n.next;
    	}
    	TreeNode root = new TreeNode(n.val);
    	root.left = helper(head, start, mid-1);
    	root.right = helper(head, mid + 1, end);
    	return root;
    }
