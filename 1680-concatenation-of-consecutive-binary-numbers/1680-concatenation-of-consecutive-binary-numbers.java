class Solution {
    public int concatenatedBinary(int n) {
        long res = 0;
        int MOD = (int)1e9 + 7;
        int bits = 0;
        for( int i = 1; i <= n; i++ ){
            if( (i&( i-1 )) == 0 ){
                bits++;
            }
            res = (( res << bits ) | i )%MOD;
        }

        return (int)res;
    }
}