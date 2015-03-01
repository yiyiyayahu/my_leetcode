/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
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
		if(head == null) return null;
		ListNode p = new ListNode(Integer.MIN_VALUE);
		p.next = head;
		ListNode n = p;
		int curr = 0;
		boolean isDup = false;
		while(p != null) { 
			while(p.next != null && p.next.next != null && p.next.val == p.next.next.val) {
				curr = p.next.val;
				while(p.next != null && p.next.next != null && p.next.next.val == curr) {
					isDup = true;
					p.next = p.next.next;
				}
				if(isDup && p.next != null) p.next = p.next.next;
				isDup = false;
			}
			p = p.next;
		}		
		return n.next;
    }
}

/*
我感觉自己code写的不太好，但是不加那个while的话是错的
其实逻辑是
while(p != null) {
  while(p.next is not unique) {
    p.next = p.next.next;
  }
  p = p.next;
}
如果前面不加那啰嗦的一句，那就p.next不是unique的时候就p=p.next
比如p->1->1->2->2 结果就变成了2->2
*/
