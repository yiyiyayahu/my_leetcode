/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
*/

/*
开始的思路不好，想写一个add(String s1, String s2)的函数，但是复杂度就会很高，因为每两个string就要遍历一次
就是比如129
      *   3 
         27
         60
        300
开始是想着比如2的位置，乘出来的结果后面可以加一个0，1的位置，乘出来的结果后面加两个0，然后再add("27","60") add("87","300")

人家的想法就很好啊，反正都是按位乘嘛，然后把乘出来的结果放到应有的位置上就可以了
arr[i+j] += (n1.charAt(i)-'0') * (n2.charAt(j)-'0');

然后要注意把前面的多余的0去掉，因为可能乘出来的结果没有len1+len2这么长
*/
public class Solution {
    public String multiply(String num1, String num2) {
        String n1 = new StringBuilder(num1).reverse().toString(); 
        String n2 = new StringBuilder(num2).reverse().toString(); 
        
        int len1 = n1.length(), len2 = n2.length();
        int[] arr = new int[len1+len2];
        
        for(int i = 0; i < len1; i++) {
            for(int j = 0; j < len2; j++) {
                arr[i+j] += (n1.charAt(i)-'0') * (n2.charAt(j)-'0');
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            int digit = arr[i]%10;
            int carry = arr[i]/10;
            if(i + 1 < arr.length) {
                arr[i+1] += carry;
            }
            sb.insert(0, digit);
        }
        
        while(sb.charAt(0) == '0' && sb.length() > 1) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
