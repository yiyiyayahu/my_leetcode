/*
跪在这个testcase上面，回去弄弄
90 / 151 test cases passed.
	Status:
Wrong Answer
	
Submitted: 6 minutes ago
Input: 	[[1,5]], [6,8]
Output: 	[[1,5],[1,5]]
Expected: 	[[1,5],[6,8]]
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
        if(intervals.size() == 0) {
            ret.add(newInterval);
            return ret;
        }
        
        Interval curr = new Interval();
        for(int i = 0; i < intervals.size(); i++) {
            curr = intervals.get(i);
            if(newInterval.start > curr.end) {
                ret.add(curr);
            } else {
                if(newInterval.end < curr.start) ret.add(newInterval);
                else {
                    //has overlapping
                    curr.start = Math.min(curr.start, newInterval.start);
                    curr.end = Math.max(curr.end, newInterval.end);
                    newInterval = curr;
                }
            }
        }
        ret.add(curr);
        return ret;
    }
}
