/*
这个完全是错的，会进入死循环
开始是这样想的，1 -> 2 -> 3 -> 4 -> 5
                p    q   next
然后q.next=p,之后p和q再向后平移一下
但是这个解法的问题是，p的next没有改诶。原来p.next=2对吧，然后q.next=p以后q就会变成2->1->2,p:1->2->1->2。。。
*/
  public void reverseList(ListNode head) {
  	if(head == null || head.next == null) return;
  	ListNode p = head;
  	ListNode q = p.next;
  	ListNode next = null;
  	
  	while(p != null && q != null) {    		
  		q = p.next;    		
  		next = q.next;
  		q.next = p; 
  		p = q;
  		q = next;
  	}
  }
