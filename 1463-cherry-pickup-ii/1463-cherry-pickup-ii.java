class Solution {
    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] bottom = new int[n][n];

        
        for( int c1 = 0 ; c1 < n ; c1++ ){
            for( int c2 = 0 ; c2 < n ; c2++ ){
                bottom[c1][c2] = (c1==c2) ? grid[m-1][c1] : grid[m-1][c1] + grid[m-1][c2];
            }
        }

        for( int row = m-2 ; row >= 0 ; row-- ){

            int[][] current = new int[n][n];

            for( int c1 = 0 ; c1 < n ; c1++ ){
                for( int c2 = 0 ; c2 < n ; c2++ ){

                    int cherries = 0;
                    if( c1 == c2 ) cherries = grid[row][c1];
                    else cherries = grid[row][c1] + grid[row][c2];

                    int best = 0;
                    for( int d1 = -1 ; d1 <= 1 ; d1++ ){
                        for( int d2 = -1 ; d2 <= 1 ; d2++ ){
                            if( c1+d1 < 0 || c1+d1 >= n || c2+d2 < 0 || c2+d2 >= n ) continue;
                            best = Math.max( best,bottom[c1+d1][c2+d2] );
                        }
                    } 

                    current[c1][c2] = cherries + best;                   
                }
            }

            bottom = current;
        }


        return bottom[0][n-1];

    }
}