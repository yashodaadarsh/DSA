package TwoPointerAndSlidingWindow.NumberofSubstringsContainingAllThreeCharacters;
import java.util.Arrays;

class NumberOfSubstringsAllThree {
    private int findMinIdx( int[] freq ){
        int min = Integer.MAX_VALUE;
        for( int i = 0 ; i < 3 ; i++ ){
            min = Math.min( min , freq[i] );
        }
        return min;
    }
    public int numberOfSubstrings(String s) {
        int[] freq = new int[3];
        Arrays.fill( freq , -1 );
        int n = s.length();
        int ans = 0;
        for( int i = 0 ; i < n ; i++ ){
            char ch = s.charAt(i);
            //finds the minunum index where all the three character include in the substring 
            freq[ch-'a'] = i;
            int minIdx = findMinIdx(freq);
            // No need of this check. Just for Clarity
            if( minIdx != -1 ) ans += ( minIdx - 0 + 1 );
        }
        return ans;
    }
}