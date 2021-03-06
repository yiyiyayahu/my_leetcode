/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
public class Solution {
    public void flatten(TreeNode root) {
	if(root == null) return;
	Stack<TreeNode> s = new Stack<TreeNode>();
	s.push(root);
	TreeNode node = root;

	while(!s.isEmpty()) {
		TreeNode tmp = s.pop();
		if(tmp != root) {
			node.right = new TreeNode(tmp.val);
			node = node.right;
		}			
		if(tmp.right != null) s.push(tmp.right);
		if(tmp.left != null) s.push(tmp.left);
	}
	root.left = null;
    }
}
/*
这相当于遍历了两遍，应该可以inplace改的，再想想
我觉得要注意的是，如果我开始node = root, 然后马上node.left=null，那就相当于把root直接改掉了，后面的preorder traversal的结果也是错的
*/
public class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
    	List<Integer> list = preOrderTraversal(root);
    	TreeNode node = root; 

    	for(int i= 1; i < list.size(); i++) {
    		node.right = new TreeNode(list.get(i));
    		node = node.right;
    	}
    	root.left = null;
    }
	
	public List<Integer> preOrderTraversal(TreeNode root) {
		Stack<TreeNode> s = new Stack<TreeNode>();
		List<Integer> result = new ArrayList<Integer>();
		if(root == null) return result;
		
		s.push(root);

		while(!s.isEmpty()) {
			TreeNode tmp = s.pop();
			result.add(tmp.val);
			if(tmp.right != null) s.push(tmp.right);
			if(tmp.left != null) s.push(tmp.left);
		}
		return result;
 	}
}


/*
    public void flatten(TreeNode root) {
        if(root == null) return;
    	List<Integer> list = preOrderTraversal(root);
    	TreeNode node = root; 
    	node = node.right;

    	for(int i= 1; i < list.size(); i++) {
    		node = new TreeNode(list.get(i));
    		node = node.right;
    	}
    	root.left = null;
    }
    最开始是这样写的，怎么都不对，我觉得是上面node直接挪到node.right，所以就直接是null了，和之前的node连接就没有了
*/
