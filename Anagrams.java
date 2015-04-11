/*
Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case
*/

/*
开始想用下面的那个isAnagram的函数。但是会出现对于特别长的字符串遍历的TLE，因为我每两个都要比较一下
然后下面的这个解法用到HashMap，可能空间复杂度就不怎么好，把string sort之后的当成key，value是对应的index的list
当这个key对应的value的size大于1的时候加到list里面

看到网上有人用hash值来弄，可以试试。
*/
public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> result = new ArrayList<String>();
        if(strs.length == 0) return result;
        
        HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        for(int i = 0; i < strs.length; i++) {
            String s = strs[i];
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if(map.containsKey(key)) {
                ArrayList<Integer> list = map.get(key);
                list.add(i);
                map.put(key, list);
            } else {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(key, list);
            }
        }
        
        for(String key : map.keySet()) {
            if(map.get(key).size() > 1) {
                ArrayList<Integer> list = map.get(key);
                for(int i : list) result.add(strs[i]);
            }
        }
        return result;
    }
}


    
    public boolean isAnagram(String s1, String s2) {
        if(s1 == null || s2 == null) return false;
        
        int len1 = s1.length(), len2 = s2.length();
        if(len1 != len2) return false;
        
        int[] arr = new int[256];
        for(int i = 0; i < len1; i++) {
            arr[s1.charAt(i)] ++;
        }
        
        for(int i = 0; i < len2; i++) {
            char c = s2.charAt(i);
            if(arr[c] == 0) return false;
            else arr[c]--;
        }
        return true;
    }
