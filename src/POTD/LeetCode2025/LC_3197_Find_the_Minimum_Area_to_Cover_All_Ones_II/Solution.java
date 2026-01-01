package POTD.LeetCode2025.LC_3197_Find_the_Minimum_Area_to_Cover_All_Ones_II;

class Solution {

    private int minimumArea( int rowStart , int rowEnd , int colStart , int colEnd , int[][] grid ){

        int m = grid.length;
        int n = grid[0].length;

        int minRow = m ;
        int maxRow = -1 ;
        int minCol = n ; 
        int maxCol = -1 ; 

        for( int i = rowStart ; i < rowEnd ; i++ ){
            for( int j = colStart ; j < colEnd ; j++ ){
                if( grid[i][j] == 1 ){
                    minRow = Math.min( minRow , i );
                    minCol = Math.min( minCol , j );

                    maxRow = Math.max( maxRow , i );
                    maxCol = Math.max( maxCol , j );
                }
            }
        }

        return ( maxRow - minRow + 1 ) * ( maxCol - minCol + 1 );
    }
    private int[][] rotateClockWise( int[][] grid ){
        int m = grid.length;
        int n = grid[0].length;
        int[][] rotateGrid = new int[n][m];
        for( int i = 0 ; i < m ; i++ ){
            for( int j = 0 ; j < n ; j++ ){
                rotateGrid[j][m-1-i] = grid[i][j];
            }
        }
        return rotateGrid;
    }
    private int utility( int[][] grid ){

        int m = grid.length;
        int n = grid[0].length;
        int result = Integer.MAX_VALUE;

        //Case - 1 and rotateClockwise Case - 1
        for( int rowSplit = 1 ; rowSplit < m ; rowSplit++ ){
            for( int colSplit = 1 ; colSplit < n ; colSplit++ ){

                int top = minimumArea( 0 , rowSplit , 0 , n , grid );
                int bottomLeft = minimumArea( rowSplit , m , 0 , colSplit , grid );
                int bottomRight = minimumArea( rowSplit , m , colSplit , n , grid );
                result = Math.min( result , top + bottomLeft + bottomRight );

                // Case - 2 and rotateClockWise Case - 2
                int topLeft = minimumArea( 0 , rowSplit , 0 , colSplit , grid );
                int topRight = minimumArea( 0 , rowSplit , colSplit , n , grid );
                int bottom = minimumArea( rowSplit , m , 0 , n , grid );
                result = Math.min( result , topLeft + topRight + bottom );

            }
        }

        //Case - 3 and rotateClockWise Case - 3
        for( int rowSplit1 = 1 ; rowSplit1 < m ; rowSplit1++ ){
            for( int rowSplit2 = rowSplit1 + 1 ; rowSplit2 < m ; rowSplit2++ ){
                int top = minimumArea( 0 , rowSplit1 , 0 , n , grid );
                int middle = minimumArea( rowSplit1 , rowSplit2 , 0 , n , grid );
                int bottom = minimumArea( rowSplit2 , m , 0 , n , grid );
                result = Math.min( result , top + middle + bottom );
            }
        }

        return result;
        
    }
    
    public int minimumSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] rotateGrid = rotateClockWise(grid);
        int result = utility(grid);
        result = Math.min( result , utility(rotateGrid) );
        return result;
    }
}