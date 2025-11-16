class Solution {
public:
    int numSub(string s) {
        int count = 0 , res = 0;
        int MOD = 1e9+7;
        for( char ch : s ){
            if( ch == '1' ){
                count++;
                res = ( res + count ) % MOD;
            }
            else{
                count = 0;
            }
        }
        return res;
    }
};