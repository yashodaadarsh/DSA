class TreeAncestor {

    private int[] parent;
    private int n;
    private int[][] up;

    public TreeAncestor(int n, int[] parent) {
        this.parent = parent;
        this.n = n;
        this.up = new int[n][20];
        this.binaryLift();
    }
    
    public int getKthAncestor(int node, int k) {
        if( node == -1 || k == 0 ){
            return node;
        }
        for( int i = 19 ; i >= 0 ; i-- ){
            if( k >= (1<<i) ){
                // Going to 2^i'th parent and finding the 2^k-i'th node
                return getKthAncestor( up[node][i], k - (1<<i) );
            }
        }
        // Don't hit. Just for closing function
        return -1;
    }

    private void binaryLift(){

        for( int node = 0 ; node < n ; node++ ){
            up[node][0] = parent[node];
        }

        // 2^i the ancestor
        for( int i = 1 ; i < 20 ; i++ ){
            for( int node = 0 ; node < n ; node++ ){
                // Node's 2^i-i th parent
                int parent = up[node][i-1];
                // Node's 2^i th parent = ( Node's i-1 th parent -> parent's 2^i-1 th node )
                up[node][i] = ( parent == -1 ) ? -1 : up[parent][i-1];
            }
        }

    }

}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */