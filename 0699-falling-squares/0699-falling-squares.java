// Brute Force - Solution
// class Solution {
//     public List<Integer> fallingSquares(int[][] positions) {

//         int n = positions.length;
//         Set<Integer> cords = new TreeSet<>();
//         Map<Integer,Integer> map = new HashMap<>();
//         int cnt = 0;
//         for( int[] p : positions ){
//             cords.add( p[0] );
//             cords.add( p[0]+p[1]-1 );
//         }
//         for( int x : cords ){
//             map.put(x,cnt++);
//         }

//         List<Integer> ans = new ArrayList<>();
//         int[] dat = new int[cords.size()];
//         int i = 0;
//         for( int[] p : positions ){
//             int x = map.get( p[0] );
//             int y = map.get( p[0] + p[1] - 1);
//             int h = findHeight( positions,dat,x,y,p[1] );
//             ans.add(h);
//         }
        
//         return ans;

//     }

//     private int findHeight( int[][] positions,int[] dat, int x, int y, int h ){

//         int max = 0, base = 0;
//         for( int i = x; i <= y; i++ ){
//             base = Math.max( base,dat[i] );
//         }
//         int newH = base + h;
//         for( int i = x; i <= y; i++ ){
//             dat[i] = newH;
//         }

//         for( int i = 0; i < dat.length; i++ ){
//             max = Math.max( max,dat[i] );
//         }

//         return max;

//     }
// }


// SegmentTree + lazy Propagation
class Solution {
    public List<Integer> fallingSquares(int[][] positions) {

        int n = positions.length;
        Set<Integer> cords = new TreeSet<>();
        Map<Integer,Integer> map = new HashMap<>();
        int cnt = 0;
        for( int[] p : positions ){
            cords.add( p[0] );
            cords.add( p[0]+p[1]-1 );
        }
        for( int x : cords ){
            map.put(x,cnt++);
        }

        SegmentTree segmentTree = new SegmentTree( cnt );
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        for( int[] p : positions ){
            int x = map.get( p[0] );
            int y = map.get( p[0] + p[1] - 1);
            int base = segmentTree.query(0,0,cnt-1,x,y);
            int newH = base + p[1];
            segmentTree.update(0,0,cnt-1,x,y,newH);
            int h = segmentTree.query(0,0,cnt-1,0,cnt-1);
            ans.add(h);
        }
        
        return ans;

    }

}

class SegmentTree{
    private int n;
    private int[] tree;
    private int[] lazy;
    public SegmentTree( int n ){
        this.n = n;
        tree = new int[4*n];
        lazy = new int[4*n];
    }

    private void push( int node, int st, int end ){
        if( lazy[node] == 0 ) return;
        tree[node] = lazy[node];
        if( st != end ){
            lazy[2*node+1] = lazy[node];
            lazy[2*node+2] = lazy[node];
        }
        lazy[node] = 0;
    }

    public void update( int node, int st, int end, int ql, int qr, int val ){

        push( node,st,end );

        if( end < ql || qr < st ){
            return;
        }

        if( ql <= st && end <= qr ){
            lazy[node] = val;
            push(node,st,end);
            return;
        }

        int mid = ( end-st )/2 + st;
        update( 2*node+1,st,mid,ql,qr,val );
        update( 2*node+2,mid+1,end,ql,qr,val );
        tree[node] = Math.max( tree[2*node+1], tree[2*node+2] );

    }

    public int query( int node, int st, int end, int ql, int qr ){

        push( node,st,end );
        if( end < ql || qr < st ){
            return 0;
        }
        if( ql <= st && end <= qr ){
            return tree[node];
        }

        int mid = ( end-st )/2 + st;

        return Math.max(
            query( 2*node+1,st,mid,ql,qr ),
            query( 2*node+2,mid+1,end,ql,qr )
        );

    }
}