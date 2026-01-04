package POTD.LeetCode2026.January.LC_1411_Number_of_Ways_to_Paint_NX3_Grid;

import java.util.*;

class Solution {
    private int MOD = (int)(1e9) + 7;
    public int numOfWays(int n) {
        char[][] grid = new char[n][3];
        char[] colors = new char[3];
        colors[0] = 'R';
        colors[1] = 'G';
        colors[2] = 'B';
        Map<String,Long> dp = new HashMap<>();
        return (int)solve(0,grid,n,colors,dp);
    }
    private long solve( int ind , char[][] grid , int n , char[] colors , Map<String,Long> dp ){
        if( ind == 3*n ){
            return 1;
        }

        int r = ind / 3;
        int c = ind % 3;

        String key = "";
        if( r > 0 ){
            key = r+""+grid[r-1][0]+grid[r-1][1]+grid[r-1][2];
        }

        if( r > 0 && c == 0 && dp.containsKey(key) ) return dp.get(key);

        long ans = 0;

        for( int i = 0 ; i < 3 ; i++ ){
            if( isPossible(r,c,grid,colors[i]) ){
                grid[r][c] = colors[i];
                ans = ( ans + solve( ind+1 , grid , n , colors , dp ) ) % MOD;
                grid[r][c] = ' ';
            }
        }
        if( r > 0 ) dp.put( key , ans );
        return ans;
    }

    private boolean isPossible( int r , int c , char[][] grid , char color ){

        int[][] dirs = { {-1,0} , {0,-1} };
        int n = grid.length;
        for( int i = 0 ; i < 2 ; i++ ){
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];
            if( nr >= 0 && nr < n && nc >= 0 && nc < 3 && grid[nr][nc] == color ){
                return false;
            }
        }
        return true;

    }
}