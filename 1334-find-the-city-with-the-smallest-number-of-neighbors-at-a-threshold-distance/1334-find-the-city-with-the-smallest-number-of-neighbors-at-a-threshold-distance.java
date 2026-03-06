class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        int[][] dist = new int[n][n];
        for( int i = 0; i < n; i++ ){
            Arrays.fill( dist[i], 1000000 );
            dist[i][i] = 0;
        }

        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            dist[u][v] = wt;
            dist[v][u] = wt;
        }

        int[] ways = new int[n];

        for( int via = 0; via < n; via++ ){
            for( int i = 0; i < n; i++ ){
                for( int j = 0; j < n; j++ ){
                    if( dist[i][via] + dist[via][j] < dist[i][j] ){
                        dist[i][j] = dist[i][via] + dist[via][j];
                    }
                }
            }
        }

        System.out.println( Arrays.toString(ways) );

        int city = n;
        int minCount = Integer.MAX_VALUE;
        for( int i = n-1; i >= 0; i-- ){
            int count = 0;
            for( int j = 0; j < n; j++ ){
                if( dist[i][j] <= distanceThreshold ){
                    count++;
                }
            }
            if( count < minCount ){
                minCount = count;
                city = i;
            }
        }

        return city;

    }
}