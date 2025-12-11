package Stacks.LargestRectangleInHistogram;

import java.util.Stack;

class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;
        for( int i = 0 ; i < n ; i++ ){
            while( !st.isEmpty() && heights[st.peek()] > heights[i] ){
                int height = heights[st.pop()];
                int NSE = i;
                int PSE = st.isEmpty() ? -1 : st.peek();
                maxArea = Math.max( maxArea , (NSE - PSE - 1)*height );
            }
            st.push(i);
        }
        while( !st.isEmpty() ){
                int height = heights[st.pop()];
                int NSE = n;
                int PSE = st.isEmpty() ? -1 : st.peek();
                maxArea = Math.max( maxArea , (NSE - PSE - 1)*height );
        }
        return maxArea;
    }
}