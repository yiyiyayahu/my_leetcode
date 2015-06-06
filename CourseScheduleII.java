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
这里fail的一个test case是党prerequisites是空的时候，还是要输出的，而且有好多种可能。我开始直接返回了new int[0]，是不对的
*/

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
      int[] ret = new int[numCourses];
    	
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
    	int index = 0;
    	while(!queue.isEmpty()) {
    		int start = queue.remove();
    		ret[index++] = start;
    		if(!graph.containsKey(start)) continue;
    		for(int next : graph.get(start)) {
    			degree[next] --;
    			if(degree[next] == 0) {
    				queue.add(next);
    			} 
    		}
    	}
    	for(int i = 0; i < numCourses; i++) {
    		if(degree[i] > 0) return new int[0];
    	}
    	return ret;
    }
}
