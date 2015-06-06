/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
*/

/*
其实这道题是看有向图里面有没有cycle的一道题
这个graph是有edge lists来组成的
可以用dfs或者bfs来做。最近dfs做的多久用的dfs
就是从一个起始点开始，往下面找这个点的edge，如果找到一个点之前visited过，那就return false
1）这道题开始的错误是visited[curr] = false;的位置问题，当时是在for循环里面就置为false了，这显然是不对的，因为是找curr发出的edge
那找的过程中应该是都保持visited[curr]=true的状态，直到所有curr发出的edge都遍历完了再重置回false才对
2）后来没有注意剪枝，导致了TLE
比如2，如果在1的时候就已经找过2发出的点了，就不要再重复找一遍了，所以就又用了一个boolean数组
其实可以用一个int数组合并visited和marked这两个boolean array的
*/
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
    	if(prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        boolean[] visited = new boolean[numCourses];
        boolean[] marked = new boolean[numCourses];
        for(int i = 0; i < numCourses; i++) {
        	if(!dfs(i, prerequisites, visited, marked)) return false;
        }
        return true;
    }
    
    public boolean dfs(int curr, int[][] graph, boolean[] visited, boolean[] marked) {
        if(visited[curr]) return false;
        if(marked[curr]) return true;
        visited[curr] = true;
        for(int i = 0; i < graph.length; i++) {
            if(graph[i][0] == curr) {
                int newNode = graph[i][1];
                if(!dfs(newNode, graph, visited, marked)) return false; 
                
            }           
        }
        visited[curr] = false;
        marked[curr] = true;
        return true;
    }
}

/*
更新一下BFS的做法，可能对于II更有用，需要用到一个HashMap方便后面查找下一层（有点像重建个小graph），和一个计算in-degree的数组
这道题因为[0,1]表示1是0的prerequisite，所以其实是1->0，这里1是起点，in-degree暂时为0，但是0的in-degree就是1
这道题是先从in-degree为0的（也就是各个起点）找起，向下找每一层，对于下面每一层的degree--，如果是0再push进queue
如果全部找完了，还是有degree不为0的，那么就是有cycle了
*/

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
    	if(prerequisites.length == 0 || prerequisites[0].length == 0) return true;
    	int[] degree = new int[numCourses];
    	HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
    	for(int i = 0; i < prerequisites.length; i++) {
    		int key = prerequisites[i][1];
    		ArrayList<Integer> list;
    		
    		if(graph.containsKey(key)) list = graph.get(key);
    		else list = new ArrayList<Integer>();
    		
			list.add(prerequisites[i][0]);
			graph.put(key, list);
			
    		degree[prerequisites[i][0]] ++;
    	}
    	System.out.println(graph);
    	Queue<Integer> queue = new LinkedList<Integer>();
    	for(int i = 0; i < numCourses; i++) {
    		if(degree[i] == 0) queue.add(i);
    	}
    	while(!queue.isEmpty()) {
    		int start = queue.remove();
    		if(!graph.containsKey(start)) continue;
    		for(int next : graph.get(start)) {
    			degree[next] --;
    			if(degree[next] == 0) {
    				queue.add(next);
    			} 
    		}
    	}
    	for(int i = 0; i < numCourses; i++) {
    		if(degree[i] > 0) return false;
    	}
    	return true;
    }
}
