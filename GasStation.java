/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

/*
这道题确实不可能像下面的解法那样两层循环就做出来了，没有意义啊
参考水中的鱼写的：

[Thoughts] 

蛮精巧的一道题。最直白的解法就是从每一个点开始，遍历整个环，然后找出最后剩余油量最大的点。这个是O(n^2)的。
但是这题明显不会无聊到让做题人写个两层循环这么简单。 

仔细想一下，其实和以前求最大连续子数组和的题很像。 

在任何一个节点，其实我们只关心油的损耗，定义： 

diff[i] = gas[i] – cost[i]  0<=i <n 

那么这题包含两个问题： 

1. 能否在环上绕一圈？ 

2. 如果能，这个起点在哪里？ 

第一个问题，很简单，我对diff数组做个加和就好了，leftGas = ∑diff[i]， 如果最后leftGas是正值，那么肯定存在这么一个起始点。
如果是负值，那说明，油的损耗大于油的供给，不可能有解。得到第一个问题的答案只需要O(n)。 

对于第二个问题，起点在哪里？ 

假设，我们从环上取一个区间[i, j], j>i， 然后对于这个区间的diff加和，定义 

sum[i,j] = ∑diff[k] where i<=k<j 

如果sum[i,j]小于0，那么这个起点肯定不会在[i,j]这个区间里，跟第一个问题的原理一样。
举个例子，假设i是[0,n]的解，那么我们知道 任意sum[k,i-1] (0<=k<i-1) 肯定是小于0的，否则解就应该是k。
同理，sum[i,n]一定是大于0的，否则，解就不应该是i，而是i和n之间的某个点。
所以第二题的答案，其实就是在0到n之间，找到第一个连续子序列（这个子序列的结尾必然是n）大于0的。 

至此，两个问题都可以在一个循环中解决。 
*/

/*
多加了一个left参数，来看看是不是leftGas = ∑diff[i] < 0
一个total是不够的，因为如果有解的话，total确实是应该置0的，在前面的都小于0的情况下，说明一切从头开始嘛
*/
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || cost == null) return -1;
        if(gas.length != cost.length) return -1;
        int n = gas.length;
        
        int total = 0, left = 0;
        int start = 0;
       
        for(int i = 0; i < n; i++) {
            total += gas[i] - cost[i];
            left += gas[i] - cost[i];
            if(total < 0) {
            	total = 0;
                start = i + 1;
            }
        }
        if(left < 0) return -1;
        return start;
    }
}
/*
唉，这个解法还是不好，应该不用分开两步写的，再想想
*/
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || cost == null) return -1;
        if(gas.length != cost.length) return -1;
        int n = gas.length;
        
        int total = 0;
        int start = 0;
       
        for(int i = 0; i < n; i++) {
        	total += gas[i] - cost[i];
        }
        if(total < 0) return -1;
        total = 0;
        for(int i = 0; i < n; i++) {
            total += gas[i] - cost[i];
            if(total < 0) {
            	total = 0;
                start = i + 1;
            }
        }
        return start;
    }
}


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
