/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

public class Solution {
    public String convert(String s, int nRows) {
        if(s == null || s.length() == 0 || s.length() == 1) return s;
        
        int nCols = 0;
        int length = s.length();
        int numInCol = 2 * nRows - 2;
        
        if(numInCol == 0) return s;
        if(length % numInCol > nRows) nCols = length/numInCol * 2 + 2;
        else if(length % numInCol > 0) nCols = length/numInCol * 2 + 1;
        else nCols = length/numInCol * 2;
        
        char c;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nRows; i++) {
            for(int j = 0; j < nCols; j++) {
                int redIndex = numInCol* (j/2) + i;
                int greenIndex = numInCol * (j/2+1) -i;

                if(j%2 == 0 && redIndex < length) {               	
                	c = s.charAt(redIndex);
                	sb.append(c);
                }else if(i != 0 && i != nRows - 1 && greenIndex < length) {
                	c = s.charAt(greenIndex);
                	sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}


/*
"PAYPALISHIRING" N = 3
P   A   H   N         0   4   8    12
A P L S I I G     ->  1 3 5 7 9 11 13
Y   I   R             2   6   10

N = 5
P        H           0        8
A     S  I           1     7  9
Y   I    R       ->  2   6    10
P L      I G         3 5      11  13
A        N           4        12

这道题好多细节都没有考虑到
开始是redIndex < length和greenIndex < length这两个条件
后来是那个length算了好久
        if(length % numInCol > nRows) nCols = length/numInCol * 2 + 2;
        else if(length % numInCol > 0) nCols = length/numInCol * 2 + 1;
        else nCols = length/numInCol * 2;
就比如N=5的时候，那其实单纯的nCols = length/numInCol * 2 + 1;是不对的
因为如果numInCol是8的话，要考虑到余数大于5小于8有两列的情况
*/
