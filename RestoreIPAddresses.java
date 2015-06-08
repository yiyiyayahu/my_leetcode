/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter) 
*/

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<String>();
        if(s == null) return list;
        int len = s.length();
        if(len > 12 || len < 4) return list;

        helper(s, 0, new ArrayList<String>(), list);
        return list;
    }

    public void helper(String s, int index, ArrayList<String> ip, List<String> list) {
        int len = s.length();
        int num = ip.size();
        if(ip.size() == 4) {
            if(index == s.length()) {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < 4; i++) {
                    sb.append(ip.get(i));
                    if(i != 3) sb.append(".");
                }
                list.add(sb.toString());
            }
            return;
        }
        if( (len-index) > (4-num)*3 || (len-index) < (4-num) ) return;
        
        for(int i = 1; i <= 3 && index + i <= len; i++) {
            String tmp = s.substring(index, index+i);
            if(isValid(tmp)) {
                ArrayList<String> l = new ArrayList<String>(ip);
                l.add(tmp);
                helper(s, index+i, l, list);
            }
        }
    }

    public boolean isValid(String s) {
        if(s.length() > 3) return false;
        int number = s.charAt(0) - '0';
        if(number == 0) {
            if(s.length() == 1) return true;
            else return false;
        }
        for(int i = 1; i < s.length(); i++) {
            number = number*10;
            number += s.charAt(i) - '0';
        }
        return number<256;
    }    
}
