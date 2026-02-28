class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        int[] lps = computeLPS( s );
        return s.substring( 0,lps[n-1] );
    }

    private int[] computeLPS( String pat ){
        int m = pat.length();
        int[] lps = new int[m];
        lps[0] = 0;

        int i = 1;
        int len = 0;

        while( i < m ){
            if( pat.charAt(i) == pat.charAt(len) ){
                len++;
                lps[i] = len;
                i++;
            }
            else{
                if( len != 0 ){
                    len = lps[len-1];
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