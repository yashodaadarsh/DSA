package Contests.LeetCode.WeeklyContest467;

class SubsequenceSumAfterCappingElements {

    //BruteForce
    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        int n = nums.length;
        boolean[] ans = new boolean[n];
        for( int i = 0 ; i < n ; i++ ){
            int[] arr = new int[n];
            for( int j = 0 ; j < n ; j++ ){
                arr[j] = Math.min( nums[j] , i+1 );
            }
            // Arrays.sort(arr);
            boolean isTrue = solve(arr,k);
            ans[i] = isTrue;
        }
        return ans;
    }
    boolean solve(int[] arr, int k) {
        int n = arr.length;
        boolean[] dp = new boolean[k + 1];
        dp[0] = true;

        for (int num : arr) {
            for (int s = k; s >= num; s--) {
                dp[s] = dp[s] || dp[s - num];
            }
        }

        return dp[k];

    }
}