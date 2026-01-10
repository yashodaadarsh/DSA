package POTD.LeetCode2026.January.LC_712_MinimumASCIIDeleteSumforTwoStrings;
import java.util.*;

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m][n];
        for( int i = 0 ; i < m ; i++ )
            Arrays.fill( dp[i] , -1 );
        return solve( 0 , 0 , m , n , s1 , s2 , dp );
    }
    private int solve( int i , int j , int m , int n , String s1 , String s2 , int[][] dp ){

        if( i == m && j == n ){
            return 0;
        }

        if( i == m ){
            int delVal = 0;
            for( int k = j ; k < n ; k++ ) delVal += (int)s2.charAt(k);
            return delVal;
        }

        if( j == n ){
            int delVal = 0;
            for( int k = i ; k < m ; k++ ) delVal += (int)s1.charAt(k);
            return delVal;
        }

        if( dp[i][j] != -1 ) return dp[i][j];

        int deleteOne = (int)1e9 , deleteTwo = (int)1e9 , noDelete = (int)1e9;

        if( s1.charAt( i ) != s2.charAt( j ) ){
            deleteOne = ( (int)s1.charAt(i) ) + solve( i+1 , j , m , n , s1 , s2 , dp );
            deleteTwo = ( (int)s2.charAt(j) ) + solve( i , j+1 , m , n , s1 , s2 , dp );
        }
        else{
            noDelete = solve( i+1 , j+1 , m , n , s1 , s2 , dp );
        }


        return dp[i][j] = Math.min( deleteOne , Math.min( deleteTwo , noDelete ) );


    }


    public int minimumDeleteSumTabulation(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];

        for( int i = n-1 ; i >= 0 ; i-- ){
            dp[m][i] = dp[m][i+1] + (int)s2.charAt(i);
        }

        for( int i = m-1 ; i >= 0 ; i-- ){
            dp[i][n] = dp[i+1][n] + (int)s1.charAt(i);
        }

        for( int i = m-1 ; i >= 0 ; i-- ){
            for( int j = n-1 ; j >= 0 ; j-- ){
                int deleteS1 = (int)1e9 , deleteS2 = (int)1e9 , noDelete = (int)1e9;
                if( s1.charAt(i) != s2.charAt(j) ){
                    deleteS1 = (int)s1.charAt(i) + dp[i+1][j];
                    deleteS2 = (int)s2.charAt(j) + dp[i][j+1];
                }
                else{
                    noDelete = dp[i+1][j+1];
                }
                dp[i][j] = Math.min( noDelete , Math.min( deleteS1 , deleteS2 ) );
            }
        }
        return dp[0][0];
    }
}