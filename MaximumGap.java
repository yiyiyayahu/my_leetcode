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
关于bucketSize的选择，尽可能大一些
因为maxGap>=(max-min)/(N-1)的上限，所以bucketSize=(max-min)/(N-1)的上限
2）遍历数组，把元素map到相应地bucket里面去，每个bucket maintain一个max和一个min
3）扫描bucket list，算出Max(q.min - p.max)
*/

/*
感觉这里用一个Bucket这样的class来做代码会简洁一点
要注意的两点：
1）我这个array没有初始化，也就是存在bucketList[i]==null的情况
2）算pmax和qmin的时候，有可能bucketList长度为4，但是只map到第一个bucket和第四个bucket的情况，这样pmax和qmin的值要保留
            pmax = bucketList[i] != null? bucketList[i].high : pmax;
            qmin = bucketList[i+1] != null? bucketList[i+1].low : qmin;
*/

class Bucket{
    int low;
    int high;
    public Bucket(){
        low = -1;
        high = -1; 
    }
}
public class Solution {
    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int len = nums.length;
        int min = nums[0], max = nums[0];
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < min) min = nums[i];
            else if(nums[i] > max) max = nums[i];
        }

        int tmp = (max-min)/(len-1);
        int bSize = (max-min)%(len-1)==0? tmp: tmp+1;
        int blistSize = (max-min)/bSize + 1;
        
        Bucket[] bucketList = new Bucket[blistSize];
        for(int i = 0; i < nums.length; i++) {
            int index = (nums[i] - min)/bSize;
            Bucket b = bucketList[index];
            if(b == null) {
            	b = new Bucket();
            	b.low = nums[i]; b.high = nums[i];
            	bucketList[index] = b;
            	continue;
            }
            
            if(nums[i] < b.low) b.low = nums[i];
            else if(nums[i] > b.high) b.high = nums[i];
        }
        
        int pmax = 0, qmin = 0, result = 0;
        for(int i = 0; i < blistSize-1; i++) {       	
            pmax = bucketList[i] != null? bucketList[i].high : pmax;
            qmin = bucketList[i+1] != null? bucketList[i+1].low : qmin;
            result = Math.max(result, qmin-pmax);
        }
        return result;
    }
}
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
