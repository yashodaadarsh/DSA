class Solution {
    public int maxProfit(int k, int[] prices) {

        int n = prices.length;
        int[][] prev = new int[2][k+1]; 

        for( int i = n-1; i >= 0; i-- ){
            int[][] cur = new int[2][k+1];
            for( int lt = 1; lt <= k; lt++ ){
                cur[1][lt] = Math.max( -prices[i] + prev[0][lt], prev[1][lt] );
                cur[0][lt] = Math.max( prices[i] + prev[1][lt-1], prev[0][lt] );
            }
            prev = cur;
        }

        return prev[1][k];
    }
}