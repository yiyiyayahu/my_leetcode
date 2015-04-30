  /*
  先写了一个对array的insertionSort
  开始理解不是特别对，不是swap，而是插入
  比如5,1,4,2,3->1,5,4,2,3->1,4,5,2,3->1,2,4,5,3->1,2,3,4,5
  到2这里，是把4，5推后，然后插入4的前面这样
  */
    public static void insertionSort(int[] arr) {
    	for(int i = 1; i < arr.length; i++) {
    		int tmp = arr[i];    		
    		int j = i;
    		while(j > 0 && arr[j-1] > tmp) {
    			arr[j] = arr[j-1];
    			j --;
    		}
    		arr[j] = tmp;
    	}
    }
