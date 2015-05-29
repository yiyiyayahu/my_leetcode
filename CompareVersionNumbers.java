/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
*/

/*
要注意1.0和1是相等的情况
*/
public class Solution {
    public int compareVersion(String version1, String version2) {
        if(version1 == null || version2 == null) return 0;
        if(version1.equals(version2)) return 0;
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        int len1 = arr1.length, len2 = arr2.length;
        for(int i = 0; i < Math.min(len1, len2); ) {
        	int a1 = Integer.parseInt(arr1[i]);
        	int a2 = Integer.parseInt(arr2[i]);
            if(a1 == a2) {
                i++;
            } else if (a1 < a2) {
                return -1;
            } else {
                return 1;
            }
        }
        if(len1 == len2) return 0;
        if(len1 > len2) {
            for(int i = len2; i < len1; i++) {
                if(Integer.parseInt(arr1[i]) != 0) return 1;
            }
            return 0;
        }
        else{
            for(int i = len1; i < len2; i++) {
                if(Integer.parseInt(arr2[i]) != 0) return -1;
            }
            return 0;
        }
    }
}
