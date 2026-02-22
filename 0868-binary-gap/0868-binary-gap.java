class Solution {
    public int binaryGap(int n) {
        int prev = -1;
        int max = 0;
        for( int i = 0 ; i <= 31 ; i++ ){
            if( ((n>>i)&1) == 1 ){
                if( prev != -1 ){
                    max = Math.max( max,i-prev );
                }
                prev = i;
            }
        }
        return max;
    }
}