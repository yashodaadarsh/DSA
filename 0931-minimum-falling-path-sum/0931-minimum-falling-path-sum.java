class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[] bottom = new int[n];
        for( int i = 0 ; i < n ; i++ ){
            bottom[i] = matrix[n-1][i];
        }

        for( int i = n-2 ; i >= 0 ; i-- ){
            int[] current = new int[n];
            for( int j = 0 ; j < n ; j++ ){
                int dia1 = 10000000,dia2 = 10000000,down = 10000000;
                if( j-1 >= 0 ) dia1 = bottom[j-1];
                if( j+1 < n ) dia2 = bottom[j+1];
                down = bottom[j];
                current[j] = matrix[i][j] + Math.min( dia1, Math.min( dia2,down ) );
            }

            bottom = current;
        }

        int ans = 10000000;
        for( int i = 0 ; i < n ; i++ ){
            ans = Math.min( bottom[i] , ans );
        }

        return ans;
    }
}