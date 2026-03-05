class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int zero_first = 0, one_first = 0;
        for( int i = 0 ; i < n ; i++ ){
            if( ( s.charAt(i) - '0' ) != i%2 ){
                zero_first++;
            }
            if( ( s.charAt(i) - '0' ) != ( i+1 )%2 ){
                one_first++;
            }
        }
        return Math.min( zero_first,one_first );
    }
}