class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] next = new int[n];

        for( int i = m-1 ; i >= 0 ; i-- ){

            int[] cur = new int[n];
            for( int j = n-1 ; j>= 0 ; j-- ){

                if( obstacleGrid[i][j] == 1 ) continue;
                if( i == m-1 && j == n-1 ) {
                    cur[j] = 1;
                    continue;
                }

                int bottom = 0 , right = 0;
                if( j+1 < n && obstacleGrid[i][j+1] != 1 ) right = cur[j+1];
                if( i+1 < m && obstacleGrid[i+1][j] != 1 ) bottom = next[j];

                cur[j] = right + bottom;
            }

            next = cur;
        }

        return next[0];
    }
}