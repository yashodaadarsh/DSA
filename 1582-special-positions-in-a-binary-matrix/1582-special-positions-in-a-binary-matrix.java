class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int res = 0;
        for( int i = 0 ; i < m ; i++ ){
            for( int j = 0; j < n ; j++ ){
                if( mat[i][j] ==1 && isSpecial(i,j,mat) ){
                    res++;
                }
            }
        }

        return res;
    }
    boolean isSpecial( int r , int c , int[][] mat ){
        int m = mat.length;
        int n = mat[0].length;

        int rc = 0,cc = 0;
        for( int i = 0; i < m; i++ ){
            if( i == r ) continue;
            if( mat[i][c] == 1 ) rc++;
            if( rc > 0 ) return false;
        }


        for( int j = 0 ; j < n ; j++ ){
            if( j == c ) continue;

            if( mat[r][j] == 1 ) cc++;
            if( cc > 0 ) return false;

        }

        return true;
    }
}