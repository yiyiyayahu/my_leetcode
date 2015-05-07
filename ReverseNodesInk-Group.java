/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/

/*
唉，代码写的又臭又长，赶紧refactor
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
    public ListNode reverseKGroup(ListNode head, int k) {
    	ListNode start = head, end = head;
    	int count = 0;
    	ListNode ret = null, tail = null, tmpTail = null;
    	while(end != null) {
    		while(count < k && end != null) {
    			end = end.next;
    			count ++;
    		}
    		if(count < k) {
    			if(start == head) return head;
    			else break;
    		}
    		if(start == head) {
    			ret = reverse(start, end);   	
    			tmpTail = ret;
    		} else {
    			tail = reverse(start, end);
    			for(int i = 0; i < k-1; i++) {
    				tmpTail = tmpTail.next;
    			}
    			tmpTail.next = tail;
    			tmpTail = tail;
    		}
    		
    		start = end;
    		count = 0;
    	}
    	return ret;
    }

    public ListNode reverse(ListNode start, ListNode end) {
        ListNode n = start;
        ListNode newtail = end;
        
        while(n != end) {
            ListNode next = n.next;
            n.next = newtail;
            newtail = n;
            n = next;
        }
        return newtail;
    }
}
