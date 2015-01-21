/*
 Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1]. 
*/

public class Solution {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(num == null || num.length == 0) return result;
        
        List<Integer> list = new ArrayList<Integer>();
        list.add(num[0]);
        result.add(list);
        
        List<List<Integer>> tmp = new ArrayList<List<Integer>>();
        
        for(int i = 1 ; i < num.length; i++) {
            int digit = num[i];
            
            for(List<Integer> l : result) {
                for(int j = 0; j <= l.size(); j++) {
                    List<Integer> arr = new ArrayList<Integer>(l);
                    arr.add(j, digit);
                    tmp.add(arr);
                }
            }
            result = tmp;
            tmp = new ArrayList<List<Integer>>();
        }
        return result;
    }
}
