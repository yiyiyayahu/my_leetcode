/*
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Hint:
Could you do it in-place with O(1) extra space?
Related problem: Reverse Words in a String II
*/

/*
自己写code的能力实在实在是太差！！

思路是这样的，比如[1,2,3,4,5] k=2
1到3，3到5，5到2，2到4，这样就完成了一个循环 -> [4,5,1,2,3]
最开始的code是这样的：
    	int curr = nums[0];
    	int index = k % nums.length;
    	while(index != 0) {
            int tmp = nums[index];
            nums[index] = curr;
            index = (index+k) % nums.length;
            curr = tmp;
    	}
    	nums[0] = curr;
但是不可能是一个循环就可以的啊，比如[1,2,3,4,5,6] k=2，那么1到3，3到5，5到1，这样就index==0了，循环就截止了，可视2，4，6还没有换
但是循环多少次呢？我开始是外面加了个for(int i = 0; i < k; i++)的循环。这样显然也是不行的。。。如果k=4呢，就会多循环一圈！
上面的解法外面加循环要加多少次我目前没想出来。。。我能想到的是要不要查查这个元素有没有遍历过，如果有，就继续，没有就算了？
或者下面的解法，一共只可能跳len次
*/
public class Solution {
    public void rotate(int[] nums, int k) {
        if(nums == null || k == 0) return;
        int len = nums.length;
        k = k % len;
        int curr = nums[0], index = 0, distance = 0;
        for(int i = 0; i < len; i++) {
        	index = (index + k) % len;
        	int tmp = nums[index];
        	nums[index] = curr;
        	curr = tmp;
        	
        	distance = (distance + k) % len;
        	if(distance == 0) {
        		index = (index + 1) % len;
        		curr = nums[index];
        	}
        }
    }
}
