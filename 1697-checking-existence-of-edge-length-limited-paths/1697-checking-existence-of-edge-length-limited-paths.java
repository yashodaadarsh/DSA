class Solution {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort( edgeList,(a,b) ->{
            return a[2] - b[2];
        });
        
        int el = edgeList.length;
        int ql = queries.length;
        int[][] queriesModified = new int[ql][4];

        for( int i = 0; i < ql; i++ ){
            int u = queries[i][0];
            int v = queries[i][1];
            int limit = queries[i][2];

            queriesModified[i] = new int[]{u,v,limit,i};
        }

        Arrays.sort( queriesModified, (a,b) -> {
            return a[2] - b[2];
        } );

        boolean[] ans = new boolean[ql];
        DSU dsu = new DSU(n);

        int j = 0;
        for( int i = 0; i < ql; i++ ){

            int u = queriesModified[i][0];
            int v = queriesModified[i][1];
            int limit = queriesModified[i][2];
            int idx = queriesModified[i][3];

            while( j < el && edgeList[j][2] < limit ){
                int a = edgeList[j][0];
                int b = edgeList[j][1];
                dsu.union(a,b);
                j++;
            }

            ans[idx] = dsu.isConnected(u,v);

        }

        return ans;
        
    }
}

class DSU{
    int[] parent;
    int[] size;
    int n; 
    public DSU( int n ){
        parent = new int[n];
        size = new int[n];
        this.n = n;
        for( int i = 0; i < n; i++ ){
            size[i] = 1;
            parent[i] = i;
        }
    }

    private int findParent( int num ){
        return ( parent[num] == num ) ? num : (parent[num] = findParent( parent[num] ));
    }

    public void union( int a, int b ){

        int p1 = findParent( a );
        int p2 = findParent( b );

        if( p1 == p2 ) return ;

        if( size[p1] < size[p2] ){
            size[p2] += size[p1];
            parent[p1] = p2;
        }
        else{
            size[p1] += size[p2];
            parent[p2] = p1;
        }

    }

    public boolean isConnected( int a , int b ){
        return findParent(a) == findParent(b);
    }
}