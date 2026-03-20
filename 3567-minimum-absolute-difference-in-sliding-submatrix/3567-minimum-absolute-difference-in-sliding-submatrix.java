class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m-k+1][n-k+1];
        Map<Integer,Integer> map = new HashMap<>();
        int i = 0, j = 0, j_ = 0;
        while( i + k <= m ){
            while( j-j_ < k ){
                for( int r = i; r < i + k; r++ ){
                    map.put( grid[r][j] , map.getOrDefault( grid[r][j], 0 ) + 1 );
                }
                j++;
            }
            ans[i][j_] = findMin(map);

            //removing the j_ column
            for( int r = i; r < i+k; r++ ){
                map.put( grid[r][j_] , map.getOrDefault( grid[r][j_], 0 ) - 1 );
                if( map.get(grid[r][j_]) == 0 ){
                    map.remove( grid[r][j_] );
                }
            }
            if( j == n ){
                map.clear();
                j_ = 0;
                j = 0;
                i++;
            }
            else{
                j_++;
            }
        }
        return ans;
    }
    private int findMin( Map<Integer,Integer> map ){

        List<Integer> list = new ArrayList<>();
        for( int key : map.keySet() ){
            list.add( key );
        }

        if( list.size() == 1 ) return 0;

        Collections.sort( list );
        // System.out.println( list );

        int min = Math.abs( list.get(1) - list.get(0) );
        for( int i = 1 ; i < list.size(); i++ ){
            min = Math.min( min, Math.abs( list.get(i) - list.get(i-1) ) );
            // System.out.println( min );
        }

        return min;

    }
}