class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int target = (1<<n) - 1;
        boolean[][] vis = new boolean[n][target+1];

        Queue<int[]> q = new LinkedList<>();

        for( int i = 0; i < n; i++ ){
            int mask = (1<<i);
            q.offer( new int[]{i,mask} );
            vis[i][mask] = true;
        }

        int path = 0;
        while( !q.isEmpty() ){
            int size = q.size();
            path++;
            for( int i = 0; i < size; i++ ){
                int[] arr = q.poll();
                int node = arr[0];
                int nodeMask = arr[1];
                for( int next : graph[node] ){
                    int nextMask = (1<<next);
                    int newMask = nodeMask | nextMask;

                    if( newMask == target ) return path;

                    if( !vis[next][newMask] ){
                        vis[next][newMask] = true;
                        q.offer( new int[]{next,newMask} );
                    }
                }
            }

        }

        return 0;
    }
}