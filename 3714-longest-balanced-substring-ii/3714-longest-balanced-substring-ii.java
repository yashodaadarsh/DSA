class Solution {
    public int longestBalanced(String s) {

        int allEqual = findAllEqualLargest( s );
        int equalAB = findTwoEqualLargest( s, 'a', 'b' );
        int equalBC = findTwoEqualLargest( s, 'b', 'c' );
        int equalAC = findTwoEqualLargest( s, 'a', 'c' );
        int equalABC = findThreeEqualLargest( s );

        return Math.max( allEqual, Math.max( equalAB, Math.max( equalBC, Math.max( equalAC, equalABC ) ) ) );
    }

    private int findThreeEqualLargest( String s ){

        int n = s.length();
        int cnt1 = 0, cnt2 =0, cnt3 = 0;
        int maxL = 0;

        HashMap<String,Integer> map = new HashMap<>();
        map.put( "0#0",-1 );
        
        for( int i = 0 ; i < n ; i++ ){

            char ch = s.charAt(i);
            if( ch == 'a' ) cnt1++;
            else if( ch == 'b' ) cnt2++;
            else{
                cnt3++;
            }

            String key = (cnt1 - cnt2) + "#"+(cnt2-cnt3);
            if( map.containsKey(key) ){
                maxL = Math.max( maxL, i-map.get(key) );
            }
            else{
                map.put(key,i);
            }

        }

        return maxL;

    }

    private int findTwoEqualLargest( String s, char ch1, char ch2 ){

        int n = s.length();
        int cnt1 = 0, cnt2 =0;
        int maxL = 0;

        HashMap<Integer,Integer> map = new HashMap<>();
        map.put( 0,-1 );
        
        for( int i = 0 ; i < n ; i++ ){

            char ch = s.charAt(i);
            if( ch == ch1 ) cnt1++;
            else if( ch == ch2 ) cnt2++;
            else{
                cnt1 = 0;
                cnt2 = 0;
                map = new HashMap<>();
            }

            int key = cnt1 - cnt2;
            if( map.containsKey(key) ){
                maxL = Math.max( maxL, i-map.get(key) );
            }
            else{
                map.put(key,i);
            }

        }

        return maxL;

    }

    private int findAllEqualLargest( String s ){
        int maxL = 1;
        int count = 1;
        for( int i = 1 ; i < s.length() ; i++ ){
            if( s.charAt(i) == s.charAt(i-1) ){
                count++;
            }
            else{
                maxL = Math.max( maxL , count );
                count = 1;
            }
        }
        maxL = Math.max(maxL, count);
        return maxL;
    }
}