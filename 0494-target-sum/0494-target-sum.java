class Solution {
    public int findTargetSumWays(int[] nums, int diff) {
        int n = nums.length;
        int total = Arrays.stream( nums ).sum();
        if( total < diff || ( total - diff )%2 != 0 ) return 0;

        int target = ( total - diff ) / 2;

        int[][] dp = new int[n][target+1];
        dp[0][0] = 1;
        if( nums[0] <= target ){
            dp[0][ nums[0] ] += 1;
        }

        for( int i = 1 ; i < n ; i++ ){
            // dp[i][0] = 1;
            for( int s = 0 ; s <= target ; s++ ){
                if( nums[i] <= s ){
                    dp[i][s] += dp[i-1][s-nums[i]];
                }
                dp[i][s] += dp[i-1][s];
            }
        } 

        return dp[n-1][target];
    }
}