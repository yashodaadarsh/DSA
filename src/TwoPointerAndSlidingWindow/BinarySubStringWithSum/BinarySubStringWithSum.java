package TwoPointerAndSlidingWindow.BinarySubStringWithSum;

public class BinarySubStringWithSum {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return findNoOfSubarrays( nums , goal ) - findNoOfSubarrays( nums , goal-1 );
    }
    private int findNoOfSubarrays( int[] nums , int goal ){
        if( goal < 0 ) return 0;
        int n = nums.length;
        int l = 0 , r = 0 , count = 0 , sum = 0;
        while( r < n ){
            sum += nums[r];
            while( sum > goal ){
                sum -= nums[l];
                l++;
            }
            count += ( r-l+1 );
            r++;
        }
        return count;
    }
}

//solution 2
// with prefixzeros concept
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int l = 0 , r = 0;

        int maxArrays = 0 , count = 0 ;
        int prefixZeros = 0;
        int windowSum = 0;

        while( r < n ){

            windowSum += nums[r];

            while( l < r && ( nums[l] == 0 || windowSum > goal ) ){
                if( nums[l] == 0 ) prefixZeros++;
                else prefixZeros = 0;
                windowSum -= nums[l];
                l++;
            }

            if( windowSum == goal ){
                count += ( 1 + prefixZeros );
                // prefixZeros = 0;
            }
            r++;

        }

        return count;

    }
}