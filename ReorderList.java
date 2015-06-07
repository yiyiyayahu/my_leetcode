/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
*/


/*
思路是:
 1) find the middle node 
 2) reverse the end 
 3) merge the front and the end
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        ListNode second = reverse(mid);
        
        ListNode first = head;
	    while(second != null) {
	    	ListNode first_next = first.next;
	    	ListNode second_next = second.next;
			first.next = second;
			second.next = first_next;
			first = first_next;
			second = second_next;
		}       
    }
    
    public ListNode reverse(ListNode mid) {
    	ListNode newtail = null;
    	ListNode n = mid;
    	
    	while(n != null) {
    		ListNode next = n.next;
    		n.next = newtail;
    		newtail = n;
    		n = next;
    	}
    	return newtail;
    }
}
