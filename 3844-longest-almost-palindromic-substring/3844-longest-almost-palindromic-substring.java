class Solution {
    public int almostPalindromic(String s) {
        int n = s.length();
        int maxL = 0;
        for( int c = 0 ; c < n ; c++ ){
            maxL = Math.max( maxL, solve(s,c,c,n) );
            if( c+1 < n ){
                maxL = Math.max( maxL, solve(s,c,c+1,n) );
            }
        }
        return maxL;
    }
    private int solve( String s, int l, int r, int n ){
        int len = 0;
        while( l >= 0 && r < n && s.charAt(l) == s.charAt(r) ){
            l--;r++;
        }
        len = (r-1)-(l+1)+1;
        if( l < 0 && r >= n ) return len;
        if( l < 0 || r >= n ){
            if( l >= 0 || r < n ) return len+1;
        }

        //skip one char
        int l1 = countAfterDelete(s,l-1,r,n);
        int l2 = countAfterDelete(s,l,r+1,n);
        return len+1+2*Math.max(l1,l2);
    }
    private int countAfterDelete(String s , int l , int r, int n ){
        int count = 0;
        while( l >= 0 && r < n && s.charAt(l) == s.charAt(r) ){
            count++;
            l--;r++;
        }
        return count;
    }
}