class Solution {
    public String maximumXor(String s, String t) {
        int n = s.length();
        int[] hash = new int[2];
        for( char ch : t.toCharArray() ){
            hash[ch-'0']++;
        }

        StringBuilder sb = new StringBuilder();
        for( char ch : s.toCharArray() ){
            int bit = ch-'0';
            if( bit == 0 && hash[1] > 0 ){
                sb.append(1);
                hash[1]--;
            }
            else if( bit == 0 && hash[1] == 0 ){
                sb.append(0);
                hash[0]--;
            }
            else if( bit == 1 && hash[0] > 0 ){
                sb.append(1);
                hash[0]--;
            }
            else{
                sb.append(0);
                hash[1]--;
            }
        }
        return sb.toString();
    }
}