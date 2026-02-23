class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        Boolean[][] dp = new Boolean[m][n];
        return solve( 0,0,m,n,s,p,dp );
    }
    private boolean solve( int i, int j, int m, int n, String s, String p, Boolean[][] dp ){

        if (j == n) return i == m;

        if (i == m) {
            for (int k = j; k < n; k++) {
                if (p.charAt(k) != '*') return false;
            }
            return true;
        }

        if( dp[i][j] != null ) return dp[i][j];
        
        char ch1 = s.charAt(i);
        char ch2 = p.charAt(j);
        boolean equal = false, star = false, qMark = false;
        if( ch1 == ch2 || ch2 == '?' ){
            equal = solve( i+1, j+1, m, n, s, p, dp );
        }
        else if( ch2 == '*' ){
            star = solve( i,j+1,m,n,s,p,dp ) || solve( i+1,j,m,n,s,p,dp );
        }

        return dp[i][j] = equal || star || qMark ;
    }
}