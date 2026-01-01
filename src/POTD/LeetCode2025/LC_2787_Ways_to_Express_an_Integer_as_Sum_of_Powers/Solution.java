package POTD.LeetCode2025.LC_2787_Ways_to_Express_an_Integer_as_Sum_of_Powers;

import java.util.Arrays;

class Solution {
    private static final int MOD = (int) 1e9 + 7;

    // Fast power function: calculates a^b
    private long pow(int base, int exponent) {
        if (exponent == 0) return 1;
        long half = pow(base, exponent / 2);
        long result = half * half;
        if ((exponent & 1) == 1) result *= base;
        return result;
    }

    public int numberOfWays(int n, int x) {
        // dp[remainingSum][currentNum] = number of ways to form 'remainingSum' using numbers >= currentNum
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1); // -1 means not computed yet
        }
        return solve(n, 1, x, dp);
    }

    private int solve(int remaining, int currentNum, int power, int[][] dp) {
        // Base case: exact sum reached
        if (remaining == 0) return 1;

        // Calculate currentNum^power
        long currentPowerValue = pow(currentNum, power);

        // Base case: exceeded target or number too large
        if (currentPowerValue > remaining) return 0;

        // If already computed, return memoized value
        if (dp[remaining][currentNum] != -1) return dp[remaining][currentNum];

        // Option 1: Take currentNum^power
        int take = solve(remaining - (int) currentPowerValue, currentNum + 1, power, dp);

        // Option 2: Skip currentNum^power
        int skip = solve(remaining, currentNum + 1, power, dp);

        // Store and return result modulo MOD
        return dp[remaining][currentNum] = (int) (((long) take + skip) % MOD);
    }
}
