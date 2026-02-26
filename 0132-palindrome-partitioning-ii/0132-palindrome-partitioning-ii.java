class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n+1];

        for( int i = n-1; i >= 0; i-- ){

            int cuts = n;
            for( int k = i; k < n; k++ ){
                if( isPalindrome( i,k,s ) ){
                    int val = 1 + dp[k+1];
                    cuts = Math.min( cuts,val );
                }
            }

            dp[i] = cuts;
        }

        return dp[0] - 1;
        // Arrays.fill( dp,-1 );
        // return solve( 0,n,s,dp ) - 1;
    }

    private int solve( int i, int n, String s, int[] dp ){

        if( i == n ) return 0;
        if( dp[i] != -1 ) return dp[i];

        int cuts = n;

        for( int k = i; k < n; k++ ){
            if( isPalindrome( i,k,s ) ){
                int val = 1 + solve( k+1,n,s,dp );
                cuts = Math.min( cuts,val );
            }
        }

        return dp[i] = cuts;
    }

    private boolean isPalindrome( int i, int j, String s ){
        int n = s.length();
        while( i <= j ){
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            if( ch1 != ch2 ) return false;
            i++; j--;
        }
        return true;
    }
}