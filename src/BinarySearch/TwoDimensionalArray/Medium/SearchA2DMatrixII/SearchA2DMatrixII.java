package BinarySearch.TwoDimensionalArray.Medium.SearchA2DMatrixII;

public class SearchA2DMatrixII {

    // 1. Extreme Brute Force: O(N * M)
    public boolean bruteForce(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // 2. Row-wise Binary Search: O(N * log M)
    public boolean rowBinarySearch(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            int low = 0, high = m - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (matrix[i][mid] == target) {
                    return true;
                } else if (matrix[i][mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    // 3. Optimal Pointer Approach (Elimination tactic): O(N + M)
    public boolean optimalSearch(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int row = 0, col = m - 1; // Start from top-right corner
        while (row < n && col >= 0) {
            int val = matrix[row][col];
            if (val == target) {
                return true;
            } else if (val > target) {
                col--; // move left
            } else {
                row++; // move down
            }
        }
        return false;
    }

}
