package Strings.LC_2211_Count_Collisions_on_a_Road;

class CountCollisions {
    public int countCollisions(String directions) {
        int n = directions.length();
        int i = 0 , j = n-1;
        while( i < n && directions.charAt(i) == 'L' ) i++;
        while( j >= 0 && directions.charAt(j) == 'R') j--;
        int ans = j - i + 1;
        for( int k = 0 ; k <= j ; k++ ){
            if( directions.charAt(k) == 'S' ) ans--;
        }
        return ans;
    }
}