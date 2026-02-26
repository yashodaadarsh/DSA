class Solution {
    public int minCost(int n, int[] cuts) {
        Arrays.sort( cuts );
        int m = cuts.length;
        int[] arr = new int[m+2];
        arr[0] = 0;
        arr[m+1] = n;
        for( int i = 0; i < m ; i++ ){
            arr[i+1] = cuts[i];
        }
        System.out.println( Arrays.toString(arr) ); 
        int[][] dp = new int[m+2][m+2];

        for( int lt = m+1; lt >= 0; lt-- ){
            for( int rt = 0; rt < m+2; rt++ ){
                if( rt-lt == 1 ) continue;
                int best = Integer.MAX_VALUE;
                for( int k = lt+1; k < rt; k++ ){
                    int val = arr[rt]-arr[lt] + dp[lt][k] + dp[k][rt];
                    best = Math.min(best,val);
                }
                dp[lt][rt] = best;
            }
        }
        
        return dp[0][m+1];
        // for( int i = 0; i < m+2 ; i++ ){
        //     Arrays.fill( dp[i] , -1 );
        // }
        // return solve(0,m+1,arr,dp);
    }
    private int solve( int lt, int rt, int[] cuts, int[][] dp ){
        
        if( rt-lt == 1 ) return 0;

        if( dp[lt][rt] != -1 ) return dp[lt][rt];

        int best = Integer.MAX_VALUE;
        for( int k = lt+1; k < rt; k++ ){
            int val = cuts[rt]-cuts[lt] + solve(lt,k,cuts,dp) + solve(k,rt,cuts,dp);
            best = Math.min(best,val);
        }

        return dp[lt][rt] = best;
    }
}