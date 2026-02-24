class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n+2][2];


        for( int i = n-1 ; i >= 0 ; i-- ){
            dp[i][1] = Math.max( -prices[i] + dp[i+1][0], dp[i+1][1] );
            dp[i][0] = Math.max( prices[i] - fee + dp[i+1][1], dp[i+1][0] );
        }

        int[] prev = new int[2];

        for( int i = n-1 ; i >= 0 ; i-- ){

            int[] cur = new int[2];
            
            cur[1] = Math.max( -prices[i] + prev[0], prev[1] );
            cur[0] = Math.max( prices[i] - fee + prev[1], prev[0] );

            prev = cur;
        }


        return prev[1];
    }
}