class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] nextRow = new int[n];
        for( int i = m-1 ; i >= 0 ; i-- ){

            int[] curRow = new int[n];

            for( int j = n-1 ; j >= 0 ; j-- ){
                if( i == m-1 && j == n-1 ){
                    curRow[j] = grid[i][j];
                    continue;
                }
                int right = ( j+1 < n ) ? curRow[j+1] : 500000;
                int bottom = ( i+1 < m ) ? nextRow[j] : 500000 ;
                curRow[j] = grid[i][j] + Math.min( right, bottom );
            }

            nextRow = curRow;

        }

        return nextRow[0];
        
    }
}