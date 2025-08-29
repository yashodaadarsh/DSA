package Arrays.LC_3446_SortMatrixByDiagonals;

import java.util.*;

public class Solution {

    // -------- Approach 1: Without Map --------
    public int[][] sortMatrixWithoutMap(int[][] grid) {
        int n = grid.length;

        // Bottom-left diagonals (including main) → descending
        for (int row = 0; row < n; row++) {
            List<Integer> diag = new ArrayList<>();
            int i = row, j = 0;

            while (i < n && j < n) {
                diag.add(grid[i][j]);
                i++; j++;
            }

            // Sort descending
            diag.sort(Collections.reverseOrder());

            i = row; j = 0;
            int idx = 0;
            while (i < n && j < n) {
                grid[i][j] = diag.get(idx++);
                i++; j++;
            }
        }

        // Top-right diagonals → ascending
        for (int col = 1; col < n; col++) {
            List<Integer> diag = new ArrayList<>();
            int i = 0, j = col;

            while (i < n && j < n) {
                diag.add(grid[i][j]);
                i++; j++;
            }

            // Sort ascending
            Collections.sort(diag);

            i = 0; j = col;
            int idx = 0;
            while (i < n && j < n) {
                grid[i][j] = diag.get(idx++);
                i++; j++;
            }
        }

        return grid;
    }

    // -------- Approach 2: With Map (i - j property) --------
    public int[][] sortMatrixWithMap(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> map = new HashMap<>();

        // Step 1: Fill diagonals into map
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(grid[i][j]);
            }
        }

        // Step 2: Sort diagonals
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            List<Integer> diag = entry.getValue();

            if (key >= 0) {
                // bottom-left → descending
                Collections.sort(diag); // ascending
            } else {
                // top-right → ascending
                diag.sort(Collections.reverseOrder()); // descending
            }
        }

        // Step 3: Place back into grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                List<Integer> diag = map.get(key);
                int val = diag.remove(diag.size() - 1);
                grid[i][j] = val;
            }
        }

        return grid;
    }

    // -------- Helper for testing --------
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] grid = {
            {3, 2, 1},
            {6, 5, 4},
            {9, 8, 7}
        };

        System.out.println("Approach 1 (Without Map):");
        print(sol.sortMatrixWithoutMap(copy(grid)));

        System.out.println("Approach 2 (With Map):");
        print(sol.sortMatrixWithMap(copy(grid)));
    }

    private static int[][] copy(int[][] arr) {
        int n = arr.length;
        int[][] newArr = new int[n][n];
        for (int i = 0; i < n; i++) newArr[i] = arr[i].clone();
        return newArr;
    }

    private static void print(int[][] arr) {
        for (int[] row : arr) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
