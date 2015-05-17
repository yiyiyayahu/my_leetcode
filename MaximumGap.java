/*
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

Credits:
Special thanks to @porker2008 for adding this problem and creating all test cases.
*/

/*
官方给出的桶排序的做法：
Suppose there are N elements and they range from A to B.

Then the maximum gap will be no smaller than ceiling[(B - A) / (N - 1)]

Let the length of a bucket to be len = ceiling[(B - A) / (N - 1)], then we will have at most num = (B - A) / len + 1 of bucket

for any number K in the array, we can easily find out which bucket it belongs by calculating loc = (K - A) / len 
and therefore maintain the maximum and minimum elements in each bucket.

Since the maximum difference between elements in the same buckets will be at most len - 1, 
so the final answer will not be taken from two elements in the same buckets.

For each non-empty buckets p, find the next non-empty buckets q, then q.min - p.max could be the potential answer to the question. 
Return the maximum of all those values.


简单来说，大概分三步：
1）确定min和max，然后算出bucketSize
2）遍历数组，把元素map到相应地bucket里面去，每个bucket maintain一个max和一个min
3）扫描bucket list，算出Max(q.min - p.max)
*/
/*
下面这个是最简单的做法，先sort然后再遍历。但是不符合题目要求，这个的时间复杂度是O(nlogn)
*/
public class Solution {
    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        Arrays.sort(nums);
        int gap = 0;
        for(int i = 1; i < nums.length; i++) {
            gap = Math.max(gap, nums[i]-nums[i-1]);
        }
        return gap;
    }
}
