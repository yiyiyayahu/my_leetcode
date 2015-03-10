/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
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
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        
        ListNode tmpA = headA;
        ListNode tmpB = headB;
        int lenA = 1;
        int lenB = 1;
        
        while(tmpA.next != null) {
            tmpA = tmpA.next;
            lenA ++;
        }
        while(tmpB.next != null) {
            tmpB = tmpB.next;
            lenB ++;
        }
        
        if(tmpA != tmpB) return null;
        
        tmpA = headA;
        tmpB = headB;
        
        if(lenA < lenB) {
            for(int i = 0; i < lenB - lenA; i++) {
                tmpB = tmpB.next;
            }
        }
        if(lenA > lenB) {
            for(int i = 0; i < lenA - lenB; i++) {
                tmpA = tmpA.next;
            }
        }
        if(tmpA == tmpB) return tmpA;
        while(tmpA.next != null && tmpB.next != null) {
            if(tmpA == tmpB) return tmpA;
            tmpA = tmpA.next;
            tmpB = tmpB.next;
        }
        return tmpA;
    }
}
