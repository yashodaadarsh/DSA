package Contests.LeetCode.WeeklyContest467;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class MaximizeSumOfAtMostKDistinctElements {
    public int[] maxKDistinct(int[] nums, int k) {
        Arrays.sort( nums );
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for( int i = n-1 ; i >= 0 ; i-- ){
            if( i == n-1 || ( nums[i+1] != nums[i] ) && list.size() < k ){
                list.add(nums[i]);
            }
        }
        // System.out.println( list );
        int[] ans = new int[list.size()];
        int l = 0;
        for( int num : list ){
            ans[l++] = num;
        }
        return ans;
    }
}