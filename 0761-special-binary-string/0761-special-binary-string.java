class Solution {

    // This question is type of valid parenthesis
    public String makeLargestSpecial(String s) {
        return solve( 0,s );
    }

    private String solve( int ind , String s ){
        int sum = 0, st = ind;
        int n = s.length();

        List<String> specialBinarySubstringList = new ArrayList<>();

        for( int i = ind ; i < n ; i++ ){
            sum += ( s.charAt(i) == '1' ) ? 1 : -1;
            if( sum == 0 ){
                String specialSubstring = s.substring( st+1, i );
                specialBinarySubstringList.add( "1" + solve(0,specialSubstring) + "0" );
                st = i+1;
            }
        }


        Collections.sort(specialBinarySubstringList, (a, b) -> b.compareTo(a));

        StringBuilder sb = new StringBuilder();
        for( String str : specialBinarySubstringList ){
            sb.append( str );
        }

        return sb.toString();
    }
}