/*
Reverse a singly linked list.
Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?
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
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode newtail = null;
        ListNode n = head;
        
        while(n != null) {
            ListNode next = n.next;
            n.next = newtail;
            newtail = n;
            n = next;
        }
        return newtail;
    }
}
