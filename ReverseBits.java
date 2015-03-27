/*
Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/

uint32_t reverseBits(uint32_t n) {
    uint32_t result = 0;
    int digits = 32;
    while(n != 0) {
        result = result << 1;
        if( (n | 1) == n) {
            result += 1;
        }
        n  = n >> 1;
        digits --;
    }
    for(int i = 0; i < digits; i++) {
        result = result << 1;
    }
    return result;
}
