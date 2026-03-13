class Solution {
    private int dirs[][] = { {1,0},{-1,0},{0,1},{0,-1} };
    public int shortestPath(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;
        boolean[][][] vis = new boolean[m][n][k+1];
        Queue<int[]> q = new LinkedList<>();
        q.offer( new int[]{0,0,0} );
        vis[0][0][0] = true;

        int steps = 0;
        while( !q.isEmpty() ){

            int size = q.size();
            for( int i = 0; i < size; i++ ){
                int[] arr = q.poll();
                int r = arr[0];
                int c = arr[1];
                int kUsed = arr[2];

                if( r == m-1 && c == n-1 ){
                    return steps;
                }
                
                for( int d = 0; d < 4; d++ ){
                    int nr = r + dirs[d][0];
                    int nc = c + dirs[d][1];
                    if( nr >= 0 && nr < m && nc >= 0 && nc < n ){
                        int newK = kUsed + grid[nr][nc];
                        if( newK <= k && !vis[nr][nc][newK] ){
                            vis[nr][nc][newK] = true;
                            q.offer( new int[]{nr,nc,newK} );
                        }
                    }
                }
            }

            steps++;

        }

        return -1;
    }
}