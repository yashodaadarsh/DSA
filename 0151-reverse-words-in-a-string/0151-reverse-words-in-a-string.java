class Solution {
    public String reverseWords(String s) {

        int n = s.length();
        int i = n-1;
        StringBuilder sb = new StringBuilder();

        // read from the end and append to the result
        while( i >= 0 ){
            while( i >= 0 && s.charAt(i) == ' ' ){
                i--;
            }
            if( i < 0 ) break;
            int end = i;

            while( i >= 0 && s.charAt(i) != ' ' ){
                i--;
            }

            if( !sb.isEmpty() ){
                sb.append( " " );
            }

            sb.append( s.substring(i+1,end+1) );
        }

        return sb.toString();
    }
}