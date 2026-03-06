class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for( int i = 0; i <= n; i++ ){
            adj.add( new ArrayList<>() );
        }

        for( int[] edge : times ){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add( new int[]{v,w} );
        }

        int[] dist = new int[n+1];
        Arrays.fill( dist,Integer.MAX_VALUE );

        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> {
            return a[1] - b[1];
        });
        pq.offer( new int[]{ k,0 } );
        dist[k] = 0;

        while( !pq.isEmpty() ){
            int[] arr = pq.poll();
            int node = arr[0];
            int nodeWei = arr[1];
            for( int[] neiNode : adj.get(node) ){
                int nextNode = neiNode[0];
                int nextNodeWei = neiNode[1];
                if( nodeWei + nextNodeWei < dist[nextNode] ){
                    dist[nextNode] = nodeWei + nextNodeWei;
                    pq.offer( new int[]{ nextNode,dist[nextNode] } );
                }
            }
        }

        int time = 0;

        for( int i = 1; i <= n; i++ ){
            time = Math.max( dist[i],time );
        }

        return time == Integer.MAX_VALUE ? -1 : time;
    }
}