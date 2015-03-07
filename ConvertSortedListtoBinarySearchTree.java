 /*
 Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */

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
