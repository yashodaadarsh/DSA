class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n+1];

        for( int i = n-1; i >= 0; i-- ){
            int maxi = Integer.MIN_VALUE;
            int sum = 0;
            for( int p = i; p < Math.min(n,i+k); p++ ){
                maxi = Math.max( maxi,arr[p] );
                int val = ( p-i+1 )*maxi + dp[p+1];
                sum = Math.max( sum,val );
            }

            dp[i] = sum;
        }

        return dp[0];

        // Arrays.fill( dp,-1 );
        // return solve( 0,n,arr,k,dp );
    }
    private int solve( int i, int n, int[] arr, int k, int[] dp ){
        if( i == n ) return 0;

        if( dp[i] != -1 ) return dp[i];

        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        for( int p = i; p < Math.min(n,i+k); p++ ){
            maxi = Math.max( maxi,arr[p] );
            int val = ( p-i+1 )*maxi + solve( p+1,n,arr,k,dp );
            sum = Math.max( sum,val );
        }

        return dp[i] = sum;
    }
}