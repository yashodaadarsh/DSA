package Stacks.NextGreaterElementII;

import java.util.*;

class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        return findCircularGreaterElement(nums);
    }
    private int[] findCircularGreaterElement(int[] nums){
        int n = nums.length;
        int[] res = new int[n];
        int k = n-1;
        Stack<Integer> st = new Stack<>();
        for( int i = 2*n -1 ; i >= 0 ; i-- ){
            while( !st.isEmpty() && st.peek() <= nums[i%n] ){
                st.pop();
            }
            if( i < n ){
                res[k--] = st.isEmpty() ? -1 : st.peek();
            }
            st.push(nums[i%n]);
        }
        return res;
    }
}