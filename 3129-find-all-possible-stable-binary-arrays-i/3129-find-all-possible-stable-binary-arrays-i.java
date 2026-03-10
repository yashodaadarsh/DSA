
//Optimal
class Solution {
    
    private int MOD = (int)1e9 + 7;
    private int[][][] dp;
    public int numberOfStableArrays(int zero, int one, int limit) {

        dp = new int[zero+1][one+1][2];
        for( int i = 0; i <= zero ; i++ ){
            for( int j = 0; j <= one; j++ ){
                Arrays.fill( dp[i][j],-1 );
            }
        }
        
        int startWithZero = solve( zero,one,0,limit );
        int startWithOne = solve( zero,one,1,limit );

        return ( startWithZero + startWithOne ) % MOD; 
    }

    private int solve( int zero,int one,int next,int limit ){
        if( zero == 0 && one == 0 ){
            return 1;
        }

        if( dp[zero][one][next] != -1 ) return dp[zero][one][next];
        // if( zero < 0 || one < 0 ) return 0;
        int result = 0;
        if( next == 0 ){
            for( int zeroLt = 1; zeroLt <= Math.min( limit,zero ); zeroLt++ ){
                result = ( result + solve( zero-zeroLt,one,1,limit ) ) % MOD;
            }
        }
        else{
            for( int oneLt = 1; oneLt <= Math.min( limit,one ); oneLt++ ){
                result = ( result + solve( zero,one-oneLt,0,limit ) ) % MOD;
            }
        }

        return dp[zero][one][next] = result;
    }
}

class Approach1 {
    
    private int MOD = (int)1e9 + 7;
    private int[][][] dp;
    public int numberOfStableArrays(int zero, int one, int limit) {

        dp = new int[zero+1][one+1][2];
        for( int i = 0; i <= zero ; i++ ){
            for( int j = 0; j <= one; j++ ){
                Arrays.fill( dp[i][j],-1 );
            }
        }
        
        int startWithZero = solve( zero,one,0,limit );
        int startWithOne = solve( zero,one,1,limit );

        return ( startWithZero + startWithOne ) % MOD; 
    }

    private int solve( int zero,int one,int next,int limit ){
        if( zero == 0 && one == 0 ){
            return 1;
        }

        if( dp[zero][one][next] != -1 ) return dp[zero][one][next];
        // if( zero < 0 || one < 0 ) return 0;
        int result = 0;
        if( next == 0 ){
            for( int zeroLt = 1; zeroLt <= Math.min( limit,zero ); zeroLt++ ){
                result = ( result + solve( zero-zeroLt,one,1,limit ) ) % MOD;
            }
        }
        else{
            for( int oneLt = 1; oneLt <= Math.min( limit,one ); oneLt++ ){
                result = ( result + solve( zero,one-oneLt,0,limit ) ) % MOD;
            }
        }

        return dp[zero][one][next] = result;
    }
}