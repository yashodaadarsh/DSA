class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int profit = 0;
        int buy = 100000;
        for( int i = 0 ; i < n ; i++ ){
            buy = Math.min( buy,prices[i] );
            int sellProfit = prices[i] - buy;
            profit = Math.max( sellProfit, profit );
        }
        return profit;
    }
}