class Solution {
        int[][] fact = {
            {},
            {0,0,0},
            {1,0,0},
            {0,1,0},
            {2,0,0},
            {0,0,1},
            {1,1,0}
        };
    Map<String,Integer> memo = new HashMap<>();
    public int countSequences(int[] nums, long k) {
        int[] tar = factorize(k);
        if( tar == null ) return 0;
        int n = nums.length;
        return solve(0,0,0,0,nums,tar,n );
    }
    int solve( int ind, int c2, int c3, int c5, int[] nums, int[] tar, int n ){

        
        if( ind == n ){
            if( c2 == tar[0] && c3 == tar[1] && c5 == tar[2] ) return 1;
            return 0;
        }

        String key = ind + "," + c2 + "," + c3 + "," + c5;
        if( memo.containsKey(key) ) return  memo.get(key);

        int ways = 0, num = nums[ind];

        ways += solve( ind+1, c2+fact[num][0], c3+fact[num][1], c5+fact[num][2], nums, tar, n);
        ways += solve( ind+1, c2-fact[num][0], c3-fact[num][1], c5-fact[num][2], nums, tar, n);
        ways += solve(ind+1,c2,c3,c5,nums,tar,n);

        memo.put( key, ways );
        return ways;
    }
    int[] factorize(long k){
        int c2 = 0,c3 = 0,c5 = 0;
        while( k%2 == 0 ){
            c2++;
            k /= 2;
        }
        while( k%3 == 0 ){
            c3++;
            k /= 3;
        }
        while( k%5 == 0 ){
            c5++;
            k /= 5;
        }

        if( k != 1 ) return null;

        return new int[]{c2,c3,c5};
    }
}