/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 
 /*
 在reverselist的基础上加入一点变形就好了，原来newtail是null，现在变成end.next
 然后一小部分reverse好了之后，把start的前一个node指向newtail就好了
 发现之前先想好可能遇到的情况很有帮助诶，这样写的程序就会完整许多
 */
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
      if(head == null || head.next == null || m == n) return head;
    	    	
    	ListNode start = head, end = head;
    	ListNode h = head, prev = null;   	
    	
    	int len = 1;
    	while(h != null) {
    		if(len == m-1) prev = h;
    		if(len == m) start = h;
    		if(len == n) end = h;
    		h = h.next;
    		len ++;
    	}
    	h = head;
    	
    	ListNode p = start, next = null, newtail = end.next;
    	for(int i = m; i <= n; i++) {
    		next = p.next;
    		p.next = newtail;
    		newtail = p;
    		p = next;
    	}
    	if(prev == null) return newtail;    	
    	else prev.next = newtail;
    	return head;
    }
}
