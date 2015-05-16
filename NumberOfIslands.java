/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3

*/

/*
开始完全没想法，只是知道并不用算出哪些区域可以form一个island
后来想到其实能出现island的地方，那么那些1肯定是相连的或者独立的1，才有一个island，island和island之间肯定是隔开的
也就是，看到1，把周围的1都设成另外一个数字，然后看这个二维char里面有多少个'1'就是有多少个island了

二维数组的题可以想想bfs啊，或者dfs啊之类的解法。这道题其实就是
自己对dfs不是很熟，再加强练一下
*/
public class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null) return 0;
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    count ++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    
    public void dfs(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length) return;
        if(j < 0 || j >= grid[0].length) return;
        if(grid[i][j] == '1') {
            grid[i][j] = '2';
            dfs(grid, i-1, j);
            dfs(grid, i+1, j);
            dfs(grid, i, j-1);
            dfs(grid, i, j+1);
        }
    }
}
