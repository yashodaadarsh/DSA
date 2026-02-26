class Solution {
    private int n;
    public int maxCoins(int[] nums) {
        n = nums.length;
        int[] arr = new int[n+2];
        arr[0] = 1;
        arr[n+1] = 1;
        for( int i = 1; i < n+1; i++ ){
            arr[i] = nums[i-1];
        }

        int[][] dp = new int[n+2][n+2];

        for( int lt = n; lt >= 1; lt-- ){
            for( int rt = 1; rt <= n; rt++ ){
                if( rt < lt ) continue;
                if( lt == rt ){
                    dp[lt][rt] = arr[lt-1]*arr[lt]*arr[lt+1];
                    continue;
                }

                int best = 0;
                for( int i = lt; i <= rt; i++ ){
                    int val = arr[lt-1]*arr[i]*arr[rt+1] + dp[lt][i-1] + dp[i+1][rt];
                    best = Math.max( best,val );
                }
                dp[lt][rt] = best;

            }
        }


        return dp[1][n];

        // for( int i = 0; i < n+2; i++ ){
        //     Arrays.fill( dp[i],-1 );
        // }

        // return solve( 1,n,arr,dp );
    }
    private int solve( int lt, int rt, int[] arr, int[][] dp ){

        if( rt < lt ) return 0;

        if( lt == rt ) return arr[lt-1]*arr[lt]*arr[lt+1];
        if( dp[lt][rt] != -1 ) return dp[lt][rt];

        int best = 0;
        for( int i = lt; i <= rt; i++ ){
            int val = arr[lt-1]*arr[i]*arr[rt+1] + solve(lt,i-1,arr,dp) + solve(i+1,rt,arr,dp);
            best = Math.max( best,val );
        }
        return dp[lt][rt] = best;
    }

}