class Solution {
    public long rob(int[] nums, int[] colors) {
        int n = nums.length;
        long[] dp = new long[n];
            Arrays.fill( dp , -1 );
        return solve( 0, n, nums, colors, dp );
    }
    private long solve( int ind, int n, int[] nums, int[] colors, long[] dp ){
        if( ind >= n ) return 0;
        if( dp[ind] != -1 ) return dp[ind];
        long equal = nums[ind];
        long pick = nums[ind];
        if( ind+1 < n && colors[ind] != colors[ind+1] ){
            pick += solve( ind+1, n, nums, colors, dp );
        } 
        else{
            equal += solve( ind+2, n, nums, colors, dp );
        }
        long nopick = solve(ind+1, n, nums,colors, dp );
        return dp[ind] = Math.max( equal, Math.max( pick,nopick ) );
    }
}