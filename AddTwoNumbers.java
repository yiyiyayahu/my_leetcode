/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
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
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;
    	int carry = 0;
    	ListNode result = new ListNode(0);
    	ListNode node = result;
    	while(l1 != null && l2 != null) {		
    		int tmp = l1.val + l2.val + carry;
    		if(tmp >= 10) {
    			carry = 1;
    			node.next = new ListNode(tmp - 10);
    		} else {
    			node.next = new ListNode(tmp);
    			carry = 0;
    		}
    		l1 = l1.next;
    		l2 = l2.next;
    		node = node.next;
    	}
    	if(carry == 0) {
    		node.next = (l1 != null) ? l1 : l2;
    	} else {
    		ListNode carryList = new ListNode(1);
    		if(l1 != null) node.next = addTwoNumbers(carryList,l1);
    		else if(l2 != null) node.next = addTwoNumbers(carryList,l2);
    		else node.next = new ListNode(1);
    	}
    	
    	return result.next;     
    }
}
