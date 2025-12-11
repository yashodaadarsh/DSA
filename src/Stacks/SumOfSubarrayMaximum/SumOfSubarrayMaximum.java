package Stacks.SumOfSubarrayMaximum;

import java.util.Stack;

public class SumOfSubarrayMaximum {
    private long findSumOfSubarrayLargest( int[] nums ){
        int n = nums.length;
        int[] NGE = findNextGreater(nums);
        int[] PGEE = findPrevGreaterOrEqual(nums);
        long total = 0;
        for( int i = 0 ; i < n ; i++ ){
            int left = i - PGEE[i];
            int right = NGE[i] - i;
            total += (1l*left*right*nums[i]);
        }
        return total;
    }
    private int[] findNextGreater( int[] nums ){
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];
        for( int i = n-1 ; i >= 0 ; i-- ){
            while( !st.isEmpty() && nums[st.peek()] <= nums[i] ) st.pop();
            res[i] = (st.isEmpty()) ? n : st.peek();
            st.push(i);
        }
        return res;
    }
    private int[] findPrevGreaterOrEqual( int[] nums ){
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];
        for( int i = 0 ; i < n ; i++ ){
            while( !st.isEmpty() && nums[st.peek()] < nums[i] ) st.pop();
            res[i] = (st.isEmpty()) ? -1 : st.peek();
            st.push(i);
        }
        return res;
    }
}
