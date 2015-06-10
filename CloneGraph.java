/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/


/*
BFS或者DFS遍历一下就好
BFS的话，注意用一个HashMap，类似visited的作用，防止循环。因为有些node可能以前create过了，就不用再create了
*/

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        
        UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
        queue.add(node);
        map.put(node, cloneNode);
        
        while(!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.remove();
            UndirectedGraphNode currClone = map.get(curr);
            
            List<UndirectedGraphNode> neighbors = curr.neighbors;
            for(int i = 0; i < neighbors.size(); i++) {
                UndirectedGraphNode n = neighbors.get(i);
                if(map.containsKey(n)) {
                    UndirectedGraphNode neighborNode = map.get(n);
                    currClone.neighbors.add(neighborNode);
                } else {
                    UndirectedGraphNode newNode = new UndirectedGraphNode(n.label);
                    map.put(n, newNode);
                    currClone.neighbors.add(newNode);
                    queue.add(n);
                }
            }
        }
        return map.get(node);
    }
}
