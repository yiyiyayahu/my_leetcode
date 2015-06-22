/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
*/

/*
这道题主要考察data structure


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
