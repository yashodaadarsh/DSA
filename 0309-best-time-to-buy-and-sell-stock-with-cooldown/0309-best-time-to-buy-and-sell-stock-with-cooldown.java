class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n+2][2];


        for( int i = n-1 ; i >= 0 ; i-- ){
            dp[i][1] = Math.max( -prices[i] + dp[i+1][0], dp[i+1][1] );
            dp[i][0] = Math.max( prices[i] + dp[i+2][1], dp[i+1][0] );
        }

        int[] prev2 = new int[2];
        int[] prev1 = new int[2];

        for( int i = n-1 ; i >= 0 ; i-- ){

            int[] cur = new int[2];
            
            cur[1] = Math.max( -prices[i] + prev1[0], prev1[1] );
            cur[0] = Math.max( prices[i] + prev2[1], prev1[0] );

            prev2 = prev1;
            prev1 = cur;
        }


        return prev1[1];
    }
}