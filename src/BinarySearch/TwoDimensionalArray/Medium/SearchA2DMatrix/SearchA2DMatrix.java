package BinarySearch.TwoDimensionalArray.Medium.SearchA2DMatrix;

class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0 , high = n*m - 1;
        while( low <= high ){
            int mid = ( high - low )/2 + low;
            int row = mid/n;
            int col = mid%n;
            if( matrix[row][col] == target ) return true;
            else if( matrix[row][col] < target ) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }
}