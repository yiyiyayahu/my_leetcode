/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
/*
开始的问题是，我虽然用了个tmp，但是我直接return head，那head被remove的情况就不对了
所以又用了个res=tmp，返回res.next
*/
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        
        ListNode tmp = new ListNode(-1);
        tmp.next = head;
        ListNode res = tmp;
        
        while(tmp != null) {
            while(tmp.next != null && tmp.next.val == val) {
                tmp.next = tmp.next.next;
            }
            tmp = tmp.next;
        }
        return res.next;
    }
}
