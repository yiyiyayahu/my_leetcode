/*
iven a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those 
horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint.

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? 
If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
*/

/*
这个trie直接用的之前实现的那个
但是我发现如果我把lists声明成static的leetcode过不了诶。。。这是为什么，要研究一下！
*/
public class Solution {
    List<String> lists = new ArrayList<String>();
    
    public List<String> findWords(char[][] board, String[] words) {
        if(board == null || words == null) return new ArrayList<String>();
        
        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dfs(board, "", i, j, trie, visited);
            }
        }
        return lists;
    }
    
    public void dfs(char[][] board, String s, int i, int j, Trie trie, boolean[][] visited) {
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]) return;
        s += board[i][j];
        if(!trie.startsWith(s)) return;
        if(trie.search(s)) {
            if(!lists.contains(s)) lists.add(s);
        }
        
        visited[i][j] = true;
        dfs(board, s, i-1, j, trie, visited);
        dfs(board, s, i+1, j, trie, visited);
        dfs(board, s, i, j-1, trie, visited);
        dfs(board, s, i, j+1, trie, visited);
        visited[i][j] = false;
    }
}
