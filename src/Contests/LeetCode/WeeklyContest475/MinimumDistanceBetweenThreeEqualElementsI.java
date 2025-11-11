package Contests.LeetCode.WeeklyContest475;

class MinimumDistanceBetweenThreeEqualElementsI {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int dist = 0;
        int min = Integer.MAX_VALUE;
        for( int i = 0 ; i < n ; i++ ){
            for( int j= i + 1; j < n  ;j++){
                for( int k = j+1 ; k < n ; k++ ){
                    if( nums[i] == nums[j] && nums[j] == nums[k] ){
                        dist = Math.abs(j-i)+Math.abs(k-j)+Math.abs(k-i);
                        min = Math.min(dist , min);
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}