class Solution {

    int[] parent;
    int[][] up;
    int n;
    int[] depth;

    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {

        List<List<int[]>> adj = new ArrayList<>();
        depth = new int[n];
        parent = new int[n];
        this.n = n;
        up = new int[n][21];

        for( int i = 0; i < n; i++ ){
            adj.add( new ArrayList<>() );
        }

        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add( new int[]{v,wt} );
            adj.get(v).add( new int[]{u,wt} );
        }

        boolean[] vis = new boolean[n];
        int[][] freq = new int[n][27];
        parent[0] = -1;
        calculateFreq( 0,adj,freq,vis,depth,0 );
        binaryLifting();

        int q = queries.length;
        int[] answer = new int[q];
        for( int i = 0; i < q; i++ ){
            answer[i] = solveQuery( queries[i], freq );
        }

        return answer;

    }

    private int solveQuery( int[] query, int[][] freq ){

        int lca = findLCA( query,freq ); 
        int a = query[0];
        int b = query[1];

        int[] weightFreq = new int[27];
        int max = 0;
        int total = 0;
        for( int i = 0; i < 27; i++ ){
            weightFreq[i] = freq[a][i] + freq[b][i] - 2*freq[lca][i];
            max = Math.max(max,weightFreq[i]);
            total += weightFreq[i];
        }

        return total - max;

    }

    private int findLCA( int[] query, int[][] freq ){
        int a = query[0];
        int b = query[1];

        int depA = depth[a];
        int depB = depth[b];
        
        if( depA < depB ){
            int temp = depA;
            depA = depB;
            depB = temp;

            int node_temp = a;
            a = b;
            b = node_temp;
        }

        a = lift( a, depA-depB );

        if( a == b ) return a;

        for( int i = 20; i >= 0; i-- ){
            int ap = up[a][i];
            int bp = up[b][i];

            if( ap != bp ){
                a = ap;
                b = bp;
            }
        }

        return up[a][0];

    }

    private void calculateFreq( int node, List<List<int[]>> adj, int[][] freq, boolean[] vis,int[] depth, int d ){


        vis[node] = true;
        depth[node] = d;
        
        for( int[] neigh : adj.get(node) ){
            int neighNode = neigh[0];
            int wt = neigh[1];
            if( !vis[neighNode] ){
                for (int w = 1; w <= 26; w++) {
                    freq[neighNode][w] = freq[node][w];
                }
                freq[ neighNode ][ wt ]++;
                parent[neighNode] = node;
                calculateFreq( neighNode,adj,freq,vis,depth,d+1 );
            }
        }

        vis[node] = false;
    }


    private void binaryLifting(){
        // storing immediate parent, i.e :- 2^0th -> 1st parent
        for( int node = 0; node < n; node++ ){
            up[node][0] = parent[node];
        }

        for( int k = 1; k < 21; k++ ){
            for( int node = 0; node < n; node++ ){
                int parent = up[node][k-1];
                // node ith parent = nodes (i-1) th parent's (i-1)th parent
                up[node][k] = ( parent == -1 ) ? -1 : up[parent][k-1];
            }
        }
    }
    
    public int lift(int node, int k) {
        if( node == -1 || k == 0  ) return node;
        for( int i = 20; i >= 0; i-- ){
            int i_th_parent = (1<<i);
            if( i_th_parent <= k ){
                // i => i_th_parent
                return lift( up[node][i] , k - i_th_parent );
            }
        }

        return node;
    }


}