/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

/*
先粗略写了一个特别简单的解法，居然过了。。。但是代码写的不简洁。
我觉得应该有更好的思路吧
*/

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || cost == null) return -1;
        if(gas.length != cost.length) return -1;
        
        for(int i = 0; i < gas.length; i++) {
            if(helper(gas, cost, i)) return i;
        }
        return -1;
    }
    
    public boolean helper(int[] gas, int[] cost, int start) {
        int total = 0;
        
        for(int i = start; i < gas.length; i++) {
            total += gas[i];
            if(total < cost[i]) return false;
            total -= cost[i];
        }
        for(int i = 0; i < start; i++) {
            total += gas[i];
            if(total < cost[i]) return false;
            total -= cost[i];
        }
        return true;
    }
}
