/*
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. 
If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. 
If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, 
which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, 
or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. 
If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/

/*
当年以为很麻烦的题，现在还挺好的，一次就基本过了，撒花~~~ 但是code好乱，要精简一下
要注意一下Integer的limit： -2147483648， 2147483647
*/

public class Solution {
    public int myAtoi(String str) {
        if(str == null || str.length() == 0) return 0;
        
        int result = 0; boolean neg = false; int len = str.length();
        int i = 0;
        while(i < len && str.charAt(i) == ' ') {i++;}
        if(i < len && str.charAt(i) == '-') {neg = true; i++;}
        else if(i < len && str.charAt(i) == '+') {i++;}

        if(i == len || !isNum(str.charAt(i))) return 0;
        
        while(i < len && isNum(str.charAt(i))) {
        	int tmp = str.charAt(i) - '0';
        	int limit = (Integer.MAX_VALUE - tmp) / 10;
        	if(result >= limit) {
        		if(!neg) return Integer.MAX_VALUE;
        		if(neg && result > limit) return Integer.MIN_VALUE;
        	}
        	result = result * 10;
            result += str.charAt(i) - '0'; 
            i++;
        }
        if(neg) return result * (-1);
        else return result;
    }
    public boolean isNum(char c) {
        if(c >= '0' && c <= '9') return true;
        return false;
    }
}


public class Solution {
    public int myAtoi(String str) {
        /*
        1. normal, only numbers (+/-)
        2. after several whitespaces : 
            1) followed by +/- numbers -> do the conversion: and ignore the additional characters afterwards
            2) followed by a invalid integral number/empty -> return 0
        3. number out of range: return Integer.MAX_VALUE/Integer.MIN_VALUE
        */
        if(str == null || str.length() == 0) return 0;
        
        int result = 0; boolean neg = false; int len = str.length();
        int i = 0;
        while(i < len && str.charAt(i) == ' ') {
            i++;
        }
        if(i == len) return 0;
        if(i < len) {
            char c = str.charAt(i);
            if(c == '-') {neg = true; i++;}
            else if(c == '+') {i++;}
            else if(!isNum(c)) return 0;
        }
        if(i == len || !isNum(str.charAt(i))) return 0;
        
        while(i < len && isNum(str.charAt(i))) {
        	int tmp = str.charAt(i) - '0';
        	if(!neg && result >= (Integer.MAX_VALUE - tmp) / 10) {
        		return Integer.MAX_VALUE;
        	}
        	if(neg && result > (Integer.MAX_VALUE - tmp) / 10) {
        		return Integer.MIN_VALUE;
        	}
        	result = result * 10;
            result += str.charAt(i) - '0'; 
            i++;
        }
        if(neg) return result * (-1);
        else return result;
    }
    public boolean isNum(char c) {
        if(c >= '0' && c <= '9') return true;
        return false;
    }
}
