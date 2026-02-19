class Solution {
    public int countBinarySubstrings(String s) {
        int n = s.length();
        int prev = 0, cur = 1, ans = 0;
        for( int i = 1 ; i < n ; i++ ){
            char ch = s.charAt(i);
            if( s.charAt(i-1) == ch ) cur++;
            else{
                ans += Math.min( prev,cur );
                prev = cur;
                cur = 1;
            }
        }
        ans += Math.min( prev,cur );
        return ans;
    }
}