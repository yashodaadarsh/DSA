package Contests.LeetCode.WeeklyContest482;
import java.util.Arrays;

// Link :- https://leetcode.com/problems/number-of-balanced-integers-in-a-range/

class NumberofBalancedIntegersinaRange {
    public long countBalanced(long low, long high) {
        return solve(high) - solve(low-1);
    }
    private long solve( long num ){
        if( num == 0 ) return 0;
        long test = num;
        int n = (int)Math.log10(num) + 1;
        int[] digits = new int[n];
        int l = 0;
        while( num > 0 ){
            digits[n-1-l] = (int)(num%10);
            num /= 10;
            l++;
        }

        long[][][][] dp = new long[n][2][73][73];
        for( int i = 0 ; i < n ; i++ )
            for( int j = 0 ; j < 2 ; j++ )
                for( int k = 0 ; k < 73 ; k++ )
                    Arrays.fill( dp[i][j][k] , -1 );

        return balanced( 0 , true , digits , 0 , 0 , dp );
    }
    private long balanced( int ind , boolean isLimit , int[] digits , int evenSum , int oddSum , long[][][][] dp ){
        if( ind == digits.length ){
            if( evenSum == 0 && oddSum == 0 ) return 0;
            if( evenSum == oddSum ) return 1;
            return 0;
        }

        if( dp[ind][ isLimit ? 1 : 0 ][evenSum][oddSum] != -1 ) return dp[ind][ isLimit ? 1 : 0 ][evenSum][oddSum];

        int limit = ( isLimit ) ? digits[ind] : 9;
        long ans = 0;
        for( int i = 0 ; i <= limit ; i++ ){
            boolean newLimit = ( isLimit && i == limit );
            int newEven = evenSum;
            int newOdd = oddSum;
            if( ind%2 == 0 ) newEven += i;
            else newOdd += i;
            ans += balanced( ind+1 , newLimit , digits , newEven , newOdd , dp );
        }
        return dp[ind][ isLimit ? 1 : 0 ][evenSum][oddSum] = ans;
    }
}