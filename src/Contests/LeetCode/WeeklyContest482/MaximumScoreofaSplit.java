package Contests.LeetCode.WeeklyContest482;

// Problem Link :- https://leetcode.com/problems/maximum-score-of-a-split/description/

class MaximumScoreofaSplit {
    public long maximumScore(int[] nums) {
        int n = nums.length;
        long[] suffixMin = new long[n];
        suffixMin[n-1] = nums[n-1];
        for( int i = n-2 ; i >= 0 ; i-- ){
            suffixMin[i] = Math.min( suffixMin[i+1] , nums[i] );
        }
        long prefix = 0 ;
        long ans = Long.MIN_VALUE;
        for( int i = 0 ; i < n-1  ; i++ ){
            prefix += nums[i];
            ans = Math.max( ans , prefix - suffixMin[i+1] );
        }
        return ans;
    }
}
