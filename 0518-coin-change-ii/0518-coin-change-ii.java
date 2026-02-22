class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        for( int s = 0 ; s <= amount ; s++ ){
            if( s%coins[0] == 0 ){
                dp[0][s] = 1;
            }
        }

        for( int i = 1; i < n; i++ ){
            for( int a = 0; a <= amount; a++ ){
                if( coins[i] <= a )
                    dp[i][a] += dp[i][a-coins[i]];
                dp[i][a] += dp[i-1][a];
            }
        }

        return dp[n-1][amount];
    }
}