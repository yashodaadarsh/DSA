class Solution {

    private long INF = (long)1e15;
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {

        List<List<int[]>> adj = new ArrayList<>();
        List<List<int[]>> revAdj = new ArrayList<>();
        for( int i = 0; i < n; i++ ){
            adj.add( new ArrayList<>() );
            revAdj.add( new ArrayList<>() );
        }

        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add( new int[]{v,wt} );
            revAdj.get(v).add( new int[]{u,wt} );
        }

        // distance from src1 to LCD( lowest common descendent )
        long[] src1ToLCD = dikstra( src1,adj );
        // distance from src2 to LCD( lowest common descendent )
        long[] src2ToLCD = dikstra( src2,adj );
        // distance from dest to LCD( lowest common descendent )
        long[] destToLCD = dikstra( dest,revAdj );

        long minWt = INF;
        // consider every node as lcd and find the min distance
        // distance( src1,lcd ) + distance( src2,lcd ) + distance( lcd,dest ) = weight

        for( int i = 0; i < n; i++ ){

            if( src1ToLCD[i] == INF || src2ToLCD[i] == INF || destToLCD[i] == INF ){
                continue;
            }
            long distance = src1ToLCD[i] + src2ToLCD[i] + destToLCD[i];
            minWt = Math.min( minWt,distance );

        }

        return minWt == INF ? -1 : minWt;


    }

    private long[] dikstra( int src,List<List<int[]>> adj ){

        int n = adj.size();
        PriorityQueue<long[]> pq = new PriorityQueue<>( (a,b) -> {
            return Long.compare(a[1],b[1]);
        });
        pq.offer( new long[]{src,0} );

        long[] dist = new long[n];
        Arrays.fill( dist,INF );

        dist[src] = 0;

        while( !pq.isEmpty() ){
            long[] arr = pq.poll();
            int node = (int)arr[0];
            long wt = arr[1];

            if (wt > dist[node]) continue;

            for( int[] neigh : adj.get(node) ){
                int next = neigh[0];
                int nextWt = neigh[1];

                if( wt + nextWt < dist[next] ){
                    dist[next] = wt + nextWt;
                    pq.offer( new long[]{next,dist[next]} );
                } 
                
            }
        }

        return dist;
    }
}