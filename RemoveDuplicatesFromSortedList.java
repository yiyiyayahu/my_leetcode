/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        
        while(p != null) {
            int tmp = p.val;
            while(p.next != null && p.next.val == tmp) {
                p.next = p.next.next;
            }
            p = p.next;
        }
        
        return head;
    }
}


/*Comment:
For linked list, remember to consider about if the node is null or not*/
