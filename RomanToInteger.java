/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/

/*
Roman: IV (5-1) VI (5+1) III(1+1+1)
*/

public class Solution {
    public int romanToInt(String s) {
        if(s == null) return 0;
        
        int result = 0;
        int prev = 0;
        for(int i = 0; i < s.length(); i++) {
            int current = getNum(s.charAt(i));
            if(current > prev && prev != 0) {
                result += current - prev * 2;
            } else  {
                result += current;
            } 
            prev = getNum(s.charAt(i));
        }
        return result;
    }
    
    public int getNum(char c) {
        switch(c) {
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
            default: return 0;
        }
    }
}
