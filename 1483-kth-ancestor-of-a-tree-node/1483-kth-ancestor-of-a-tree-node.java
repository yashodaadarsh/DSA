class TreeAncestor {
    int[] parent;
    int[][] up;
    int n;

    public TreeAncestor(int n, int[] parent) {
        this.n = n;
        this.parent = parent;
        this.up = new int[n][21];
        binaryLifting();
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
    
    public int getKthAncestor(int node, int k) {
        if( node == -1 || k == 0  ) return node;
        for( int i = 20; i >= 0; i-- ){
            int i_th_parent = (1<<i);
            if( i_th_parent <= k ){
                // i => i_th_parent
                return getKthAncestor( up[node][i] , k - i_th_parent );
            }
        }

        return node;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */