/*
Implement pow(x, n).
*/

/*
二分法
我之前写的是result = pow(x, n/2) * pow(x, n/2)。。。有点差啊。。。这重复调用多次pow(x,n/2)当然就time limit exceeded了
*/
public class Solution {
    public double pow(double x, int n) {
        double result = 1.0;
        boolean isNeg = false;
        if(n < 0) {
            isNeg = true;
            n = -n;
        }
        if(n == 0) return 1;
        
        double tmp = pow(x, n/2);
        if(n%2 == 0) result = tmp * tmp;
        if(n%2 == 1) result = tmp * tmp * x;
        
        if(isNeg) result = 1.0/result;
        return result;
    }
}
