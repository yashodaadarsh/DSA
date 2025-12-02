package SegmentTree.RangeSum;
import java.util.ArrayList;

class SegmentTree{

    private ArrayList<Integer> segmentTree;
    private int n;
    public SegmentTree( int[] arr ){
        this.n = arr.length;
        segmentTree = new ArrayList<>(4 * n);
        for (int i = 0; i < 4 * n; i++) 
            segmentTree.add(0);
        buildTree(0,0,n-1,arr);
    }

    void buildTree( int i , int l , int r , int[] arr ){
        if( l == r ){
            segmentTree.set( i , arr[l] );
            return ;
        }
        int mid = (r-l)/2 + l;
        buildTree(2*i+1,l,mid,arr);
        buildTree(2*i+2,mid+1,r,arr);
        segmentTree.set( i ,  segmentTree.get(2*i+1) + segmentTree.get(2*i+2) );
    }

    public int querySum( int st , int end ){
        return rangeSum( 0 , 0 , n - 1 , st , end );
    }

    private int rangeSum( int i , int l , int r , int st , int end ){

        if( r < st || l > end ) return 0;
        if( l >= st && r <= end ){
            return segmentTree.get(i);
        }
        int mid = ( r-l )/2 + l;
        return rangeSum( 2*i+1 , l , mid , st , end ) + rangeSum( 2*i+2 , mid+1 , r , st , end );
        
    }
}