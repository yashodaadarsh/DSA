class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for( int i = m-1 ; i >= 0 ; i-- ){
            for( int j = n-1 ; j >= 0 ; j-- ){
                char ch1 = text1.charAt(i);
                char ch2 = text2.charAt(j);
                int p1 = 0, p2 = 0, p3 = 0;
                if( ch1 == ch2 ){
                    p1 = 1 + dp[i+1][j+1];
                }
                p2 = dp[i+1][j];
                p3 = dp[i][j+1];
                dp[i][j] = Math.max( p1,Math.max(p2,p3) );
            }
        }

        return dp[0][0];
        
    }
}