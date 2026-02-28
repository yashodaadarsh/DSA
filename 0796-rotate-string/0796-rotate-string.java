class Solution {
    public boolean rotateString(String s, String goal) {

        // // Brute Force 
        // int n = s.length();
        // // Already Equal
        // if( s.equals( goal ) ) return true;

        // // Rotating one by one
        // for( int i = 0; i < n-1; i++ ){
        //     s = s.substring(1,n) + s.substring(0,1);
        //     if( s.equals(goal) ) return true;
        // }
        // return false;

        // optimal
        if( s.length() != goal.length() ) return false;
        StringBuilder sb = new StringBuilder(s);
        sb.append(s);
        // sb = s+s

        return sb.toString().contains(goal);
    }
}