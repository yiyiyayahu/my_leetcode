/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
*/


/*
代码实在写的太烂了。有些情况没有考虑到：
1. 没有overlap:
	1) newInterval在最前面
	2）newInterval在最后面
	3）newInterval在中间
2. 有overlap：
	和一个或者多个overlap
开始的代码，没有overlap里面在最前面和最后面都没有考虑到。。。
*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> ret = new ArrayList<Interval>();
        if(intervals == null) return intervals;
        
        Interval curr = new Interval();
        boolean added = false; 
        for(int i = 0; i < intervals.size(); ) {
            curr = intervals.get(i);
            if(newInterval.end < curr.start && !added) {
            	ret.add(newInterval);
            	added = true;
            } else if(added || newInterval.start > curr.end) {
                ret.add(curr);
                i++;
            } else {              
                curr.start = Math.min(curr.start, newInterval.start);
                curr.end = Math.max(curr.end, newInterval.end);
                newInterval = curr;
                i++;
            }
        }
        if(!added) ret.add(newInterval);
        return ret;
    }
}
