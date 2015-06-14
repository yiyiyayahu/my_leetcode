/*
There are two sorted arrays nums1 and nums2 of size m and n respectively. 
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/

/*
啊啊啊啊啊，这道题好难啊，做了半天做不出。。。
思路大概知道的，但其实代码也不是很好写
明天一定要自己独立写出来啊
下面这个是别人的blog里面转来的
http://www.cnblogs.com/springfor/p/3861890.html
*/

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m+n;
        if (total%2 != 0)
            return (double) findKth(nums1, 0, m-1, nums2, 0, n-1, total/2+1);
        else {
            double x = findKth(nums1, 0, m-1, nums2, 0, n-1, total/2);
            double y = findKth(nums1, 0, m-1, nums2, 0, n-1, total/2+1);
            return (double)(x+y)/2;
        }
    }
 
    public int findKth(int[] A, int astart, int aend, int[] B, int bstart, int bend, int k) {
        int m = aend - astart + 1;
        int n = bend - bstart + 1;
        
        if(m>n)
            return findKth(B,bstart,bend,A,astart,aend,k);
        if(m==0)
            return B[k-1];
        if(k==1)
            return Math.min(A[astart],B[bstart]);
        
        int partA = Math.min(k/2,m);
        int partB = k - partA;
        if(A[astart+partA-1] < B[bstart+partB-1])
            return findKth(A,astart+partA,aend,B,bstart,bend,k-partA);
        else if(A[astart+partA-1] > B[bstart+partB-1])
            return findKth(A,astart,aend,B,bstart+partB,bend,k-partB);
        else
            return A[astart+partA-1];
 	}
}
