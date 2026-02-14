class BookMyShow {

    private SegmentTree segmentTree ;
    private int n , m;

    private Set<Integer> nonZeroRows;
    private int[] freeSeatIdxOfRow;

    public BookMyShow(int n, int m) {
        this.segmentTree = new SegmentTree(n,m);
        this.n = n;
        this.m = m;

        this.nonZeroRows = new TreeSet<>();
        this.freeSeatIdxOfRow = new int[n];

        for( int i = 0 ; i < n ; i++ ){
            nonZeroRows.add(i);
        }
    }
    
    public int[] gather(int k, int maxRow) {
        int row = segmentTree.queryMax( 0, n-1, 0, k , 0, maxRow );
        if( row > maxRow || row == -1 ) return new int[]{};
        // System.out.println( "GAteher row :- " + "{ " + k + " " + maxRow + " }" + row );


        int col = freeSeatIdxOfRow[row];

        freeSeatIdxOfRow[row] += k;

        segmentTree.update( row, m - freeSeatIdxOfRow[row] );

        if( freeSeatIdxOfRow[row] == m ) nonZeroRows.remove( row );

        return new int[]{row,col};

    }
    
    public boolean scatter(int k, int maxRow) {

        long total = segmentTree.queryTotal( 0, maxRow, 0, n-1, 0 );
        if( total < k ) return false;
        // System.out.println( total );

        Set<Integer> rowsToRemove = new HashSet<>();

        for( int row : nonZeroRows ){
            int seats = m - freeSeatIdxOfRow[row];
            if( seats >= k ){
                segmentTree.update( row, seats - k );
                freeSeatIdxOfRow[row] += k;
                if( seats == k ) rowsToRemove.add( row );
                break;
            }
            segmentTree.update( row , 0 );
            freeSeatIdxOfRow[row] += seats;
            rowsToRemove.add( row );
            k -= seats;
        }

        for( int row : rowsToRemove ){
            nonZeroRows.remove( row );
        }

        return true;

    }
}


class SegmentTree{

    private int[] max;
    public long[] total;

    private int n;
    private int m;

    public SegmentTree( int n, int m ){
        this.max = new int[4*n];
        this.total = new long[4*n];

        this.n = n;
        this.m = m;

        this.buildSegmentTree();
    }

    public void buildSegmentTree( ){
        createTree( 0 , n-1, 0 );
    }

    private void createTree( int l , int r , int i ){

        if( r < l ) return;

        if( l == r ){
            total[i] = m;
            max[i] = m;
            return ;
        }

        int mid = (r-l)/2 + l;
        createTree( l , mid , 2*i+1 );
        createTree( mid+1 , r , 2*i+2 );

        max[i] = Math.max( max[2*i+1] , max[2*i+2] );
        total[i] = total[2*i+1] + total[2*i+2];

    }

    public void update( int idx , int val ){
        updateTree( 0, n-1, 0, idx, val );
    }

    private void updateTree( int l , int r , int i, int idx , int val ){

        if( l == r ){
            total[i] = val;
            max[i] = val;
            return;
        }

        int mid = ( r-l )/2 + l;
        if( idx <= mid ){
            updateTree( l , mid , 2*i+1, idx , val );
        }
        else{
            updateTree( mid+1, r, 2*i+2, idx , val );
        }

        max[i] = Math.max( max[2*i+1] , max[2*i+2] );
        total[i] = total[2*i+1] + total[2*i+2];

    }

    public int queryMax( int l , int r, int i, int k, int st, int end ){

        if( k > max[i] ){
            return -1;
        }

        if( l == r ){
            return l;
        }

        int mid = (r-l)/2 + l;
        int left = queryMax( l, mid, 2*i+1, k, st, end );
        if( left != -1 ) return left;
        return queryMax( mid+1, r, 2*i+2, k, st, end );
    }

    public long queryTotal( int st, int end, int l, int r, int i ){

        if( r < st || end < l ){
            return 0l;
        }

        if( st <= l && r <= end ){
            return total[i];
        }

        int mid = ( r-l )/2 + l;

        long left = queryTotal( st, end, l, mid, 2*i+1 );
        long right = queryTotal( st,end,mid+1,r,2*i+2 );
        return left+right;

    }

}

/**
 * Your BookMyShow object will be instantiated and called as such:
 * BookMyShow obj = new BookMyShow(n, m);
 * int[] param_1 = obj.gather(k,maxRow);
 * boolean param_2 = obj.scatter(k,maxRow);
 */