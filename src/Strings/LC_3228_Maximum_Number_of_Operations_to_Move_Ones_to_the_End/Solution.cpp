class Solution {
public:
    int maxOperations(string s) {
        int last = -1 , prev = -1;
        int ans = 0 , count = 0;
        int n = s.size();
        for( int i = 0 ; i < n ; i++ ){
            if( s[i] == '1' ){
                count++;
                last = i;
            }
            else if( last != prev ){
                ans += count;
                prev = last;
            }
        }
        return ans;
    }
};