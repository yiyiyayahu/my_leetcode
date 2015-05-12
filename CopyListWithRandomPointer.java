/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/

/*
开始没有完全理解题意，其实只是和normal的linkedlistNode相比，多了一个random的pointer，指向这个list中的其中一个node，或者null
要把这个信息在copy的时候也完全的记录下来

思路是看的网上的解法，主要分三步
   A -> B -> C -> D
   |____|_____|   |
        |_________|
1. 在每个node后面插入一个node
   A -> A' -> B -> B' -> C -> C'
   
2. 然后复制random的pointer
   A.next.random = A.random.next (也就是A'.random = C.next = C')
   这里要注意A.random有可能是null，要判断好！这里自己又忘记鸟
   
3. 把链表该去掉的链接去掉，还原
   A.next = A'.next
   A'.next = A'.next.next
*/
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return head;
        
        RandomListNode n = head;
        RandomListNode ret = null;
        while(n != null) {
            RandomListNode tmp = new RandomListNode(n.label);
            RandomListNode next = n.next;
            n.next = tmp;
            tmp.next = next;
            n = next;
        }
        n = head;
        
        while(n != null && n.next != null) {
            n.next.random = (n.random == null) ? null : n.random.next;
            n = n.next.next;
        }
        n = head;
        ret = n.next;
        RandomListNode curr = n.next;
        while(n != null && curr != null) {
            n.next = curr.next;
            curr.next = curr.next == null? null : curr.next.next;
            n = n.next;
            curr = curr.next;
        }
        return ret;
    }
}
