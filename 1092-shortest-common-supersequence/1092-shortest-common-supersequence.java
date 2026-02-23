class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m][n];

        for( int i = 0 ; i < m ; i++ ){
            char ch1 = str1.charAt(i);
            for( int j = 0 ; j < n ; j++ ){
                char ch2 = str2.charAt(j);
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

        StringBuilder sb = new StringBuilder();

        int i = m-1, j = n-1;
        while( i >= 0 && j >= 0 ){

            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(j);
            if( ch1 == ch2 ){
                sb.append( ch1 );
                i--; j--;
            }
            else if( ( i>0 ) && dp[i-1][j] >= dp[i][j] ){
                sb.append(ch1);
                i--;
            }
            else{
                sb.append(ch2);
                j--;
            }

        }

        while( i >= 0 ){
            char ch1 = str1.charAt(i);
            sb.append( ch1 );
            i--;
        }

        while( j >= 0 ){
            char ch2 = str2.charAt(j);
            sb.append( ch2 );
            j--;
        }

        return sb.reverse().toString();
    }
}