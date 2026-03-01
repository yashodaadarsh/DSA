class Solution {
    public int repeatedStringMatch(String a, String b) {
        int[] lps = computeLPS( b );
        int n = a.length();
        int m = b.length();
        int count = 0;

        StringBuilder sb = new StringBuilder( );
        while( sb.length() < b.length() ){
            sb.append( a );
            count++;
        }

        if( KMPMatching( sb,b,lps ) ) return count;

        sb.append( a );
        count++;

        if( KMPMatching( sb,b,lps ) ) return count;
        
        return -1;
       
    }

    private boolean KMPMatching( StringBuilder txt, String pat, int[] lps ){
        // System.out.println( "txt :- " + txt + " pat :- " + pat );
        int n = txt.length();
        int m = pat.length();
        int i = 0, j = 0;

        while( i < n ){
            if( txt.charAt(i) == pat.charAt(j) ){
                j++;
                i++;
            }

            if( j == m ){
                return true;
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

        return false;
    }

    private int[] computeLPS( String pat ){
        int m = pat.length();
        int[] lps = new int[m];

        lps[0] = 0;
        int len = 0;
        int i = 1;

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