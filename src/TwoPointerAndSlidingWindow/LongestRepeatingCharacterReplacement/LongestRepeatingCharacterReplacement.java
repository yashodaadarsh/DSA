package TwoPointerAndSlidingWindow.LongestRepeatingCharacterReplacement;

class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int l = 0 , r = 0 , maxLen = 0 , maxFreq = 0;;
        int[] freq = new int[26];
        while( r < n ){
            char ch = s.charAt(r);
            freq[ ch-'A' ]++;

            int len = (r-l+1);
            maxFreq = Math.max( maxFreq , freq[ ch-'A' ] );

            if( ( len - maxFreq ) > k ){
                freq[ s.charAt(l)-'A' ]--;
                l++;
            }
            else
                maxLen = Math.max( maxLen , len );
            r++;
        }
        return maxLen;
    }
}