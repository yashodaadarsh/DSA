package POTD.LeetCode2026.January.LC_1458_MaxDotProductofTwoSubsequences;
import java.util.*;

class Solution {
    private int n , m ;
    public int maxDotProduct(int[] nums1, int[] nums2) {
        n = nums1.length ;
        m = nums2.length;
        int[][] dp = new int[n][m]; 
        for( int i = 0 ; i < n ; i++ )
            Arrays.fill( dp[i] , -1 );
        return solve( 0,0,nums1,nums2,dp );
    }
    private int solve( int i , int j , int[] nums1 , int[] nums2 , int[][] dp ){
        if( i >= n || j >= m ){
            return (int) -1e6;
        }
        if( dp[i][j] != -1 ) return dp[i][j];
        int ans1 = nums1[i]*nums2[j];
        int ans2 = ans1 + solve( i+1 , j+1 , nums1 , nums2 , dp );
        int ans3 = solve( i , j+1 , nums1 , nums2 , dp );
        int ans4 = solve( i+1 , j , nums1 , nums2 , dp );
        return dp[i][j] = Math.max( ans1 , Math.max( ans2 , Math.max( ans3 , ans4 ) ) );
    }
    public int maxDotProductTabulation(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int res = Integer.MIN_VALUE;
        int[][] dp = new int[n][m];
        for( int i = 0 ; i < n ; i++ ){
            for( int j = 0 ; j < m ; j++ ){
                int ans1 = nums1[i]*nums2[j];
                int ans2 = Integer.MIN_VALUE;
                if( j > 0 && i > 0 )
                    ans2 = ans1 + dp[i-1][j-1];
                int ans3 = Integer.MIN_VALUE;
                if( j > 0 )
                    ans3 = dp[i][j-1];
                int ans4 = Integer.MIN_VALUE;
                if( i > 0 )
                    ans4 = dp[i-1][j];

                dp[i][j] = Math.max( ans1 , Math.max( ans2 , Math.max( ans3 , ans4 ) ) );
            }
        }
        return dp[n-1][m-1];
    }
}