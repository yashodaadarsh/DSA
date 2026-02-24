class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int k = 2;
        int[][][] dp = new int[n+2][2][k+1];

        // for( int i = 0; i < n; i++ ){
        //     for( int j = 0; j < k; j++ )
        //         Arrays.fill( dp[i][j] ,-1 );
        // }

        for( int i = n-1; i >= 0; i-- ){
            for( int lt = 1; lt <= k; lt++ ){
                dp[i][1][lt] = Math.max( -prices[i] + dp[i+1][0][lt], dp[i+1][1][lt] );
                dp[i][0][lt] = Math.max( prices[i] + dp[i+1][1][lt-1], dp[i+1][0][lt] );
            }
        }

        return dp[0][1][k];
    }

    private int solve( int ind, int n, int buy, int k, int[] prices, int[][][] dp ){
        if( k == 0 ){
            return 0;
        }
        if( ind == n ){
            return 0;
        }
        if( dp[ind][buy][k] != -1 ) return dp[ind][buy][k];

        int profit = 0;
        if( buy == 1 ){
            profit = Math.max( -prices[ind] + solve( ind+1,n,0,k,prices,dp ), 
                    solve( ind+1,n,1,k,prices,dp ) );
        }
        else{
            profit = Math.max( profit, Math.max( prices[ind] + solve( ind+1,n,1,k-1,prices,dp ),
                    solve( ind+1,n,0,k,prices,dp ) ) );
        }

        return dp[ind][buy][k] = profit;
    }
}