// Sorting + Insertion
// class Solution {
//     public int[][] reconstructQueue(int[][] people) {
//         Arrays.sort( people, (a,b) -> {
//             if( a[0] == b[0] ){
//                 return a[1] - b[1];
//             }
//             return b[0] - a[0];
//         });

//         int n = people.length;

//         List<int[]> list = new ArrayList<>();
//         for( int[] p : people ){
//             list.add( p[1],p );
//         }
//         int[][] res = new int[n][2];
//         int i = 0;
//         for( int[] p : list ){
//             res[i++] = p;
//         }
//         return res;
//     }
// }

// Segment tree + query (k+1) th position
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,(a,b)->{
            if( a[0] == b[0] ){
                return b[1]-a[1];
            }
            return a[0] - b[0];
        });

        int n = people.length;
        int[][] res = new int[n][2];
        SegmentTree segmentTree = new SegmentTree(n);
        for( int[] p : people ){
            int pos = segmentTree.query(0,0,n-1,p[1]+1);
            res[pos] = p;
            segmentTree.update(0,0,n-1,pos);
        }
        return res;

    }
}

class SegmentTree{
    private int n; 
    private int[] tree;
    public SegmentTree( int n ){
        this.n = n;
        tree= new int[4*n];
        build(0,0,n-1);
    }

    private void build( int node,int st,int end ){
        if( st == end ){
            tree[node] = 1;
            return;
        }

        int mid = (end-st)/2+st;
        build(2*node+1,st,mid);
        build(2*node+2,mid+1,end);
        tree[node]=tree[2*node+1]+tree[2*node+2];

    }

    public void update(int node,int st,int end,int idx){
        if( st == end ){
            tree[node] = 0;
            return;
        }

        int mid = ( end-st )/2 + st;
        if( idx <= mid ){
            update(2*node+1,st,mid,idx);
        }
        else{
            update(2*node+2,mid+1,end,idx);
        }
        tree[node] = tree[2*node+1]+tree[2*node+2];

    }

    public int query( int node, int st, int end, int k ){
        if( st == end ){
            return st;
        }
        int mid = (end-st)/2 + st;
        if( tree[2*node+1] >= k ){
            return query( 2*node+1,st,mid,k );
        }
        else{
            return query( 2*node+2,mid+1,end,k-tree[2*node+1]);
        }
    }

    
}