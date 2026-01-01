package TwoPointerAndSlidingWindow.MaxConsecutiveOnesIII;

class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int l = 0 , r = 0 , zeroCnt = 0 , maxLen = 0;
        while( r < n ){
            if( nums[r] == 0 ) zeroCnt++;
            if( zeroCnt > k ){
                if( nums[l] == 0 ) zeroCnt--;
                l++;
            }
            maxLen = Math.max( maxLen , (r-l+1) );
            r++;
        }
        return maxLen;
    }
}