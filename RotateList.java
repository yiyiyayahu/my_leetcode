/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
*/


/*
开始没太明白啥叫rotate，以为k>len就不行，直接return head呢
其实1-2-3-4-5，k=6和k=1的效果是一样的，那就要加一个k=k%len
然后注意如果k=0呢，那就是不rotate对吧
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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        
        ListNode tmp = head;
        ListNode end = null;
        int len = 0;
        while(tmp != null) {
            len ++;
            if(tmp.next == null) end = tmp;
            tmp = tmp.next;
        }
        k = k % len;
        if(len == k || k == 0) return head;
        
        int steps = len - k;
        tmp = head;
        for(int i = 1; i < steps; i++) {
            tmp = tmp.next;
        }
        
        ListNode newHead = tmp.next;
        tmp.next = null;
        end.next = head;
        return newHead;
    }
}
