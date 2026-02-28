class Solution {
    public int countSubstrings(String s) {
        // Using the simple palindrome property
        int n = s.length();
        int count = 0 ;
        for( int i = 0 ; i < n ; i++ ){
            count += check( s , i , i , n  ); // odd length
            count += check( s , i , i+1 , n  ); // even length
        }
        return count;
    }
    int check( String s , int i , int j , int n ){
        int count = 0;
        while( i>=0 && j < n ){
            if( s.charAt( i ) == s.charAt(j) ) count++;
            else break;
            i-- ; j++;
        }
        return count;
    }
}