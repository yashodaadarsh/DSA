package Contests.LeetCode.BiweeklyContest169;

class CountSubarraysWithMajorityElementI {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;
        for( int i = 0 ; i < n ; i++ ){
            int count = 0;
            for( int j = i ; j < n ; j++ ){
                if( nums[j] == target ) count++;
                int size = j-i+1;
                if( size/2 < count ) ans++;
            }
        }
        return ans;
    }
    boolean check( int i , int j, int[] nums , int target ){
        int size = j - i + 1;
        int count = 0;
        for( int k = i ; k <= j ; k++ ){
            if( nums[k] == target ) count++;
        }
        if( size/2 < count ) return true;
        return false;
    }
}