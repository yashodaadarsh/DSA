class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for( int i = 0; i < n; i++ ){
            dp[i][i] = 1;
        }

        for( int i = n-1; i >= 0 ; i-- ){
            for( int j = 0; j < n; j++ ){
                if( i >= j ) continue;
                char ch1 = s.charAt(i);
                char ch2 = s.charAt(j);
                int pick = 0;
                if( ch1 == ch2 ){
                    pick = 2 + dp[i+1][j-1];
                }
                int nopick = Math.max( dp[i+1][j] , dp[i][j-1] );
                dp[i][j] = Math.max( pick,nopick );
            }
        }

        return n - dp[0][n-1];
    }
}