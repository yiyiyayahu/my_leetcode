/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. 
Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), 
write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. 
It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. 
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: 
[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of 
[ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. 
Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height.
Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, 
[...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: 
[...[2 3], [4 5], [12 7], ...]

*/


/*
这道题不好想诶，第一反应是和merge interval很像。但是因为有了另外一维高度的问题，就不晓得怎么做了。
参考了http://www.cnblogs.com/easonliu/p/4531020.html的想法，思路是这样的
首先我把buildings的坐标拆成[x,y]这种的，比如考虑[2 9 10], [3 7 15], [5 12 12]这种情况
就是拆成[2,10],[9,10],[3,15],[7,15],[5,12],[12,12]
然后按照x排序一下 （这里排序要注意，如果x想同之后的排序还是有点麻烦的，要考虑好多）
[2,10],[3,15],[5,12],[7,15],[9,10],[12,12]
之后，开始找规律：
如果left的点，当前的最大高度和之前的最大高度不一样，就是一个turning point
比如[2,10]之前高度为0，当前最大高度是10，显然是个turning point，更新prev是10
到了[3,15]，显然当前最大高度是15，之前是10，也是一个turning point，加入result，更新prev是15
到了[5,12]，当前高度12比之前最大高度15小，也就是当前最大高度是15，prev也是15，continue;
到了[7,15]，但是这个是右节点，要把15这个高度去掉，已经无效了(题里没有要求这种折点输出)，
把15去掉后发现最大高度是12，之前是15，所以[7,12]也是一个turning point
。。。
思路就比较清晰了：
1. 用一个max heap来维护最大高度堆
2. 用curr和prev分别表示当前和上次的最大高度
3. 如果left，当前height加入maxHeap，如果right，当前height从maxHeap中pop出来
4. curr=maxHeap.peek()，如果maxHeap是空，curr=0，表示到达一个区间的终点
   如果curr != prev，说明是一个turning point，加入result
   
   
但是我排序那里出了两个问题，就是x相等的时候怎么办：
[2,4,7],[2,4,6],[2,4,5]
顺序其实应该是[2,7],[2,6],[2,5],[4,5],[4,6],[4,7]，这样才能保证输出是[2,7],[4,0]
因为如果[2,5]在前面的话就会直接输出[2,5]了，显然是不对的。同理，如果[4,7]在前面的话，curr!=prev，也会输出

还有就是[0,2,3],[2,5,3]
[2,3](right)和[2,3](left)是连着的，如果right那个在前面，直接pop出来了，就会多一个[2,0]的点，所以要让left的在前面
*/

class Coordinator {
	int x;
	int y;
	boolean isLeft;
	public Coordinator(int x, int y, boolean isLeft) {
		this.x = x;
		this.y = y;
		this.isLeft = isLeft;
	}
}

public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<int[]>();
    	
    	ArrayList<Coordinator> lists = new ArrayList<Coordinator>();
    	int rows = buildings.length;
    	for(int i = 0; i < rows; i++) {
    		int[] b = buildings[i];
    		lists.add(new Coordinator(b[0],b[2],true));
    		lists.add(new Coordinator(b[1],b[2],false));
    	}
    	Collections.sort(lists, new Comparator<Coordinator>() {
			@Override
			public int compare(Coordinator a, Coordinator b) {
				if(a.x != b.x) return a.x - b.x;
				if(a.isLeft && b.isLeft) return b.y - a.y;
				if(!a.isLeft && !b.isLeft) return a.y - b.y;
				if(a.isLeft) return -1;
				else return 1;
			}  		
    	});
    	
    	
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>(){  
            @Override  
            public int compare(Integer a, Integer b) {  
                return b - a;  
            }  
        });  
    	int prev = 0, curr = 0;
    	for(Coordinator c : lists) {
    		if(c.isLeft) {
    			maxHeap.add(c.y);
    		} else {
    			maxHeap.remove(c.y);
    		}
    		curr = maxHeap.isEmpty() ? 0 : maxHeap.peek();;
    		if(curr != prev) {
    			result.add(new int[]{c.x, curr});
    			prev = curr;
    		}
    	}
    	return result;
    }
}
