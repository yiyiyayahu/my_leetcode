/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/

/*
这里要注意一下，如果stack.isEmpty()的话是要返回"/"的
*/
public class Solution {
    public String simplifyPath(String path) {
        String[] arr = path.split("/+");
        Stack<String> stack = new Stack<String>();

        for(int i = 0; i < arr.length; i++) {
        	if(arr[i].equals("") || arr[i].equals(".")) continue;
        	else if(arr[i].equals("..")) {
        		if(!stack.isEmpty()) stack.pop();
        	}
        	else stack.push(arr[i]);
        }
        StringBuilder sb = new StringBuilder();
        
        if(stack.isEmpty()) return "/";
        while(!stack.isEmpty()) {
        	sb.insert(0, "/" + stack.pop());
        }
        
        return sb.toString();
    }
}
