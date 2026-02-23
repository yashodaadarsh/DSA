class Solution {
    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m][n];

        for( int i = 0 ; i < m ; i++ ){
            char ch1 = word1.charAt(i);
            for( int j = 0 ; j < n ; j++ ){
                char ch2 = word2.charAt(j);
                if( ch1 == ch2 ){
                    dp[i][j] = 1 + ( ( i>0 && j>0 ) ? dp[i-1][j-1] : 0 );
                }
                else{
                    int sk1 = ( i>0 ) ? dp[i-1][j] : 0;
                    int sk2 = ( j>0 ) ? dp[i][j-1] : 0;
                    dp[i][j] = Math.max( sk1,sk2 );
                }
            }
        }

        int lcsLen = dp[m-1][n-1];

        return m + n - 2*lcsLen;
    }
}