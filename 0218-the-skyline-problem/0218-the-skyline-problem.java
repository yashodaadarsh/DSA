// // Brute Force
// class Solution {
//     public List<List<Integer>> getSkyline(int[][] buildings) {
//         TreeSet<Integer> set = new TreeSet<>();
//         for (int[] b : buildings) {
//             set.add(b[0]);
//             set.add(b[1]);
//         }

//         List<List<Integer>> list = new ArrayList<>();

//         Iterator<Integer> it = set.iterator();
//         int prev = 0;
//         while (it.hasNext()) {
//             int val = it.next();
//             int h = findHeight(val, buildings);
//             if (prev != h) {
//                 list.add(new ArrayList<>(Arrays.asList(val, h)));
//             }
//             prev = h;
//         }

//         return list;
//     }

//     private int findHeight(int x, int[][] buildings) {
//         int maxH = 0;

//         for (int[] b : buildings) {
//             if (b[0] <= x && x < b[1]) {
//                 maxH = Math.max(maxH, b[2]);
//             }
//         }

//         return maxH;
//     }
// }

// Segment Tree -> Range Update
// class Solution {
//     public List<List<Integer>> getSkyline(int[][] buildings) {
//         TreeSet<Integer> set = new TreeSet<>();
//         for (int[] b : buildings) {
//             set.add(b[0]);
//             set.add(b[1]);
//         }

//         Map<Integer,Integer> cords = new HashMap<>();
//         Iterator<Integer> it = set.iterator();

//         int id = 0;
//         while( it.hasNext() ){
//             int x = it.next();
//             cords.put( x,id++ );
//         }

//         SegmentTree segmentTree = new SegmentTree(id);

//         for( int[] b : buildings ){
//             int l = cords.get(b[0]);
//             int r = cords.get(b[1]) - 1;

//             if (l <= r) {
//                 segmentTree.update(0, 0, id - 1, l, r, b[2]);
//             }
//         }

//         List<List<Integer>> list = new ArrayList<>();

//         int prev = 0;

//         it = set.iterator();
//         while( it.hasNext() ){
//             int x = it.next();
//             int p = cords.get(x);
//             int h = segmentTree.query(0, 0, id - 1, p);

//             if (h != prev) {
//                 list.add(Arrays.asList(x, h));
//             }
//             prev = h;
//         }

//         return list;
//     }
// }

// class SegmentTree{

//     private int n;
//     private int[] tree;

//     public SegmentTree( int n ){
//         this.n = n;
//         tree = new int[4*n];
//     }

//     public void update( int node, int st, int end, int ql, int qr, int val ){
//         if( qr < st || end < ql ){
//             return;
//         }

//         if( ql <= st && end <= qr ){
//             tree[node] = Math.max( tree[node],val );
//             return;
//         }

//         int mid = ( end-st )/2 + st;

//         update( 2*node+1,st,mid,ql,qr,val );
//         update( 2*node+2,mid+1,end,ql,qr,val );

//         // tree[node] = Math.max( tree[node], Math.max(tree[2*node+1],tree[2*node+2]) );
//     }

//     public int query( int node, int st, int end, int idx ){
//         if( st == end ){
//             return tree[node];
//         }

//         int mid = ( end-st )/2 + st;
        
//         if( idx <= mid ){
//             return Math.max( tree[node],query( 2*node+1,st,mid,idx ) );
//         }
//         else{
//             return Math.max( tree[node],query( 2*node+2,mid+1,end,idx ) );
//         }
//     }
// }

// Using the Line Sweep
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<int[]> cord = new ArrayList<>();
        for( int[] b : buildings ){
            cord.add( new int[]{b[0],b[2]} );
            cord.add( new int[]{b[1],-b[2]} );
        }

        Collections.sort( cord,(a,b) -> {
            if( a[0] == b[0] ){
                return b[1]-a[1];
            }
            return a[0]-b[0];
        });

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);

        int prev = 0;
        List<List<Integer>> list = new ArrayList<>();
        for( int[] arr: cord ){

            int h = arr[1];
            if( h > 0 ){
                map.put( h,map.getOrDefault(h,0)+1 );
            }
            else{
                map.put( -h,map.get(-h)-1 );
                if( map.get(-h) == 0 ) map.remove(-h);
            }

            int curr = map.lastKey();
            if( prev != curr ){
                list.add( Arrays.asList(arr[0],curr) );
            }

            prev = curr;

        }

        return list;

    }
}