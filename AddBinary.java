public class Solution {
    public String addBinary(String a, String b) {
        if(a == null) return b; 
        if(b == null) return a;
        
        int la = a.length() - 1;
        int lb = b.length() - 1;
        
        int carry = 0, result = 0, na = 0, nb = 0;
        StringBuilder sb = new StringBuilder();
        
        while(la >= 0 && lb >= 0) {
            na = a.charAt(la) - '0';
            nb = b.charAt(lb) - '0';
            
            result = na ^ nb ^ carry;
            if(carry == 0) carry = na & nb;
            else carry &= na | nb;
            sb.append(result + "");
            
            la --;
            lb --;
        }
        
        if(la < 0 && lb >= 0) {
            while(lb >= 0) {
                if(carry == 0) sb.append(b.charAt(lb));
                else {
                    nb = b.charAt(lb) - '0';
                    result = nb ^ carry;
                    carry &= nb;
                    sb.append(result + "");
                }
                lb --;
            }
        }
        
        if(lb < 0 && la >= 0) {
            while(la >= 0) {
                if(carry == 0) sb.append(a.charAt(la));
                else {
                    na = a.charAt(la) - '0';
                    result = na ^ carry;
                    carry &= na;
                    sb.append(result + "");
                }
                la --;
            }
        }
        if(la < 0 && lb < 0 && carry ==1) sb.append(carry+"");
        return sb.reverse().toString();
    }
}
