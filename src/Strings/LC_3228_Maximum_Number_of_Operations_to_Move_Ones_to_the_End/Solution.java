package Strings.LC_3228_Maximum_Number_of_Operations_to_Move_Ones_to_the_End;

class Solution {
    public int maxOperations(String s) {
        int n = s.length();
        int ans = 0 , count = 0;
        int last = -1 , prev = -1;

        for( int i = 0 ; i < n ; i++ ){
            char a = s.charAt(i);
            if( a == '1' ){
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
}