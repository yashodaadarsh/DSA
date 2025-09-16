package Contests.LeetCode.BiweeklyContest165;
import java.util.*;

class MinimumDiscardsToBalanceInventory {
    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int n = arrivals.length;
        Map<Integer,Integer> map = new HashMap<>();
        int l = 0 , discard = 0;
        for( int r = 0 ; r < n ; r++ ){
            if( r - l + 1 > w ){
                if( arrivals[l] != -1 )
                    map.put( arrivals[l] , map.getOrDefault(arrivals[l],0) - 1 );
                l++;
            }
            map.put( arrivals[r] , map.getOrDefault(arrivals[r] , 0 ) + 1 );
            if( map.get(arrivals[r]) > m ){
                map.put( arrivals[r] , map.get(arrivals[r]) - 1 );
                discard++;
                arrivals[r] = -1;
            }
        }
        return discard;
    }
}