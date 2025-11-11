package DynamicProgramming.KnapSack.LC_474_Ones_and_Zeros;
import java.util.Arrays;

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {

        int l = strs.length;
        int[][][] dp = new int[l][m+1][n+1];

        for( int i = 0 ; i < l ; i++ ){
            for( int j = 0 ; j <= m ; j++ ){
                Arrays.fill( dp[i][j] , -1 );
            }
        }
        
        return solve( 0 , strs , m , n , l , dp );

    }

    private int solve( int i , String[] strs , int m , int n , int l , int[][][] dp ){

        if( i == l ){
            return 0;
        }
        if( dp[i][m][n] != -1 ) return dp[i][m][n];

        int pick = 0 , nopick = 0;
        if( check( strs[i] , m , n ) ){
            int[] arr = findCount(strs[i]);
            pick = 1 + solve( i+1 , strs , m - arr[0], n - arr[1] , l , dp );
        }
        nopick = solve( i+1 , strs , m , n , l , dp );

        return dp[i][m][n] = Math.max( pick,nopick );

    }

    private boolean check( String str , int m , int n ){
        int[] arr = findCount(str);
        return ( arr[0] <= m && arr[1] <= n );
    }

    private int[] findCount( String str ){
        int o = 0 , z = 0;
        int n = str.length();
        for( int i = 0 ; i < n ; i++ ){
            if( str.charAt(i) == '1' ) o++;
            else z++;
        }
        return new int[]{z,o};
    }
}