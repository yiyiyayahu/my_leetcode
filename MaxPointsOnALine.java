/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

/*
看两个点斜率，如果斜率相同就在一条直线上
*/

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) {
        HashMap<Double, Integer> map = new HashMap<Double, Integer>();
        int len = points.length;
        int max = 0;
        for(int i = 0; i < len; i++) {
            int duplicate = 1;
            map.clear();
            for(int j = i+1; j < len; j++) {
                Double k = 0.0;
                if(points[i].x == points[j].x) {
                    if(points[i].y == points[j].y) {
                        duplicate++;
                        continue;
                    }
                    else k = Integer.MAX_VALUE * 1.0;
                }
                else {
                	if(points[j].y == points[i].y) k = 0.0;
                	else k = (double)(points[j].y - points[i].y) / (points[j].x - points[i].x);
                }
                if(!map.containsKey(k)) map.put(k, 1);
                else map.put(k, map.get(k)+1);
            }
            
            for(Integer count : map.values()) {
                if(count + duplicate > max) {
                    max = count + duplicate;
                }
            }
            if(map.isEmpty()) max = Math.max(max, duplicate);
        }
        return max;
    }
}
