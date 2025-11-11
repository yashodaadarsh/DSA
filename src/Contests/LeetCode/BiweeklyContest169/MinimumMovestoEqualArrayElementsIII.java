package Contests.LeetCode.BiweeklyContest169;
import java.util.Arrays;

class MinimumMovestoEqualArrayElementsIII {
    public int minMoves(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int res = 0;
        int n = nums.length;
        for( int i = 0 ; i < n ; i++ )
        {
            res += ( max - nums[i]);
        }    
        return res;
    }
}