class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // We can use the simple BFS. If we check stop by stop we can update the next stop distance later with the more stops and less distance.

        List<List<int[]>> adj = new ArrayList<>();
        for( int i = 0; i < n; i++ ){
            adj.add( new ArrayList<>() );
        }

        for( int[] edge : flights ){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get( u ).add( new int[]{ v,wt } );
        }

        int[] dist = new int[n];
        Arrays.fill( dist,Integer.MAX_VALUE );

        Queue<int[]> q = new LinkedList<>();
        // { src,current_cost,stops }
        q.offer( new int[]{ src,0,0 } );

        while( !q.isEmpty() ){
            int[] arr = q.poll();
            int node = arr[0];
            int cost = arr[1];
            int stops = arr[2];

            if( stops > k ) continue;

            for( int[] neigh : adj.get(node) ){
                int next = neigh[0];
                int price = neigh[1];

                if( cost + price < dist[next] ){
                    dist[next] = cost + price;
                    q.offer( new int[]{ next,dist[next],stops+1 } );
                }

            }

        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];

        
    }
    
}

// Dikstra'a + DP
class Approach1{
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        return dikstraAlgorithm( n,flights,src,dst,k+1 );
    }
    private int dikstraAlgorithm( int n, int[][] edges, int src, int dst, int k ){
        List<List<int[]>> adj = new ArrayList<>();
        for( int i = 0; i < n; i++ ){
            adj.add( new ArrayList<>() );
        }

        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get( u ).add( new int[]{ v,wt } );
        }

        int[][] dist = new int[n][k+1];
        for( int i = 0; i < n; i++ )
            Arrays.fill( dist[i],Integer.MAX_VALUE );

        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> {
            return a[1] - b[1];
        } );

        // { src,wt,k }
        pq.offer( new int[]{ src,0,0 } );
        dist[src][0] = 0;

        while( !pq.isEmpty() ){

            int[] arr = pq.poll();
            int node = arr[0];
            int wt = arr[1];
            int kUsed = arr[2];

            for( int[] nextNodes : adj.get(node) ){
                int next = nextNodes[0];
                int nextWt = nextNodes[1];
                if( (kUsed + 1) <= k && wt + nextWt < dist[next][kUsed+1] ){
                    dist[next][kUsed+1] = wt + nextWt;
                    pq.offer( new int[]{ next,dist[next][kUsed+1],kUsed+1 } );
                }
            }
        }

        int minDist = Integer.MAX_VALUE;
        for( int i = 0; i <= k; i++ ){
            minDist = Math.min( minDist, dist[dst][i] );
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;

    }
}