  /*
  Sort a linked list using insertion sort.
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
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode n = head;
        
        while(n != null) {
            int val = n.val;
            ListNode tmp = head;
            while(tmp.next != n && tmp.val < val) {
                tmp = tmp.next;
            }
            if(tmp.val >= val) {
                int preVal = tmp.val;
                tmp.val = val;
                while(tmp != n) {
                	tmp = tmp.next;
                	int currVal = tmp.val;
                	tmp.val = preVal;
                	preVal = currVal;
                }
            }
            n = n.next;
        }
        
        return head;
    }
}
  
  /*
  先写了一个对array的insertionSort
  开始理解不是特别对，不是swap，而是插入
  比如5,1,4,2,3->1,5,4,2,3->1,4,5,2,3->1,2,4,5,3->1,2,3,4,5
  到2这里，是把4，5推后，然后插入4的前面这样
  */
    public static void insertionSort(int[] arr) {
    	for(int i = 1; i < arr.length; i++) {
    		int tmp = arr[i];    		
    		int j = i;
    		while(j > 0 && arr[j-1] > tmp) {
    			arr[j] = arr[j-1];
    			j --;
    		}
    		arr[j] = tmp;
    	}
    }
