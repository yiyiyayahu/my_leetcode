public class Solution {
    public int threeSumClosest(int[] num, int target) {
        if(num == null || num.length == 0) return Integer.MAX_VALUE;
    	
    	Arrays.sort(num);
    	int len = num.length;
    	int minDiff = Integer.MAX_VALUE;
    	
    	for(int i = 0; i < len - 2; i++) {
			int start = i+1, end = len-1;
			
			while(start < end) {
				int tmp = num[start] + num[end] + num[i];
				if(tmp == target) return target;
				
				int diff = tmp - target;
				if(minDiff == Integer.MAX_VALUE || Math.abs(diff) < Math.abs(minDiff)) {
					minDiff = diff;
				} 
				if(tmp < target) start ++;
				else if(tmp > target) end --;	
			}
    	}
    	int result = target + minDiff;
        return result;
    }
}
