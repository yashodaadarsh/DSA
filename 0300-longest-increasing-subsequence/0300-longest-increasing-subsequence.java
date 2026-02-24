class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n+1];
        Arrays.fill( dp,1 );
        int max = 1;

        for( int i = 0; i < n; i++ ){
            for( int j = 0; j < i; j++ ){
                if( nums[j] < nums[i] ){
                    dp[i] = Math.max( dp[i], 1 + dp[j] );
                    max = Math.max( max,dp[i] );
                }
            }
        }

        return max;

        // int[][] dp = new int[n][n+1];
        // for( int i = 0; i < n; i++ ){
        //     Arrays.fill( dp[i],-1 );
        // }
        // return solve(0,-1,nums,n,dp );
    }
    private int solve( int ind, int prev, int[] nums, int n, int[][] dp ){
        if( ind == n ){
            return 0;
        }

        if( dp[ind][prev+1] != -1 ) return dp[ind][prev+1];

        int pick = 0;

        if( prev == -1 || nums[prev] < nums[ind] ){
            pick = 1 + solve( ind+1,ind,nums,n,dp );
        }

        int nopick = solve( ind+1, prev, nums, n, dp );
        
        return dp[ind][prev+1] = Math.max( pick,nopick );
    }
}