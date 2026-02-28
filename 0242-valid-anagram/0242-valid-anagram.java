class Solution {
    public boolean isAnagram(String s, String t) {
        int[] hash = new int[26];
        int n = s.length();
        int m = t.length();
        if( n != m ) return false;

        for( int i = 0; i < n; i++ ){
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            hash[ch1-'a']++;
            hash[ch2-'a']--;
        }

        for( int i = 0; i < 26; i++ ){
            if( hash[i] != 0 ) return false;
        }

        return true;
    }
}