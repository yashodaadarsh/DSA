package Strings.LC_1513_Number_of_Substrings_With_Only_1s;

class Solution {
    public int numSub(String s) {
        int count = 0 , res = 0;
        int MOD = (int)(1e9+7);
        for( char ch : s.toCharArray() ){
            if( ch == '1' ){
                count++;
                res = ( res + count ) % MOD;
            }else{
                count = 0;
            }
        }
        return res;
    }
}