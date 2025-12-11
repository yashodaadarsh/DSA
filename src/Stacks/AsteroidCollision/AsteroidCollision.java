package Stacks.AsteroidCollision;

import java.util.ArrayList;
import java.util.List;

class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> st = new ArrayList<>();
        int n = asteroids.length;
        for( int i = 0 ; i < n ; i++ ){
            int el = asteroids[i];
            if( el > 0 ) st.add(el);
            else{
                while( !st.isEmpty() && st.getLast() > 0 && st.getLast() < Math.abs(el) ) st.removeLast();
                if( !st.isEmpty() && st.getLast() == Math.abs(el) ) st.removeLast();
                else if ( st.isEmpty() || st.getLast() < 0 ) st.add(el);
            }
        }
        return st.stream().mapToInt(i->i).toArray();

    }
}