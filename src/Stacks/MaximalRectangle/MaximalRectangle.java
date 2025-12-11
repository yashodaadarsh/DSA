package Stacks.MaximalRectangle;

import java.util.Stack;

class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] prefixMatrix = buildPrefixMatrix(matrix,r,c);

        for( int i = 0 ; i < r ; i++ ){
            int max = findLargestRectangleArea( prefixMatrix[i] );
            maxArea = Math.max( max , maxArea );
        }
        return maxArea;
    }
    private int[][] buildPrefixMatrix( char[][] matrix , int row, int col ){
        int[][] prefixMatrix = new int[row][col];
        for( int c = 0 ; c < col ; c++ ){
            int sum = 0;
            for( int r = 0 ; r < row ; r++ ){
                if( matrix[r][c] == '1' ){
                    sum++;
                    prefixMatrix[r][c] = sum;
                }
                else{
                    sum = 0;
                }
            }
        }
        return prefixMatrix;
    }
    // private int[] buildHeights( char[][] matrix , int r , int c ){
    //     int[] heights = new int[c];
    //     for( int i = 0 ; i < c ; i++ ){
    //         for( int j = r ; j >= 0 ; j-- ){
    //             if( matrix[j][i] != '1' ) break;
    //             heights[i]++;
    //         }
    //     }
    //     return heights;
    // }
    private int findLargestRectangleArea( int[] heights ){
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;
        for( int i = 0 ; i < n ; i++ ){
            while( !st.isEmpty() && heights[st.peek()] > heights[i] ){
                int height = heights[st.pop()];
                int NSE = i;
                int PSE = (st.isEmpty()) ? -1 : st.peek();
                maxArea = Math.max( maxArea , (NSE - PSE - 1) * height );
            }
            st.push(i);
        }
        while( !st.isEmpty() ){
            int height = heights[st.pop()];
            int NSE = n;
            int PSE = (st.isEmpty()) ? -1 : st.peek();
            maxArea = Math.max( maxArea , (NSE - PSE - 1) * height );
        }
        return maxArea;
    }
}