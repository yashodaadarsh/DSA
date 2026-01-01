package TwoPointerAndSlidingWindow.LongestSubStringWithOutRepeatingCharacters;
import java.util.Arrays;

class LongestSubString {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int l = 0 , r = 0;
        int[] hash = new int[256];
        Arrays.fill( hash , -1 );
        int maxLen = 0;
        while( r < n ){
            char ch = s.charAt(r);
            int idx = (int)ch;
            if( hash[idx] >= l ){
                l = hash[idx] + 1;
            }
            maxLen = Math.max( maxLen , (r-l+1) );
            hash[idx] = r;
            r++;
        }
        return maxLen;
    }
}