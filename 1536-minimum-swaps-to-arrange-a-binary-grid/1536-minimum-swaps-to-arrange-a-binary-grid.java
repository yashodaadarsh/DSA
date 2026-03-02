class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zeros = new int[n];
        for( int i = 0; i < n; i++ ){
            int zero = 0;
            for( int j = n-1; j >= 0; j-- ){
                if( grid[i][j] == 0 ) zero++;
                else break;
            }
            zeros[i] = zero;
        }
        System.out.println( Arrays.toString(zeros) );

        int ans = 0;
        for( int i = 0; i < n; i++ ){
            int reqZero = n-i-1;
            boolean possible = false;
            for( int j = i; j < n; j++ ){
                if( zeros[j] >= reqZero ){
                    possible = true;
                    ans += (j-i);

                    // Rotation
                    changeIndex(j,i,zeros);
                    break;
                }
            }
            if( !possible ) return -1;
        }
        return ans;

    }

    private void changeIndex( int src,int dest,int[] zeros ){
        int n = zeros.length;
        int temp = zeros[src];

        for( int i = src-1; i >= dest; i-- ){
            if( i+1 < n ) 
                zeros[i+1] = zeros[i];
        }
        zeros[dest] = temp;
    }
}