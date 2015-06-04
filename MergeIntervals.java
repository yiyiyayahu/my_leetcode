/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18]. 
*/

/*
这道题需要先根据start给interval排序，然后要自己写个comparator
java 的comparator里面compare函数是: negative表示小于，positive大于，0相等
之后merge的代码注意一下，开始总是写不对。好笨喔
而且最开始TLE了
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
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<Interval>();
        if(intervals == null || intervals.size() <= 1) return intervals;

        Comparator<Interval> comp = new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                if(i1.start == i2.start) return i1.end-i2.end;
                else return i1.start-i2.start;
            }
        };
        Collections.sort(intervals, comp);

        Interval in1 = intervals.get(0);

        for(int j = 1; j < intervals.size(); j++) {
            Interval in2 = intervals.get(j);
            if(in2.start <= in1.end) {
                in1.end = Math.max(in1.end, in2.end);
            } else {
                ret.add(in1);
                in1 = in2;
            }
        }
        ret.add(in1);
        return ret;
    }
}


/*
开始TLE时候的代码
*/
        int i = 0, j = 0;
        while(i < intervals.size()) {
            Interval in1 = intervals.get(i);

            for(j = i+1; j < intervals.size(); j++) {
                Interval in2 = intervals.get(j);
                if(in2.start <= in1.end) {
                    in1.end = Math.max(in1.end, in2.end);
                } else {
                    break;
                }
            }
            ret.add(in1);
            i = j;
        }
