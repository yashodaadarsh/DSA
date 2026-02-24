class Solution {

    // LIS Varient
    public int longestStrChain(String[] words) {

        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int n = words.length;

        int[] dp = new int[n+1];
        Arrays.fill( dp,1 );

        int max = 1;

        for( int i = 0; i < n; i++ ){
            for( int j = 0; j < i; j++ ){
                if( doesFormChain(words[j],words[i]) ){
                    if( dp[i] < 1 + dp[j] ){
                        dp[i] = 1 + dp[j];
                        max = Math.max( max,dp[i] );
                    }
                }
            }
        }

        return max;
    }

    private boolean doesFormChain( String word1, String word2 ){
        if( word1.length() + 1 != word2.length() ) return false;

        int m = word1.length();
        int n = word2.length();

        boolean skip = true;
        int k = 0, l = 0;

        while( k < m && l < n ){
            char ch1 = word1.charAt(k);
            char ch2 = word2.charAt(l);
            if( ch1 != ch2 ){
                if( skip ){
                    l++;
                    skip = false;
                }
                else{
                    return false;
                }
            }
            else{
                k++;
                l++;
            }
        }

        return true;
    }
}