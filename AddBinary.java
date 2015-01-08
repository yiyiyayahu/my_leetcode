/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/

public class Solution {
    public String addBinary(String a, String b) {
        if(a == null) return b; 
        if(b == null) return a;
        
        int la = a.length() - 1, lb = b.length() - 1;
        int carry = 0, result = 0, na = 0, nb = 0;
        StringBuilder sb = new StringBuilder();
        
        int maxLen = la > lb ? la : lb;
        
        while(maxLen >= 0) {
            if(la >= 0) na = a.charAt(la) - '0';
            else na = 0;
            if(lb >= 0) nb = b.charAt(lb) - '0';
            else nb = 0;
            
            result = na ^ nb ^ carry;
            if(carry == 0) carry = na & nb;
            else carry &= na | nb;
            
            sb.append(result == 0? '0':'1');
            
            maxLen --;
            la --;
            lb --;
        }

        if(la < 0 && lb < 0 && carry ==1) sb.append(carry+"");
        return sb.reverse().toString();
    }
}

/*Remember don't use (result + ""), since it will create a new string object each time*/
