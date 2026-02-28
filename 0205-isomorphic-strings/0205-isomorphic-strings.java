class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> sMap = new HashMap<>();
        Map<Character,Character> tMap = new HashMap<>();

        int n = s.length();
        for( int i = 0; i < n; i++ ){
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if( (sMap.containsKey(ch1) && sMap.get(ch1) != ch2 ) || ( tMap.containsKey(ch2) && tMap.get( ch2 ) != ch1 ) ) return false;

            sMap.put( ch1,ch2 );
            tMap.put( ch2,ch1 );
        }

        return true;
    }
}