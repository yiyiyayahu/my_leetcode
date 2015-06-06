/*
 Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space. 
*/

/*
这道题好难写啊啊啊啊
开始是思路想不出，后来看网上其他人的，发现，诶可以利用数组诶，下标+1存number就可以了，就是inplace替换嘛

好，撸袖子开始写，结果问题特别多，我都对自己无语了
开始是根本没有swap
后来是swap之后马上就后移了
比如数组[3,4,-1,1]，我把3和-1swap后，i++，把4和1swap后，i就直接++了。但是但是，就成了这副样子-1,1,3,4,
所以，不行，i不能马上++，还是得判断
这时候又出现问题了[1,1]尼玛，死循环了好么。。。所以加一步判断 nums[i] != nums[index]
*/
public class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) return 1;

        for(int i = 0; i < nums.length; i++) {
            int index = nums[i]-1;
            if(index >=0 && index < nums.length && nums[i] != nums[index] && nums[i] != i+1) {
                int tmp = nums[index];
                nums[index] = nums[i];
                nums[i] = tmp;
                i --;
            }
        } 

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == i+1) continue;
            else return i+1;
        }
        return nums.length+1;
    }
}
