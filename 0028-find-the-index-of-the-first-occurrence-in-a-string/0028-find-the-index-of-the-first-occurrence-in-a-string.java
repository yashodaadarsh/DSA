class Solution {
    public int strStr(String haystack, String needle) {
        int[] lps = computeLPS( needle );
        return KMPMatch( haystack,needle,lps );
    }

    private int KMPMatch( String txt, String pat , int[] lps ){
        int n = txt.length();
        int m = pat.length();
        
        int i = 0, j = 0;

        while( i < n ){
            if( txt.charAt(i) == pat.charAt(j) ){
                i++; j++;
            }

            if( j == m ){
                return i - m;
            }
            else if( i < n && txt.charAt(i) != pat.charAt(j) ){
                if( j != 0 ){
                    j = lps[j-1];
                }
                else{
                    j = 0;
                    i++;
                }
            }
        }

        return -1;
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