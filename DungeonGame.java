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
做dp的题可以先想想recursive，这样相对好想一点，直接递推公式可能想不通
这道题开始可能会纠结选路线的问题，但是由于只要返回int，这点不用想，只要得出最小血量就行
那recursive呢，就可以想想subproblem：也就是0，0这个点的右边和下面这个点分别到右下角需要的血量，取个最小值就行了
所以dp[i][j]表示i,j这个点能到达最右下角那个点需要的minHP，这样从下面一点点推上来，推到dp[0][0]+1就可以了
还有一个问题就是，比如碰到30这样的，其实记为0就可以了
dp[i][j] = min(dp[i+1][j], dp[i][j+1]) + dungeon[i][j] * (-1) 如果当前格子的血量是负的
            如果血量是正的，如果比之前需要的血量多，那就为0，就来到i，j之前不用加血，不然，就更新差值
这道题推上来其实就是
6  4  1
5  10 4
0  0  5
所以最终结果是7
这道题还可以缩减为一维dp的，用滚动数组来做。代码再简化一下
*/
/*
这个是一维的，注意考虑if(i == rows-1) prev = hp[j+1];这种情况
开始fail在[0,-3] 我直接返回1了。。囧
其实很多人dp会再加一行这样，但是我可能不太习惯
*/
public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length == 0) return 0;
        int rows = dungeon.length, cols = dungeon[0].length;
        int[] hp = new int[cols];
        for(int i = rows-1; i >= 0; i--) {
            for(int j = cols-1; j>= 0; j--) {
                int prev = 0;
                if(j == cols-1) prev = hp[j];
                else if(i == rows-1) prev = hp[j+1];
                else prev = Math.min(hp[j], hp[j+1]);
                if(dungeon[i][j] < 0) hp[j] = prev + dungeon[i][j]*(-1);
                else {
                    if(dungeon[i][j] > prev) hp[j] = 0;
                    else hp[j] = prev - dungeon[i][j];
                }
            }
        }
        return hp[0]+1;
    }
}

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
