class Solution {
    public int maximalRectangle(char[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        
        int[] height = new int[c];
        int maxArea = 0;
        for( int row = 0; row < r; row++ ){

            for( int col = 0; col < c; col++ ){
                int val = matrix[row][col] - '0';
                if( val == 1 )
                    height[col] += 1;
                else
                    height[col] = 0;
            }

            int area = findArea( height );
            maxArea = Math.max( maxArea, area );

        }

        return maxArea;
    }

    private int findArea( int[] height ){

        int maxArea = 0;
        Stack<Integer> st = new Stack<>();
        int n = height.length;
        for( int i = 0 ; i < n ; i++ ){
            while( !st.isEmpty() && height[ st.peek() ] > height[i] ){
                int ht = height[ st.peek() ];
                int nextInd = i;
                st.pop();
                int prevInd = ( st.isEmpty() ) ? -1 : st.peek();
                int area = ht * ( nextInd - prevInd  - 1);
                maxArea = Math.max( area , maxArea );
            }
            st.push( i );
        }

        while( !st.isEmpty() ){
            int ht = height[ st.peek() ];
            int nextInd = n;
            st.pop();
            int prevInd = ( st.isEmpty() ) ? -1 : st.peek();
            int area = ht * ( nextInd - prevInd - 1 );
            maxArea = Math.max( area , maxArea );
        }

        return maxArea;
    }
}