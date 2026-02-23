class Solution {
    int m,n;
    public int numDistinct(String s, String t) {
        m = s.length();
        n = t.length();
        int[][] dp = new int[m+1][n+1];

        for( int i = 0; i <= m ; i++ ){
            dp[i][n] = 1;
        }

        for( int i = m-1 ; i >= 0 ; i-- ){
            for( int j = n-1 ; j >= 0 ; j-- ){
                char ch1 = s.charAt(i);
                char ch2 = t.charAt(j);
                if( ch1 == ch2 ){
                    dp[i][j] += dp[i+1][j+1];
                }
                dp[i][j] += dp[i+1][j];
            }
        }

        return dp[0][0];

        // for( int i = 0 ; i < m ; i++ ){
        //     Arrays.fill( dp[i] , -1 );
        // }
        // return solve( 0,0,s,t,dp );
    }
    private int solve( int i, int j, String s, String t, int[][] dp ){

        if( j == n ) return 1;

        if( i == m ) return 0;

        if( dp[i][j] != -1 ) return dp[i][j];

        char ch1 = s.charAt(i);
        char ch2 = t.charAt(j);

        int ways = 0;
        if( ch1 == ch2 ){
            ways = solve( i+1,j+1,s,t,dp );
        }

        ways += solve( i+1,j,s,t,dp );

        return dp[i][j] = ways;
    }
}