/*
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. 
The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. 
If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; 
other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path 
RIGHT-> RIGHT -> DOWN -> DOWN.
-2 (K) 	-3 	3
-5 	-10 	1
10 	30 	-5 (P)

Notes:

    The knight's health has no upper bound.
    Any room can contain threats or power-ups, even the first room the knight enters and 
    the bottom-right room where the princess is imp
*/


/*
这道题还可以缩减为一维dp的，用滚动数组来做。代码再简化一下
*/
public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length == 0) return 0;
        int rows = dungeon.length, cols = dungeon[0].length;
        int[][] hp= new int[rows][cols];

        for(int i = rows-1; i >= 0; i--) {
            for(int j = cols-1; j >= 0; j--) {
                int prev = 0;
                if(i == rows-1 && j == cols-1) prev = 0;
                else if(i == rows-1) prev= hp[i][j+1];
                else if(j == cols-1) prev = hp[i+1][j];
                else prev= Math.min(hp[i+1][j], hp[i][j+1]);

                if(dungeon[i][j] < 0) hp[i][j] = prev + dungeon[i][j]*(-1);
                else {
                    if(dungeon[i][j] > prev) hp[i][j] = 0;
                    else hp[i][j] = prev - dungeon[i][j];
                }
            }
        }
        return hp[0][0]+1;
    }
}
