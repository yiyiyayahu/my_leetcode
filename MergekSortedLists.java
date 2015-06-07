/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/

/*
开始太naive了。以为1和2合并出来的ret和3合并，依次下去就可以了，但是肯定会TLE啊
你想想，每次merge，ListNode ret的length就增加了，后来越来越长
还是要用divide and conquer
其实是lists[0]和lists[n-1]merge存到lists[0]里，list[1]和lists[n-2]merge存到lists[1]里，然后再循环
也可以直接写个helper函数recursive的调用
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
    public ListNode mergeKLists(ListNode[] lists) {
    	if(lists == null || lists.length == 0) return null;
    	return helper(lists, 0, lists.length-1);
    }
    public ListNode helper(ListNode[] lists, int start, int end) {
    	while(start < end) {
    		int mid = (start+end)/2;
    		return mergeTwoLists(helper(lists, start, mid), helper(lists, mid+1, end));
    	}
    	return lists[start];
    }
    
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
