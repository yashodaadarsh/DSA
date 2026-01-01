package POTD.LeetCode2025.LC_3195_Find_the_Minimum_Area_to_Cover_All_Ones_I;

class Solution {
    public int minimumArea(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Step 1: Initialize boundary variables
        int minRow = m, maxRow = -1;
        int minCol = n, maxCol = -1;

        // Step 2: Traverse the grid to find extreme '1' positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }

        // Step 3: If no '1' is found, return 0
        if (maxRow == -1) {
            return 0;
        }

        // Step 4: Calculate dimensions of rectangle
        int length = maxCol - minCol + 1;  // horizontal span
        int width = maxRow - minRow + 1;   // vertical span

        // Step 5: Return minimum area
        return length * width;
    }
}
