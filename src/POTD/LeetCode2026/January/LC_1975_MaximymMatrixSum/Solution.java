package POTD.LeetCode2026.January.LC_1975_MaximymMatrixSum;

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int n = matrix.length;
        int negCount = 0;
        int min = Integer.MAX_VALUE;
        for( int i = 0 ; i < n ; i++ ){
            for( int j = 0 ; j < n ; j++ ){
                sum += Math.abs(matrix[i][j]);
                if( matrix[i][j] < 0 ) negCount++;
                min = Math.min( min , Math.abs(matrix[i][j]) );
            }
        }
        return ( (negCount&1) == 1 ) ? (sum-2*min) : sum;
    }
}