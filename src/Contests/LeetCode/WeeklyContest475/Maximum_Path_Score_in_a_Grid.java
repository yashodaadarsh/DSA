package Contests.LeetCode.WeeklyContest475;
import java.util.Arrays;

class Maximum_Path_Score_in_a_Grid {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][k+1];
        for( int i = 0 ; i < m ; i++ )
            for( int j = 0 ; j < n ; j++ )
               Arrays.fill(dp[i][j] , -1);
        dp[0][0][0] = 0;
        for( int i = 0 ; i < m; i++ ){
            for( int j = 0 ; j < n ; j++ ){
                for( int cost = 0 ; cost <= k ; cost++ ){
                    
                
                if(dp[i][j][cost] == -1 )continue;
                int bS = dp[i][j][cost];
                if(j+1<n){
                    int nc = cost + ( (grid[i][j+1] == 1 || grid[i][j+1] == 2 ) ? 1 : 0 );
                    if( nc <= k ){
                        dp[i][j+1][nc] = Math.max(dp[i][j+1][nc] , bS + grid[i][j+1]);
                    }
                }

                if( i +1 < m ){
                    int nc = cost + ( (grid[i+1][j] == 1 || grid[i+1][j] == 2 ) ? 1 : 0);
                    if( nc <= k ){
                        dp[i+1][j][nc] = Math.max(dp[i+1][j][nc] , bS + grid[i+1][j]);
                    }
                    
                }
                }
            }
        }

        int ans = -1;
        for( int cost = 0 ; cost <= k ; cost++ ){
            ans = Math.max(ans,dp[m-1][n-1][cost]);
        }
        return ans;
    }
}