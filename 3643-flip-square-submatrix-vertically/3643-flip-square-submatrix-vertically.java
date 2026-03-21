class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int r1 = x, r2 = x+k-1;
        int c1 = y, c2 = y+k;
        while( c1 < c2 ){
            flip(grid,r1,r2,c1);
            c1++;
        }
        return grid;
    }
    private void flip( int[][] grid,int r1,int r2,int c ){
        while( r1 < r2 ){
            int temp = grid[r1][c];
            grid[r1][c] = grid[r2][c];
            grid[r2][c] = temp;
            r1++;
            r2--;
        }
    }
}