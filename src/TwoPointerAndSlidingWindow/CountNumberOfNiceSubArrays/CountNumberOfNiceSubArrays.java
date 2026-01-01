package TwoPointerAndSlidingWindow.CountNumberOfNiceSubArrays;

public class CountNumberOfNiceSubArrays {
    public int numberOfSubarrays(int[] nums, int k) {
        return countNiceSubArray( nums , k ) - countNiceSubArray( nums , k-1 );
    }

    private int countNiceSubArray( int[] nums , int goal ){
        if( goal < 0 ) return 0;
        int n = nums.length;
        int sum = 0 , l = 0 , r = 0 , result = 0;
        while( r < n ){
            sum += nums[r]%2;
            while( sum > goal ){
                sum -= nums[l]%2;
                l++;
            }
            result += ( r-l+1 );
            r++;
        }
        return result;
    }
}

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return countArrays( nums , k );
    }
    private int countArrays( int[] nums , int goal ){
        int n = nums.length;
        int l = 0 , r = 0 , result = 0;
        int sum = 0 , prefixZeros = 0;
        while( r < n ){
            sum += nums[r]%2;
            while( l < r && ( nums[l]%2 == 0 || sum > goal ) ){
                if( nums[l]%2 == 0 ) prefixZeros++;
                else prefixZeros = 0;
                sum -= nums[l]%2;
                l++;
            }
            if( sum == goal ){
                result += ( 1 + prefixZeros );
            }
            r++;
        }
        return result;
    }
}
