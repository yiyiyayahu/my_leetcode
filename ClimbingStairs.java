/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/


public class Solution {
    public int climbStairs(int n) {
        int[] arr = new int[n+1];
        return climbStairs(n, arr);
    }
    public int climbStairs(int n, int[] arr) {
        if(n < 0) {
            return 0;
        } else if(n == 0 || n == 1) {
            return 1;
        } else if(arr[n] > 0) {
            return arr[n];
        } else {
            arr[n] = climbStairs(n-1, arr) + climbStairs(n-2, arr);
            return arr[n];
        }
    }
}

/*
f(0)=f(1)=1, f(2)=2
f(n)=f(n-1)+f(n-2) first time climb one stair or first time climb two stairs
*/
