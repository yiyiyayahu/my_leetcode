

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
