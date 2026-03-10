class Solution {

    private int n;

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {

        this.n = n;

        int l = edges.length;

        int[][] edgesModified = new int[l][4];

        for( int i = 0; i < l ; i++ ){
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            int idx = i;
            edgesModified[i] = new int[]{u,v,wt,idx};
        }


        Arrays.sort( edgesModified , (a,b) ->{
            return a[2] - b[2];
        });

        int MST_WEIGHT = kruskal( edgesModified,-1,-1 );
        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();

        for( int i = 0; i < l; i++ ){
            int idx = edgesModified[i][3];
            if( kruskal(edgesModified,i,-1) > MST_WEIGHT ){
                critical.add(idx);
            }
            else if( kruskal(edgesModified,-1,i) == MST_WEIGHT ){
                pseudoCritical.add(idx);
            }
        }
        
        List<List<Integer>> res = new ArrayList<>();
        res.add( critical );
        res.add( pseudoCritical );
        return res;

    }

    private int kruskal( int[][] edges, int remove, int include ){

        DSU dsu = new DSU(n);
        int weight = 0;

        if( include != -1 ){
            int u = edges[include][0];
            int v = edges[include][1];
            int wt = edges[include][2];
            dsu.union( u,v );
            weight += wt;
        }

        int el = edges.length;

        for( int i = 0; i < el; i++ ){
            if( i == remove ) continue;
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            if( dsu.union(u,v) ){
                weight += wt;
            }
        }

        for( int i = 0; i < n; i++ ){
            if( dsu.findParent(i) != dsu.findParent(0) ){
                return Integer.MAX_VALUE;
            }
        }

        return weight;

    }
}

class DSU{

    int[] parent;
    int[] size;

    public DSU( int n ){
        parent = new int[n];
        size = new int[n];

        for( int i = 0; i < n ; i++ ){
            size[i] = 1;
            parent[i] = i;
        }
    }

    public int findParent( int num ){
        if( parent[num] == num ) {
            return num;
        }
        return parent[num] = findParent( parent[num] );
    }

    public boolean union( int a, int b ){
        int p1 = findParent( a );
        int p2 = findParent( b );
        if( p1 == p2 ) return false;

        if( size[p1] < size[p2] ){
            size[p2] += size[p1];
            parent[p1] = p2;
        }
        else{
            size[p1] += size[p2];
            parent[p2] = p1;
        }

        return true;
    }

}