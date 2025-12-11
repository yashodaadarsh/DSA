package Arrays.LC_3577CounttheNumberofComputerUnlockingPermutations;

class CountNoOfComputerUnlocking {
    private int MOD = (int)1e9+7;
    public int countPermutations(int[] complexity) {

        int n = complexity.length;

        for( int i = 1 ; i < n ; i++ ){
            if( complexity[i] <= complexity[0] ) return 0;
        }

        int ans = factorial(n-1);
        return ans;

    }
    private int factorial( int n ){
        long count = 1;
        for( int i = 2 ; i <= n ; i++ ){
            count = ( count*i )%MOD;
        }
        return (int)count;
    }
}