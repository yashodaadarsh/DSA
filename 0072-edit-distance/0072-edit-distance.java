class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1];

        /*
            Dp States
            j == n : return m - i;   // delete remaining word1
            i == m : return n - j;   // insert remaining word2

            if( ch1 == ch2 ) dp[i][j] = dp[i+1][j+1]
            else{
                a1 = 1 + dp[i][j+1] insertion
                a2 = 1 + dp[i+1][j+1] deletion
                a3 = 1 + dp[i+1][j] replace
            }
        */

        for( int i = 0; i <= m; i++ ){
            dp[i][n] = m - i;
        }

        for( int j = 0; j <= n ; j++ ){
            dp[m][j] = n - j;
        }

        for( int i = m-1; i >= 0; i-- ){
            for( int j = n-1; j >= 0; j-- ){
                char ch1 = word1.charAt(i);
                char ch2 = word2.charAt(j);
                if( ch1 == ch2 ){
                    dp[i][j] = dp[i+1][j+1];
                }
                else{
                    int a1 = 1 + dp[i][j+1];
                    int a2 = 1 + dp[i+1][j+1];
                    int a3 = 1 + dp[i+1][j];
                    dp[i][j] = Math.min( a1,Math.min(a2,a3) );
                }
            }
        }

        return dp[0][0];
    }
}