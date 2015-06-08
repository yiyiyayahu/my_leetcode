/*
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.
*/

/*
开始理解错了，以为是求公共面积，其实是全部的面积
然后要注意有overlap的commonArea和没有overlap的情况
*/

public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    	int common = 0;
    	if( E>=C || G <= A || F>=D || H <= B ) common = 0;
    	else {
	        int length = Math.min(C, G) - Math.max(A, E);
	        int width = Math.min(D, H) - Math.max(B, F);
	        common = length * width;
    	}
        
        int area1 = (C-A) * (D-B);
        int area2 = (G-E) * (H-F);
        
        return area1+area2-common;
    }
}
