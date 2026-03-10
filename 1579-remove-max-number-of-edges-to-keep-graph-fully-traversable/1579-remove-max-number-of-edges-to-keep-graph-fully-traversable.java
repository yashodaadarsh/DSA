class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        DSU alice = new DSU(n+1);
        DSU bob = new DSU(n+1);
        Arrays.sort( edges,(a,b) -> {
            return b[0] - a[0];
        });

        int extra = 0;

        for( int i = 0; i < edges.length; i++ ){
            int type = edges[i][0];
            int u = edges[i][1];
            int v = edges[i][2];

            if( type == 3 ){
                boolean aliceEdge = alice.union(u,v);
                boolean bobEdge = bob.union(u,v);
                if( aliceEdge || bobEdge ){
                    extra++;
                }
            }
            else if( type == 1 ){
                if( alice.union(u,v) ){
                    extra++;
                }
            }
            else{
                if( bob.union(u,v) ){
                    extra++;
                }
            }
        }

        return ( alice.max == n && bob.max == n ) ? extra : -1;
    }
}

class DSU{

    int[] parent;
    int[] size;
    int n;
    int max;

    public DSU( int n ){
        parent = new int[n];
        size = new int[n];
        this.n = n;
        max = 0;
        for( int i = 0; i < n; i++ ){
            size[i] = 1;
            parent[i] = i;
        }
    }

    private int findParent( int num ){
        if( parent[num] == num ) return num;
        return parent[num] = findParent( parent[num] );
    }

    public boolean union( int a, int b ){

        int p1 = findParent( a );
        int p2 = findParent( b );

        if( p1 == p2 ) return true;
        
        if( size[p1] < size[p2] ){
            size[p2] += size[p1];
            parent[p1] = p2;
        }
        else{
            size[p1] += size[p2];
            parent[p2] = p1;
        }

        max = Math.max( max,Math.max( size[p1],size[p2] ) ); 

        return false;
    }
}