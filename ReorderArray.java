/*
Given an array, move all the non-zero elements to the front
*/

/*
这道题看着简单，其实蛮难写对的。
首先，列case的时候要把corner case都考虑到：
  null
  [0]
  [0,0,0]
  [1]
  [1,1,1]
  [0,1,1,0]
举的这些test case要简短并且有代表性。不要让面试官提出一个test case，很有可能就是这个case你没有考虑到，pass不了，就比较严重了
其次，和面试官说明思路。这道题就是前面一个指针，后面一个指针，num[i]==0，num[j]!=0的情况下swap
开始这道题的code我是这样写的
while(i <= j) {
	while(i < len && nums[i] != 0) i++;
	while(j >= 0 && num[j] == 0) j--;
	if(i < j) {
  	swap(nums, i, j);
  	i ++; j --;
	}
}
这样code就很乱
后来铄姐说尽量不要while里面再套while，要加很多判断条件的
其实就改成if加个continue就蛮好
if(nums[i] != 0) {i ++; continue;}

以后写完code也要多多想想怎么优化
加油！
*/

  public static int reorder(int[] nums) {
    	if(nums == null || nums.length == 0) return 0;
    	int len = nums.length;
    	int i = 0, j = len - 1;
    	while(i <= j) {
    		if(nums[i] != 0) {i ++; continue;}
    		if(nums[j] == 0) {j --; continue;}
    		
    		swap(nums, i, j);
    		i ++; j --;
    	}
    	return i;
    }
	public static void swap(int[] array, int i, int j) {
		int tmp = 0;
		tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
