/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
*/

/*
这个code也不是一次性写成的，开始的时候还是忘记判断node是不是null直接循环写的while(node.next!=null)。。。
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
    public ListNode partition(ListNode head, int x) {
        if(head == null) return head;
        
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode tmp = fakeHead;
        
        while(tmp.next != null && tmp.next.val < x) {
        	tmp = tmp.next;
        }
       
        ListNode node = tmp.next;
        while(node != null && node.next != null) {
            if(node.next.val >= x) node = node.next;
            else {
                int val = node.next.val;
                node.next = node.next.next;
                
                ListNode n = new ListNode(val);
                n.next = tmp.next;
                tmp.next = n;
                
                tmp = tmp.next;
            }
        }
        return fakeHead.next;
    }
}
