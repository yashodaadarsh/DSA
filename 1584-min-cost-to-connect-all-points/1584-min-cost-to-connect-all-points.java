class Solution {
    public int minCostConnectPoints(int[][] points) {
        
        int n = points.length;
        List<int[]> edges = new ArrayList<>();
        
        for( int i = 0; i < n; i++ ){
            for( int j = i+1; j < n; j++ ){
                int dist = Math.abs( points[i][0] - points[j][0] ) + Math.abs( points[i][1] - points[j][1] );
                edges.add( new int[]{i,j,dist} );
            }
        }

        Collections.sort( edges, (a,b) -> {
            return a[2] - b[2];
        } );

        DSU dsu = new DSU(n);

        int cost = 0;
        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if( dsu.union( u,v ) ){
                cost += wt;
            }
        }

        return cost;
    }
}

class DSU{
    int[] parent;
    int[] size;
    DSU( int n ){
        parent = new int[n];
        size = new int[n];
        for( int i = 0; i < n; i++ ){
            parent[i] = i;
            size[i] = 1;
        }
    }

    private int findParent( int num ){
        if( parent[num] == num ) return num;
        return parent[num] = findParent( parent[num] );
    }

    public boolean union( int n1, int n2 ){
        int p1 = findParent(n1);
        int p2 = findParent(n2);
        if( p1 == p2 ) return false;
        if( size[p1] <= size[p2] ){
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