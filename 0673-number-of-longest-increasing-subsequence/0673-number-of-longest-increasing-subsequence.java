class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        Arrays.fill( dp,1 );
        int maxLen = 0;
        for( int i = 0; i < n; i++ ){
            cnt[i] = 1;
            for( int j = 0; j < i; j++ ){
                if( nums[j] < nums[i] ){
                    if( dp[i] < 1 + dp[j] ){
                        dp[i] = 1 + dp[j];
                        cnt[i] = cnt[j];
                    }
                    else if( dp[i] == 1 + dp[j] ){
                        cnt[i] += cnt[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        int ans = 0;
        for( int i = 0; i < n; i++ ){
            if( dp[i] == maxLen ){
                ans += cnt[i];
            }
        }
        return ans;
    }
}