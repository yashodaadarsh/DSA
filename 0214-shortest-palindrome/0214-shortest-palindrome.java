class Solution {
    public String shortestPalindrome(String s) {
        int n = s.length();
        StringBuilder rev = new StringBuilder(s).reverse();
        System.out.println( rev );
        int[] lps = computeLPS( s + "#" + rev.toString() );

        return rev.substring( 0, n-lps[2*n] ).toString() + s;
    }

    private int[] computeLPS( String s ){
        int m = s.length();
        int i = 1;
        int len = 0;

        int[] lps = new int[m];
        lps[0] = 0;

        while( i < m ){
            if( s.charAt(i) == s.charAt(len) ){
                len++;
                lps[i] = len;
                i++;
            }
            else{
                if( len != 0 ){
                    len = lps[len - 1];
                }
                else{
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}