/*
Sort a linked list in O(n log n) time using constant space complexity.
*/

/*
好开心，以前这道题做了很久做不出，现在觉得很简单诶，一下子就做完了
其实就是mergesort嘛。主要就是merge那一步而已，而且之前已经有一道题是merge two sorted list了
比如1-4-7-2
left:1-4 right:7-2
再拆分1,4 ->merge (1->4) 7,2->merge (2->7)
然后再把1->4 2->7merge起来
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(mid);
        return mergeTwoLists(l1, l2);
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        ListNode fakeHead = new ListNode(-1);
        ListNode p = fakeHead;
        
        while( l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = (l1 == null)? l2 : l1;
        return fakeHead.next;
    }  
}
