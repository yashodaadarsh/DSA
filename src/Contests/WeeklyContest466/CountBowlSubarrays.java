package Contests.WeeklyContest466;

import java.util.Stack;

class CountBowlSubarrays {
    public long bowlSubarrays(int[] nums) {
        int n = nums.length;
        int[] prevGreater = findPrevGreater( nums , n );
        int[] nextGreater = findNextGreater( nums , n );
        // System.out.println( Arrays.toString(prevGreater));
        // System.out.println( Arrays.toString(nextGreater));
        long ans = 0;
        for( int i = 1 ; i < n-1 ; i++ ){
            int lh = prevGreater[i];
            int rh = nextGreater[i];
            if( lh == -1 || rh == -1 ) continue;
            if( (rh-lh+1) >= 3 ) ans++;
        }
        return ans;
    }
    int[] findPrevGreater( int[] nums , int n ){
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        for( int i = 0 ; i < n ; i++ ){
            int num = nums[i];
            while( !st.isEmpty() && nums[st.peek()] <= num ){
                st.pop();
            }
            res[i] = ( st.isEmpty() ) ? -1 : st.peek();
            st.push(i);
        }
        return res;
    }
    int[] findNextGreater( int[] nums , int n ){
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        for( int i = n - 1 ; i >= 0 ; i-- ){
            int num = nums[i];
            while( !st.isEmpty() && nums[st.peek()] <= num ){
                st.pop();
            }
            res[i] = ( st.isEmpty() ) ? -1 : st.peek();
            st.push(i);
        }
        return res;
    }
}