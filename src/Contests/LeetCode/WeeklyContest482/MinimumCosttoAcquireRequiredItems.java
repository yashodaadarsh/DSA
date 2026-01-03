package Contests.LeetCode.WeeklyContest482;

// Link :- https://leetcode.com/problems/minimum-cost-to-acquire-required-items/

class MinimumCosttoAcquireRequiredItems {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        
        long ans = Long.MAX_VALUE;
        long a1 = 1l*cost1*need1+1l*cost2*need2;
        ans = Math.min( a1 , ans );
        
        long a2 = 1l*costBoth*Math.max( need1,need2 );
        ans = Math.min( a2,ans );
        
        if( need1 < need2 ){
            long a3 = 1l*costBoth*need1+1l*cost2*(need2-need1);
            ans = Math.min( a3 , ans );
        }
            
        else{
            long a3 = 1l*costBoth*need2+1l*cost1*(need1-need2);
            ans = Math.min( a3 , ans );
        }
        
        return ans;
    }
}