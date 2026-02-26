class Solution {
    public int numSteps(String s) {
        int n = s.length();
        int i = n-1;
        int cnt = 0;
        StringBuilder sb = new StringBuilder(s);
        while( i >= 0 ){
            char ch = sb.charAt(i);
            if( i == 0 && ch == '1') return cnt;
            if( ch == '1' ){
                cnt++;
                while( i >= 0 && sb.charAt(i) == '1' ){ cnt++; i--; }
                if( i > 0 ) sb.setCharAt(i,'1');
            }else{
                cnt++;
                i--;
            }
        }
        return cnt;
    }
}