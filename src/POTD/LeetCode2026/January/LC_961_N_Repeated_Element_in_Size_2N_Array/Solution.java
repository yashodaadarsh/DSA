package POTD.LeetCode2026.January.LC_961_N_Repeated_Element_in_Size_2N_Array;

class Solution {
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;
        for( int i = 0 ; i < n-2 ; i++ ){
            if( nums[i] == nums[i+1] ){
                return nums[i];
            }
            else if( nums[i] == nums[i+2] ){
                return nums[i];
            }
        }
        return nums[n-1];
    }
}