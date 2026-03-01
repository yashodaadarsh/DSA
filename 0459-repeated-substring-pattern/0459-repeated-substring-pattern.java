class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int[] lps = computeLPS( s );
        int n = s.length();

        // n = k x p :: p = length of repeating block :: k = number of times repeated

        // n - lps[n-1] :: it gives the length of the repeating block :: abc abc abc :: ( 12 - 9 ) = 3 ==> abc tarvatha nundi lps start avuthadhi
        // so p = n - lps[n-1]
        // n / ( n - lps[n-1] ) = k 
        // ==> n % ( n -lps[n-1] ) == 0 


        return (lps[n-1] > 0 && n % (n - lps[n-1]) == 0);
    }

    private int[] computeLPS( String s ){
        int m = s.length();
        int i = 1;
        int[] lps = new int[m];
        int len = 0;
        lps[0] = 0;

        while( i < m ){
            if( s.charAt(i) == s.charAt(len) ){
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