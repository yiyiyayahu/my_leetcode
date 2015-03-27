/*
 The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case. 
*/

/*
开始没有特别理解，其实这个buf不是file，而是从file里面读n个character放到这个buf里面，然后read4是一次放4个而已。
要考虑中间有可能end of file的情况，就是n比file的size大
然后这里要用到System.arraycopy
*/

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        if(n==0) return 0;
        
        boolean eof = false;
        int readBytes = 0;
        char[] buffer = new char[4];
        
        while(!eof && readBytes < n) {
            int size = read4(buffer);
            if(size < 4) eof = true;
            
            int bytes = Math.min(n-readBytes, size);
            System.arraycopy(buffer, 0, buf, readBytes, bytes);
            readBytes += bytes;
        }
        return readBytes;
    }
}
