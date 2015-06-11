/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, 
for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
*/

/*
挺简单的，其实相当于自己实现一个hashCode，把长度为10的string转化成integer，比较方便。如何实现hashCode要注意一下
这里是比如A-00 C-01 G-10 T-11，相当于2位表示一个字母，一共就是20位<32，在Integer的范围里
然后注意bit的操作 hash += (hash<<2) | convertToInt(s.charAt(i));
hash先前移两位让后与第i位的数字或，就可以了
*/
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list = new ArrayList<String>();
        if(s == null || s.length() < 10) return list;
        
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i+10);
            int hash = hash(sub);
            if(set.contains(hash) && !list.contains(sub)) list.add(sub);
            else set.add(hash);
        }
        return list;
    }
    
    public int hash(String s) {
        int hash = 0;
        for(int i = 0; i < s.length(); i++) {
            hash = (hash<<2) | convertToInt(s.charAt(i));
        }
        return hash;
    }
    public int convertToInt(char c) {
        switch(c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default: return 0;
        }
    }
}
