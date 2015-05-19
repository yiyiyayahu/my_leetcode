/*
Description:

Count the number of prime numbers less than a non-negative number, n

click to show more hints.

References:
How Many Primes Are There?

Sieve of Eratosthenes： http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
*/

/*
用提示里面的链接，Sieve of Eratosthenes算法
1）p=2, 然后把2p,3p,4p....的都cross掉，因为肯定不是prime
2）p更新成比p大的prime，重复上面操作，直到p>=n
3）看还剩下哪些没有被cross掉的，count一下就是最终一共有多少个prime
注意：
1）要审题，题目要求less than，我开始理解为了<=n，所以最开始初始化了n+1长度的boolean
2）我开始是用的isPrime[]数组，也就是不是prime的时候设成false。可是要注意数组初始化就都是false啊，这样显然是不行的
3）p++后check notPrime[p]的时候，我要判断一下当前的p这个index是不是valid的，不然就又出现ArrayIndexOutOfBoundsException
*/
public class Solution {
    public int countPrimes(int n) {
        if(n == 0 || n == 1) return 0;
        boolean[] notPrime = new boolean[n];
        notPrime[0] = true; notPrime[1] = true;
        int p = 2;
        while(p < n) {
            for(int i = 2; i < (n%p>0 ? n/p+1:n/p); i++) {
                notPrime[i * p] = true;
            }
            p ++;
            if(p < n && notPrime[p] == true) {p++; continue;}
        }
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(notPrime[i] == false) count ++;
        }
        return count;
    }
}
