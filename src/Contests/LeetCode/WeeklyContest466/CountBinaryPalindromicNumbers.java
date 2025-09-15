package Contests.LeetCode.WeeklyContest466;

class CountBinaryPalindromicNumbers {
    private int findNoBits( long n ){
        int bits = 0;
        while( n != 0 ){
            bits++;
            n /= 2;
        }
        return bits;
    }
    public int countBinaryPalindromes(long n) {
        int count = 0;
        int maxLen = findNoBits(n);
        if( maxLen == 0 ) return 1;
        for( int len = 1 ; len < maxLen ; len++ ){
            int half = (len+1)/2;
            count += Math.pow(2,half)/2;
            System.out.println( Math.pow(2,half)/2 );
        }
        // finding the number of possible number of maxLen
        int half = ( maxLen + 1 )/2;
        long start = 1 << ( half - 1 );
        long end = (  1 << half ) - 1;
        while( start <= end ){
            long mid = ( end - start )/2 + start;
            if( makepalindrome( mid , half ) <= n ){
                start = mid + 1;
            }
            else 
                end = mid -1;
        }
        int lenFromMaxLen = (int)( start - ( 1 << (half-1) ) );
        System.out.println( start );
        count += lenFromMaxLen;
        return count + 1;
    }
    long makepalindrome( long num , int len ){
        long temp = num , res = num;
        if( len % 2 != 0 ) temp /= 2;
        while( temp > 0 ){
            long rem = temp % 2;
            res = res*2 + rem; 
            temp /= 2;
        }
        return res;
    }
}