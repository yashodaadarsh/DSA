class Solution {
    private static final int MOD = (int)1e9 + 7;
    public int countPaths(int n, int[][] roads) {

        List<List<long[]>> adj = new ArrayList<>();
        for( int i = 0; i < n; i++ ){
            adj.add( new ArrayList<>() );
        }

        for( int[] edge : roads ){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add( new long[]{v,wt} );
            adj.get(v).add(new long[]{u, wt});
        }

        long[] dist = new long[n];
        long[] ways = new long[n];
        Arrays.fill( dist,Long.MAX_VALUE );

        PriorityQueue<long[]> pq = new PriorityQueue<>( (a,b) -> {
            return Long.compare( a[1],b[1] );
        });
        pq.offer( new long[]{0,0} );
        dist[0] = 0;
        ways[0] = 1;


        while( !pq.isEmpty() ){
            long[] arr = pq.poll();
            int node = (int)arr[0];
            long wt = arr[1];

            for( long[] neigh : adj.get(node) ){
                int next = (int)neigh[0];
                long nextWt = neigh[1];
                if( wt + nextWt < dist[next] ){
                    dist[next] = wt + nextWt;
                    ways[next] = ways[node];
                    pq.offer( new long[]{next,dist[next]} );
                }
                else if( wt + nextWt == dist[next] ){
                    ways[next] =( ways[next] + ways[node] )%MOD;
                }
            }
        }

        System.out.println( Arrays.toString(ways) );

        return (int)ways[n-1];

    }
}