/*
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

/*
啊，终于写完了，这道题也太难了！！！！
主要是要自己建个图，把之前的path存起来，然后再重建
喔喔，然后这个和i很像嘛，就是找到了就返回对吧，那就是最小path，不用再去比较len之类的
就在	if(begin.equals(end))这里直接就找到全部路径返回了
因为我是一层一层下来的嘛。在这个level找到了就直接返回了？
再想想
*/
class Node {
	String str;
	int dist;
	List<Node> preNode;

	public Node(String str, int dist) {
		this.str = str;
		this.dist = dist;
		this.preNode = new ArrayList<Node>();
	}
	public void addPreNode(Node n) {
		this.preNode.add(n);
	}
}

public class Solution {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
		List<List<String>> ret = new ArrayList<List<String>>();
		
		//this is to store the graph, where each node has its parents' list, and the distance to start, so that can reconstruct the whole path
		HashMap<String, Node> map = new HashMap<String, Node>();
		Queue<String> queue = new LinkedList<String>();
		
		queue.add(start);
		map.put(start, new Node(start, 1));
		dict.add(end); //add to dict, so that end Node is also in the map

		while(!queue.isEmpty()) {
			String begin = queue.remove();
		    	if(begin.equals(end)) {
		    		getPaths(map.get(end), new ArrayList<String>(), ret);
		    		return ret;
		    	}
        		Set<String> set = oneLetterDiffSet(begin);
        		for(String s : set) {
			    	if(dict.contains(s)) {
			    		Node n = map.get(begin);
			    		if(!map.containsKey(s)) {                   			                   			
			    			Node newNode = new Node(s, n.dist+1);
			    			newNode.addPreNode(n);
			    			map.put(s, newNode);
			    			queue.add(s);
			    		} else {
			    			Node curr = map.get(s);
			    			if(curr.dist == n.dist+1) {
			    				curr.addPreNode(n);
			    			}
			    		}
	            		}
                              
			}
	}
	return ret;
    }
    
    public void getPaths(Node n, ArrayList<String> list, List<List<String>> ret) {		
		String s = n.str;
		List<Node> prelist = n.preNode;
		list.add(0, s);
		if(n.dist == 1) {
			ret.add(list);
			return;
		}
		for(Node prev : prelist) {
			getPaths(prev, new ArrayList<String>(list), ret);
		}
	}
 
    public Set<String> oneLetterDiffSet(String s) {
        Set<String> stringSet = new HashSet<String>();
        if(s == null || s.length() == 0) return stringSet;
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char[] arr = s.toCharArray();
            for(char ch = 'a'; ch <= 'z'; ch++) {
            	if(ch == c) continue;
            	arr[i] = ch;
            	stringSet.add(String.valueOf(arr));
            }
        }
        return stringSet;
    }
}
