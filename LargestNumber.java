/*
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/

/*
这道题开始想错了，把数字全部拆开了。但是34和30是要连在一起的呀
然后这道题比较巧妙的做法是，只要看s1+s2大还是s2+s1大就好了，比如比较34和3的话，就看343和334
s2.compareTo(s1)这里要注意一下，compare函数返回正的还是负的我总是懵的。。。。
*/
public class Solution {
    public String largestNumber(int[] nums) {
    	if(nums == null || nums.length == 0) return "";
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	for(int i = 0; i < nums.length; i++) {
    		list.add(nums[i]);
    	}
    	Collections.sort(list, new Comparator<Integer>(){
    		public int compare(Integer a, Integer b) {
    			String s1 = String.valueOf(a) + String.valueOf(b);
    			String s2 = String.valueOf(b) + String.valueOf(a);
    			
    			return s2.compareTo(s1); //s1>s2 positive s1=343 s2=334 return 1
    		}
    	});
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < list.size(); i++) {    		
    		sb.append(list.get(i));
    	}
    	if(sb.charAt(0) == '0') return "0";
    	return sb.toString();
    }
}
