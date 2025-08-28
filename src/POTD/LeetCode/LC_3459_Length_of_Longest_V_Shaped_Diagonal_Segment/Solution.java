package POTD.LeetCode.LC_3459_Length_of_Longest_V_Shaped_Diagonal_Segment;

import java.util.Arrays;

class Solution {
    // Directions in proper clockwise order
    private int[][] dirs = {
        {1, 1},   // d=0: Top-left → Bottom-right
        {1, -1},  // d=1: Top-right → Bottom-left
        {-1, -1}, // d=2: Bottom-right → Top-left
        {-1, 1}   // d=3: Bottom-left → Top-right
    };

    private int n, m;

    public int lenOfVDiagonal(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int ans = 0;

        // dp[r][c][d][canTurn] → longest path starting from (r,c) in dir d with canTurn=0/1
        int[][][][] dp = new int[n][m][4][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 4; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        // start only from cells containing 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        ans = Math.max(ans, 1 + dfs(i, j, grid, d, 2, true, dp));
                    }
                }
            }
        }
        return ans;
    }

    private int dfs(int r, int c, int[][] grid, int d, int nextVal, boolean canTurn, int[][][][] dp) {
        int nr = r + dirs[d][0];
        int nc = c + dirs[d][1];
        int turnInt = canTurn ? 1 : 0;

        // boundary or mismatch → stop
        if (nr < 0 || nr >= n || nc < 0 || nc >= m || grid[nr][nc] != nextVal) {
            return 0;
        }

        if (dp[nr][nc][d][turnInt] != -1) return dp[nr][nc][d][turnInt];

        int maxLen = 0;

        // continue straight
        maxLen = Math.max(maxLen,
                1 + dfs(nr, nc, grid, d, (nextVal == 2 ? 0 : 2), canTurn, dp));

        // one-time clockwise turn
        if (canTurn) {
            int newDir = (d + 1) % 4;
            maxLen = Math.max(maxLen,
                    1 + dfs(nr, nc, grid, newDir, (nextVal == 2 ? 0 : 2), false, dp));
        }

        return dp[nr][nc][d][turnInt] = maxLen;
    }
}
