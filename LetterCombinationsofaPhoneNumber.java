/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
*/

public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if(digits == null || digits.length() == 0) return result;
        
        return helper(digits, digits.length() - 1);
    }
    public List<String> helper(String digits, int index) {
        List<String> result = new ArrayList<String>();
        if(index < 0 || index >= digits.length() ) return result;
        
        char curr = digits.charAt(index);
        List<String> numList = getListByNum(curr);
        if(index == 0) return numList;
        
        List<String> preList = helper(digits, index - 1);
        if(numList.size() == 0) return preList;
        if(preList.size() == 0) return numList;
        
        for(int i = 0; i < numList.size(); i++) {
            String c = numList.get(i);
            for(int j = 0; j < preList.size(); j++) {
                String s = preList.get(j);
                StringBuilder sb = new StringBuilder(s);
                sb.append(c);
                result.add(sb.toString());
            }
        }
        return result;      
    }
    public List<String> getListByNum(char c) {
        String[] lists = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    	int num = Character.getNumericValue(c);
    	String s = lists[num];
    	List<String> list = new ArrayList<String>();
    	if(s.equals("")) return list;
    	
    	for(int i = 0; i < s.length(); i++) 
    		list.add(String.valueOf(s.charAt(i)));
    	return list;
    }
}
