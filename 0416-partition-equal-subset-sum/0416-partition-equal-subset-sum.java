class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int total = Arrays.stream( nums ).sum();

        if( total%2 != 0 ) return false;

        int target = total/2;

        boolean[] prev = new boolean[target+1];
        prev[0] = true;
        if( nums[0] <= target ) prev[nums[0]] = true;

        for( int i = 1 ; i < n ; i++ ){
            boolean[] current = new boolean[target+1];
            current[0] = true;
            for( int s = 1 ; s <= target ; s++ ){
                boolean skip = prev[s];
                boolean take = false;
                if( nums[i] <= s ){
                    take = prev[s-nums[i]];
                }
                current[s] = take || skip;
            }
            prev = current;
        }

        return prev[target];
    }
}