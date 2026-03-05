class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> adj = new ArrayList<>();
        for( int i = 0; i <= n; i++ ){
            adj.add( new ArrayList<>() );
        }
        for( int[] edge : dislikes ){
            adj.get( edge[0] ).add( edge[1] );
            adj.get( edge[1] ).add( edge[0] );
        }
        boolean[] vis = new boolean[n+1];
        int[] color = new int[n+1];
        for( int i = 0; i <= n; i++ ){
            if( !bipartite(i,adj,vis,color) ) return false;
        }
        return true;
    }
    private boolean bipartite( int node, List<List<Integer>> adj, boolean[] vis, int[] color ){
        vis[node] = true;
        for( int neigh : adj.get(node) ){
            if( !vis[neigh] ){
                color[neigh] = 1^color[node];
                if( !bipartite( neigh,adj,vis,color ) ) return false;
            }
            else if( color[node] == color[neigh] ) return false;
        }

        return true;
    }
}