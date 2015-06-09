/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
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
