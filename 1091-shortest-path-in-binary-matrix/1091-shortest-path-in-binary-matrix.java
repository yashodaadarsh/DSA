class Solution {
    private int n;
    private int[][] dirs = { {0,1},{0,-1},{1,0},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1} };
    public int shortestPathBinaryMatrix(int[][] grid) {
        n = grid.length;
        if( grid[0][0] == 1 ) return -1;
        if( n == 1 ) return 1;

        Queue<int[]> q = new LinkedList<>();
        q.offer( new int[]{ 0,0,1 } );
        int[][] dist = new int[n][n];
        for( int i = 0; i < n; i++ ){
            Arrays.fill( dist[i], Integer.MAX_VALUE );
        }

        while( !q.isEmpty() ){
            int[] arr = q.poll();
            int r = arr[0];
            int c = arr[1];
            int d = arr[2];
            for( int dir = 0; dir < 8; dir++ ){
                int nr = r + dirs[dir][0];
                int nc = c + dirs[dir][1];
                if( nr >= 0 && nr < n && nc >= 0&& nc < n && grid[nr][nc] == 0 && d+1 < dist[nr][nc] ){
                    if( nr == n-1 && nc == n-1 ) return d+1;
                    dist[nr][nc] = d+1;
                    q.offer( new int[]{nr,nc,d+1} );
                }
            }
        }
        return -1;
    }
}