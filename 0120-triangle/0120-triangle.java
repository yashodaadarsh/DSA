class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {

        int m = triangle.size();

        List<Integer> bottomRow = triangle.get( m - 1 );
        int n = bottomRow.size();

        int[] bottom = new int[n];
        for( int i = 0 ; i < n ; i++ ){
            bottom[i] = bottomRow.get(i);
        }

        for( int i = m-2 ; i >= 0 ; i-- ){
            
            List<Integer> currentRow = triangle.get(i);
            int size = currentRow.size();
            int[] current = new int[ size ];

            for( int j = 0 ; j < size ; j++ ){
                current[j] = currentRow.get(j) + Math.min( bottom[j], bottom[j+1] );
            }

            bottom = current;

        }

        return bottom[0];


    }
}