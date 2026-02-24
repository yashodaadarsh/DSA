class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort( nums );
        int n = nums.length;

        int[] dp = new int[n+1];
        int[] parent = new int[n];
        for( int i = 0 ; i < n ; i++ ){
            parent[i] = i;
            dp[i] = 1;
        }

        int max = 1;
        int ind = 0;

        for( int i = 0; i < n; i++ ){
            for( int j = 0; j < i; j++ ){
                if( nums[i] % nums[j] == 0 ){
                    if( dp[i] < 1 + dp[j] ){
                        dp[i] = 1 + dp[j];
                        parent[i] = j;
                        if( max < dp[i] ){
                            max = dp[i];
                            ind = i;
                        }
                    }
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        while( ind != parent[ind] ){
            res.add( nums[ind] );
            ind = parent[ind];
        }
        res.add( nums[ind] );

        System.out.println( max );

        return res;
    }
}