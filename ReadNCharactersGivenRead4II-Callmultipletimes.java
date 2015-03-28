/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.
*/

/*
这道题的题目意思一直不理解
其实呢，是这样的，比如abcdefg对吧，我第一次调read(buf,1)的时候，因为里面调用了read4，虽然只输出1，但是实际上read4的这个指针已经
到了e，也就是buffer里面有abcd，只是我只取出来a而已，那第二次调read(buf,1)的话，应该输出b而不是e
注意要declare some instance variables
*/
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
     
    private char[] buffer = new char[4];
    private int offset = 0;
    private int bufsize = 0;
    private boolean eof = false;

    public int read(char[] buf, int n) {
        if(n == 0) return 0;
        int readBytes = 0;
        
        if(bufsize > 0) {
            int buffBytes = Math.min(bufsize - offset, n);
            System.arraycopy(buffer, offset, buf, 0, buffBytes);
            if(n < bufsize - offset) {
                offset += buffBytes;
            } else {
                bufsize = 0; 
                offset = 0;
            }
            readBytes += buffBytes;
        }
        while(!eof && readBytes < n) {
            int size = read4(buffer);
            if(size < 4) eof = true;
            int bytes = Math.min(n-readBytes, size);
            if(bytes < size) {
                bufsize = size;
                offset = bytes;
            }
            System.arraycopy(buffer, 0, buf, readBytes, bytes);
            readBytes += bytes;  
        }
        return readBytes;
    }
}
