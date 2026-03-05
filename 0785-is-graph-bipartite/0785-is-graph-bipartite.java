class Solution {
    // Odd length cycle cannot be biparted
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];

        boolean[] vis = new boolean[n];
        for( int node = 0; node < n; node++ ){
            if( vis[node] ) continue;
            if( !bipartite( node,graph,vis,color ) ) return false;
        }
        return true;

    }

    private boolean bipartite( int node, int[][] adj, boolean[] vis, int[] color ){
        vis[node] = true;
        for( int neigh : adj[node] ){
            if( !vis[neigh] ){
                color[neigh] = 1^color[node];
                if( !bipartite( neigh,adj,vis,color ) ) return false;
            }
            else if( color[node] == color[neigh] ) return false;
        }

        return true;
    }
}