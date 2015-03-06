/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/

public class Solution {
    public String intToRoman(int num) {
        if(num == 0) return null;
    		
    	StringBuilder sb = new StringBuilder();
    	int unit = 1000;
    	
    	while(unit >= 1) {
    		int res = num/unit;
    		if(res <=3 && res >= 1) {
    			for(int i =0; i < res; i++) {
    				sb.append(getRoman(unit));
    			}
    		} else if(res >= 5 && res <= 8) {
    			sb.append(getRoman(unit*5));
    			for(int i = 0; i < res-5; i++) {
    				sb.append(getRoman(unit));
    			}
    		} else if(res == 4) {
    			sb.append(getRoman(unit));
    			sb.append(getRoman(unit*5));
    		} else if(res == 9) {
    			sb.append(getRoman(unit));
    			sb.append(getRoman(unit*10));
    		}
    		num = num % unit;
    		unit = unit/10;
    	}
    	return sb.toString();
    	
    }
    
    public char getRoman(int num) {
    	switch(num) {
    		case 1: 	return 'I';
    		case 5: 	return 'V';
    		case 10: 	return 'X';
    		case 50: 	return 'L';
    		case 100: 	return 'C';
    		case 500: 	return 'D';
    		case 1000: 	return 'M';
    		default: 	return ' ';
    	}
    }
}
