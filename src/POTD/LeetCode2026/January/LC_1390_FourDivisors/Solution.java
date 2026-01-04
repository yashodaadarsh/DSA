package POTD.LeetCode2026.January.LC_1390_FourDivisors;

class Solution {
    public int sumFourDivisors(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for( int i = 0 ; i < n ; i++ ){
            ans += countFourDivisorSum(nums[i]);
        }
        return ans;
    }
    private int countFourDivisorSum( int num ){
        int sum = 0 , count = 0;
        for( int i = 1 ; i*i <= num ; i++ ){
            if( num%i == 0 ){
                count++; sum += i;
                if( num/i != i ){
                    sum += num/i; count++;
                }
            }
            if( count > 4 ) break;
        }
        return ( count == 4 ) ? sum : 0;
    }
}