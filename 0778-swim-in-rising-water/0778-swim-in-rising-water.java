class Solution {
    private int[][] dirs = { { 0,1 } , { 1,0 } , { 0,-1 } , { -1,0 } };
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] vis = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) ->{
            return a[2] - b[2];
        } );
        pq.offer( new int[]{0,0,grid[0][0]} );
        while( !pq.isEmpty() ){
            int[] arr = pq.poll();
            int r = arr[0];
            int c = arr[1];
            int time = arr[2];
            vis[r][c] = true;
            if( r == n-1 && c == n-1 ) return time;
            for( int d = 0; d < 4 ; d++ ){
                int nr = r + dirs[d][0];
                int nc = c + dirs[d][1];
                if( nr >= 0 && nr < n && nc >= 0 && nc < n && !vis[nr][nc] ){
                    if( nr == n-1 && nc == n-1 ) return Math.max( time,grid[nr][nc] );
                    pq.offer( new int[]{nr,nc,Math.max( time,grid[nr][nc] ) } );
                }
            }
        }

        return -1;

    }
}