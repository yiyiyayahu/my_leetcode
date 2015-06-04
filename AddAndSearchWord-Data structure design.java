/*
 Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)

search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

Note:
You may assume that all words are consist of lowercase letters a-z.

click to show hint.
You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first. 
*/

/*
发现不用加wrapper那么麻烦，直接返回一个boolean就好了
*/
class TrieNode {
    // Initialize your data structure here.
    HashMap<Character, TrieNode> map = new HashMap<Character, TrieNode>();
    boolean isLeaf;
    public TrieNode() {

    }
}

public class WordDictionary {
    private TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
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

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return searchNode(word, root, 0);
    }

    public boolean searchNode(String word, TrieNode n, int index) {
        TrieNode curr = n;
        if(index >= word.length()) {
            if(curr.isLeaf) return true;
            else return false;
        }
        char c = word.charAt(index);
        if(c != '.') {
            if(curr.map.containsKey(c)) {
                if(searchNode(word, curr.map.get(c), index+1)) return true;
            }
        } else {
            for(Map.Entry<Character,TrieNode> entry : curr.map.entrySet()) {
                if(searchNode(word, entry.getValue(), index+1)) return true;
            }
        }
        return false;
    }
}
/*
/*
开始是用一个全局变量found来maintain，但是
一共跪了两个testcase，其中一个是：
Input: 	addWord("ran"),addWord("rune"),addWord("runner"),addWord("runs"),addWord("add"),addWord("adds"),addWord("adder"),addWord("addee"),search("r.n"),search("ru.n.e"),search("add"),search("add."),search("adde."),search(".an."),search("...s"),search("....e."),search("......."),search("..n.r")
Output: 	[false,false,true,true,true,false,true,true,false,false]
Expected: 	[true,false,true,true,true,false,true,true,false,false]
不晓得为什么第一个"r.n"判断失败，可能觉得n不是leaf? eclipse里面是过的

后来我在boolean的found那里加了一个wrapper过了
*/
class TrieNode {
    HashMap<Character, TrieNode> map = new HashMap<Character, TrieNode>();
    boolean isLeaf;
    public TrieNode() {

    }
}
class Wrapper {
	boolean found;
	public Wrapper() {
		
	}
}

public class WordDictionary {
    private TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
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

    public boolean search(String word) {
    	Wrapper w = new Wrapper();
        searchNode(word, root, 0, w);
        return w.found;
    }

    public void searchNode(String word, TrieNode n, int index, Wrapper w) {
        TrieNode curr = n;
        if(index >= word.length()) {
            if(curr.isLeaf) w.found = true;
            return;
        }
        char c = word.charAt(index);
        if(c != '.') {
            if(curr.map.containsKey(c)) {
                searchNode(word, curr.map.get(c), index+1, w);
            }
        } else {
            for(Map.Entry<Character,TrieNode> entry : curr.map.entrySet()) {
                searchNode(word, entry.getValue(), index+1, w);
            }
        }
    }
}
// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
