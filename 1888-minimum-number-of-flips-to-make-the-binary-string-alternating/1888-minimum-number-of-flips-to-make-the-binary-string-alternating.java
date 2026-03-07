class Solution {
    public int minFlips(String s) {

        StringBuilder sb = new StringBuilder(s);
        sb.append(s);

        char[] arr = sb.toString().toCharArray();
        int n = arr.length;

        int[] firstEven = new int[n];
        int[] firstOdd = new int[n];

        for( int i =  0; i < n; i++ ){
            int num = arr[i] - '0';
            if( num != (i%2) ){
                firstEven[i] = 1;
            }
            if( num != (i+1)%2 ){
                firstOdd[i] = 1;
            }
        }

        for( int i = 1; i < n; i++ ){
            firstEven[i] += firstEven[i-1];
            firstOdd[i] += firstOdd[i-1];
        }

        int sl = s.length();

        int min = Integer.MAX_VALUE;
        for( int i = 0; i < sl; i++ ){
            int cnt1 = firstEven[i+sl-1] - ( ( i > 0 ) ? firstEven[i-1] : 0 );
            int cnt2 = firstOdd[i+sl-1] - ( ( i > 0 ) ? firstOdd[i-1] : 0 );
            min = Math.min( min, Math.min( cnt1,cnt2 ) );
        }

        return min;
    }
}