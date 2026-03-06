class Solution {
    public boolean checkOnesSegment(String s) {

        int n = s.length();
        int i = 0;
        while( i < n && s.charAt(i) == '1' ){
            i++;
        }

        for( ; i < n-1; i++ ){
            if( s.charAt(i) != s.charAt(i+1) ){
                return false;
            }
        }
        return true;
    }
}