/*
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/

/*
这道题肯定还有更好的解法，首先看wiki上面的解释是node里面不存值，在edge上面存值。后来发现好多解法其实设计想法不一样的
我目前是这样想的
        root
  t  /  |o  \ h
  node  o    node
 o /          \ i
  to          hi
然后为了方便查找，用一个hashmap来存这个char和node的对应

但是我开始没有加isLeaf这个boolean，忽略了这种情况

Input:	insert("abc"),search("abc"),search("ab"),insert("ab"),search("ab"),insert("ab"),search("ab")
Output:	[true,false,false,false]
Expected:	[true,false,true,true]

就是先加入abc，再加入ab，那之后search ab的时候应该是返回true的，可是我返回的是false，因为我没有把ab的那个node算作一个leaf
我是按照node.map.size() == 0来判断是不是leaf的，这样的话，b显然不是的。

看看其他人的解法，再想想trie的一些扩展问题
*/
class TrieNode {
    // Initialize your data structure here.
    HashMap<Character, TrieNode> map = new HashMap<Character, TrieNode>();
    boolean isLeaf;
    public TrieNode() {
        
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curr = root;
        if(word == null) return;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(curr.map.containsKey(c)) {
                curr = curr.map.get(c);
            } else {
                TrieNode n = new TrieNode();
                curr.map.put(c, n);
                curr = n;
            }
        }
        curr.isLeaf = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode curr = searchNode(word);
        if(curr == null) return false;
        return curr.isLeaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode curr = searchNode(prefix);
        if(curr == null) return false;
        return true;
    }
    
    public TrieNode searchNode(String word) {
        TrieNode curr = root;
        if(word == null) return null;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(curr.map.containsKey(c)) {
                curr = curr.map.get(c);
            } else {
                return null;
            }
        }
        return curr;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
