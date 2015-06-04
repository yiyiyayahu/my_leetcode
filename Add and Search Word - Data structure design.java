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
下面的代码在一共跪了两个testcase，其中一个是：
Input: 	addWord("ran"),addWord("rune"),addWord("runner"),addWord("runs"),addWord("add"),addWord("adds"),addWord("adder"),addWord("addee"),search("r.n"),search("ru.n.e"),search("add"),search("add."),search("adde."),search(".an."),search("...s"),search("....e."),search("......."),search("..n.r")
Output: 	[false,false,true,true,true,false,true,true,false,false]
Expected: 	[true,false,true,true,true,false,true,true,false,false]
不晓得为什么第一个"r.n"判断失败，可能觉得n不是leaf? 回家再看看
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

    private boolean found = false;
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        searchNode(word, root, 0);
        return found;
    }

    public void searchNode(String word, TrieNode n, int index) {
        TrieNode curr = n;
        if(index >= word.length()) {
            if(curr.isLeaf) found = true;
            return;
        }
        char c = word.charAt(index);
        found = false;
        if(c != '.') {
            if(curr.map.containsKey(c)) {
                searchNode(word, curr.map.get(c), index+1);
            }
        } else {
            for(Map.Entry<Character,TrieNode> entry : curr.map.entrySet()) {
                searchNode(word, entry.getValue(), index+1);
            }
        }
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
