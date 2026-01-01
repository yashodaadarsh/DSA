package TwoPointerAndSlidingWindow.LongestSubArrayWithAtmostKDistinctCharacteers;

import java.util.HashMap;
import java.util.Map;

class LongestSubStringWithKUniques {
    public int longestKSubstr(String s, int k) {
        // code here
        int n = s.length();
        Map<Character,Integer> map = new HashMap<>();
        
        int r = 0 , l = 0;
        int result = -1;
        
        while( r < n ){
            
            char ch = s.charAt(r);
            map.put( ch , map.getOrDefault( ch , 0 ) + 1 );
            
            if( map.size() > k ){
                map.put( s.charAt(l) , map.get( s.charAt(l) ) - 1 );
                if( map.get( s.charAt(l) ) == 0 ) map.remove( s.charAt(l) );
                l++;
            }
            if( map.size() == k )
                result = Math.max( result , (r-l+1) );
            r++;
            
        }
        
        return result;
        
    }
}