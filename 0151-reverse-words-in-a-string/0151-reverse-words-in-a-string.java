class Solution {
    public String reverseWords(String s) {
        ArrayList<String> list = new ArrayList<>();
        int n = s.length();
        int i = 0;
        while( i < n ){
            while( i < n && s.charAt(i) == ' ' ) i++;
            StringBuilder sb = new StringBuilder();
            while( i < n && s.charAt(i) != ' ' ){
                sb.append( s.charAt(i) );
                i++;
            }
            list.add( sb.toString() );
            while( i < n && s.charAt(i) == ' ' ) i++;
            // i++;
        }

        StringBuilder ans = new StringBuilder();
        int size = list.size();
        for( int j = size - 1; j >= 0; j-- ){
            ans.append( list.get(j) );
            if( j != 0 )
                ans.append( " " );
        }
        return ans.toString();
    }
}