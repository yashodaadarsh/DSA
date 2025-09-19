package Stacks.SumOfSubArrayMinimum;

import java.util.Stack;

class SumOfSubArrayMinimums {
    private final int MOD = (int) (1e9+7);
    public int sumSubarrayMins(int[] arr) {
        int[] nextSmallerElement = NextSmallerElement(arr);
        int[] prevSmallerOrEqualElement = PrevSmallerOrEqual(arr);
        int n = arr.length;
        long totalSum = 0;
        for( int i = 0 ; i < n ; i++ ){
            int left = i - prevSmallerOrEqualElement[i];
            int right = nextSmallerElement[i] - i;
            long sum = ( ( 1l*right*left )*arr[i] ) % MOD;
            totalSum = ( totalSum + sum ) % MOD;
        }
        
        return (int)totalSum;
    }
    private int[] NextSmallerElement( int[] arr ){
        Stack<Integer> st = new Stack<>();
        int n = arr.length ;
        int[] nse = new int[n];
        for( int i = n-1 ; i >= 0 ; i-- ){
            while( !st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();
            nse[i] = (st.isEmpty()) ? n : st.peek();
            st.push(i);
        }
        return nse;
    }
    private int[] PrevSmallerOrEqual( int[] arr ){
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int[] psee = new int[n];
        for( int i = 0 ; i < n ; i++ ){
            while( !st.isEmpty() && arr[st.peek()] > arr[i] ) st.pop();
            psee[i] = (st.isEmpty()) ? -1 : st.peek();
            st.push(i);
        }
        return psee;
    }
}