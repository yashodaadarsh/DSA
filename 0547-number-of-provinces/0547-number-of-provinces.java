class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DSU dsu = new DSU(n);
        for( int i = 0; i < n; i++ ){
            for( int j = 0; j < n; j++ ){
                if( isConnected[i][j] == 1 )
                    dsu.union(i,j);
            }
        }

        return dsu.count;
    }
}

class DSU{

    int[] parent;
    int[] size;
    int count;
    DSU( int n ){
        parent = new int[n];
        size = new int[n];
        count = n;
        for( int i = 0; i < n; i++ ){
            parent[i] = i;
            size[i] = 1;
        }
    }

    private int findParent( int num ){
        if( parent[num] == num ) return num;
        return parent[num] = findParent( parent[num] );
    }

    public void union( int n1, int n2 ){
        int p1 = findParent(n1);
        int p2 = findParent(n2);
        if( p1 == p2 ) return;
        if( size[p1] <= size[p2] ){
            size[p2] += size[p1];
            parent[p1] = p2;
        }
        else{
            size[p1] += size[p2];
            parent[p2] = p1;
        }
        count--;
    }
}