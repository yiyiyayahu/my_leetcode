/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
*/

/*
这道题主要考察data structure
如果是least recently used，那就是每次不管是set还是get，访问了就要放到前面来，这样当capacity达到的时候就删除最后一个就可以了
但是如果用linkedlist的话删除太麻烦，所以用双链表（插入和删除为O(1)）
而且如果get想要O(1)的话需要用到hashmap

所以思路是：Hashmap+double linked list
1. set：如果map里面有，更新map，把node挪到前面
	如果没有，if比capacity小，加进去，更新len，else，把最后那个删掉，加进去
2. get: 如果map有，把node挪到前面，返回node的值。
	如果没有，返回-1
	
但是双链表的插入删除操作要注意，我写了好多次都错了，囧
注意prev和next的更新！还有考虑head和end是不是null的问题
*/
class Node {
    int key;
    int value;
    Node prev;
    Node next;
    public Node(int k, int v) {
        this.key = k;
        this.value = v;
    }
}

public class LRUCache {
    HashMap<Integer, Node> map;
    int capacity;
    int len = 0;
    Node head;
    Node end;
    public LRUCache(int capacity) {
        map = new HashMap<Integer, Node>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node n = map.get(key);
        int value = n.value;
        removeNode(n);
        addToHead(n);
        return value;
    }
    
    public void set(int key, int value) {
        if(map.containsKey(key)) {
            Node n = map.get(key);
            n.value = value;
            removeNode(n);
            addToHead(n);
        } else {
            if(len >= capacity) {
                map.remove(end.key);
                removeNode(end);
            } 
	        Node n = new Node(key, value);
	        map.put(key, n);
	        addToHead(n);
	        if(len < capacity) len++;           
        }
    }
    
    public void removeNode(Node n) {
    	Node prev = n.prev;
    	Node next = n.next;
    	
    	if(prev == null) {
    		head = next;
    	} else {
    		prev.next = next;
    	}
    	
    	if(next == null) {
    		end = prev;
    	} else {
    		next.prev = prev;
    	}
    }
    
    public void addToHead(Node n) {
        n.prev = null; //important!!! Since sometimes the node prev is not null, if move to front, set it to null
    	if(head != null) {
    		n.next = head;
    		head.prev = n;
    	} 
    	head = n;
    	
    	if(end == null) end = n;
    }
}
