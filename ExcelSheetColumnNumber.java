/*
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
*/

public class Solution {
    public int titleToNumber(String s) {
        int result = 0;
        if(s == null) return result;
        
        for(int i = 0; i < s.length(); i++) {
            result = result * 26 + toNum(s.charAt(i));
        }
        return result;
    }
    public int toNum(char c) {
        switch(c) {
            case 'A' : return 1;
            case 'B' : return 2;
            case 'C' : return 3;
            case 'D' : return 4;
            case 'E' : return 5;
            case 'F' : return 6;
            case 'G' : return 7;
            case 'H' : return 8;
            case 'I' : return 9;
            case 'J' : return 10;
            case 'K' : return 11;
            case 'L' : return 12;
            case 'M' : return 13;
            case 'N' : return 14;
            case 'O' : return 15;
            case 'P' : return 16;
            case 'Q' : return 17;
            case 'R' : return 18;
            case 'S' : return 19;
            case 'T' : return 20;
            case 'U' : return 21;
            case 'V' : return 22;
            case 'W' : return 23;
            case 'X' : return 24;
            case 'Y' : return 25;
            case 'Z' : return 26;
        }
        return 0;
    }
}
