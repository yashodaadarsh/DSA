
# LeetCode 3197: Find the Minimum Area to Cover All Ones II

## Problem Description

You are provided with a **2D binary array `grid`**. The challenge is to identify **three non-overlapping rectangles**, each having a non-zero area and strictly horizontal and vertical sides. These three rectangles must collectively **cover all '1's present in the grid**. The ultimate goal is to determine the **minimum possible sum of the areas of these three rectangles**. It's specified that the rectangles are allowed to touch each other but are **not permitted to overlap**.

## Key Concepts & Insights

*   **Two Split Lines, Three Rectangles**: A crucial insight for solving this problem is that **drawing two splitting lines within the grid is sufficient to divide it into three non-overlapping rectangles**. These lines can be horizontal, vertical, or a combination, creating various configurations of the three rectangles.
*   **Problem Reduction (Leveraging Part I)**: This problem (Part II) builds upon a preceding problem (Part I). The core task of determining the **minimum rectangular area to cover all '1's within a *single* specified sub-grid** is a recurring sub-problem. This `minArea` function, which calculates the smallest bounding box containing '1's, is repeatedly invoked for each of the three sub-rectangles formed by the splits.
*   **Exploring All Configurations**: To guarantee finding the absolute minimum total area, the solution must **systematically explore all possible ways** to position the two split lines. The presenter notes that there are **six unique configurations** of three rectangles that need to be considered.
*   **Grid Rotation for Complete Coverage**: To avoid writing duplicate code for "vertical" split configurations (e.g., two vertical lines, or a vertical-first split), the entire grid can be **rotated 90 degrees (e.g., clockwise)**. By applying the same "horizontal" split logic (originally designed for the non-rotated grid) to the rotated grid, all "vertical" and mixed split scenarios are implicitly covered. This technique significantly simplifies the codebase and ensures that all six unique configurations are checked.

## Approach

The solution involves systematically iterating through all valid positions for the two split lines, calculating the combined area for the resulting three rectangles using a helper function, and continuously updating the minimum sum of areas found.

1.  **`minArea` Helper Function (from LeetCode 3196 - Part I)**:
    *   This function accepts the `grid` and bounding coordinates (`start_row`, `end_row`, `start_col`, `end_col`).
    *   It identifies the smallest rectangular bounding box that encloses all '1's within the given sub-grid defined by these coordinates.
    *   It then returns the area of this minimal bounding box. This function is directly "copied and pasted" from the Part I problem solution.

2.  **`utility` Function (Main Splitting Logic)**:
    A helper function, often named `utility`, encapsulates the logic for iterating through split lines and calculating areas for a given grid (either the original or a rotated version). This function addresses different splitting strategies:

    *   **Strategy A: One Horizontal and One Vertical Split**:
        *   This strategy places one horizontal split line (`row_split`) and one vertical split line (`col_split`).
        *   It leads to two primary configurations within the same set of loops:
            *   **Configuration 1**: A full rectangle at the top, with the remaining bottom part divided into two rectangles (bottom-left, bottom-right).
            *   **Configuration 2**: The top part is split into two rectangles (top-left, top-right), and a full rectangle is at the bottom.
        *   *Implementation*: Nested `for` loops iterate through all possible `row_split` positions (from 1 to `m-1`) and `col_split` positions (from 1 to `n-1`). Inside these loops, `minArea` is called three times for each configuration to calculate areas, and the overall minimum result is updated.

    *   **Strategy B: Two Horizontal Splits**:
        *   This strategy involves two purely horizontal split lines (`split1`, `split2`).
        *   This creates three distinct horizontal rectangles: Top, Middle, and Bottom.
        *   *Implementation*: A separate set of nested `for` loops iterates through all possible `split1` positions (from 1 to `m-1`) and `split2` positions (from `split1 + 1` to `m-1`). `minArea` is used to calculate the areas for the Top, Middle, and Bottom rectangles, updating the minimum result.

3.  **Overall Algorithm Execution**:
    *   Initialize `result` (minimum total area) to a very large value.
    *   **Call `utility(original_grid)`**: Apply the splitting strategies (Strategy A & B) to the original grid. Update `result` with the minimum found. This covers three of the six unique configurations.
    *   **Rotate the Grid**: Create a `rotated_grid` by rotating the `original_grid` 90 degrees clockwise using a `rotateClockwise` helper function.
    *   **Call `utility(rotated_grid)`**: Apply the *exact same splitting strategies* (Strategy A & B) to the `rotated_grid`. Update `result` again. This crucial step implicitly covers the remaining three configurations that involve vertical splits (e.g., two vertical lines, or one vertical and one horizontal with the "full" rectangle on the left/right side).
    *   Finally, **return `result`**.

## Rotation Logic (Clockwise)

To rotate a grid `grid` of dimensions `m x n` 90 degrees clockwise:
*   The new `rotated_grid` will have dimensions `n x m` (the number of rows becomes the number of columns, and vice-versa).
*   An element originally at `grid[i][j]` in the original grid will move to `rotated_grid[j][m - i - 1]` in the rotated grid.



## Time Complexity

The time complexity of this approach is **`O(m^2 * n^2)`**.
*   The `minArea` function, in the worst case, takes `O(m * n)` time to scan a sub-grid.
*   The `utility` function contains multiple sets of nested `for` loops (e.g., `row_split` and `col_split`, or `split1` and `split2`), each of which can run up to `m` or `n` times.
*   Since `minArea` is called three times within these nested loops, the dominant part of the `utility` function's complexity for one grid becomes approximately `O(m * n * m * n)` or `O(m^2 * n^2)`.
*   The grid rotation itself takes `O(m * n)` time.
*   As the `utility` function is invoked twice (once for the original grid and once for the rotated grid), the overall time complexity remains **`O(m^2 * n^2)`**.
*   However, the problem constraints for `m` and `n` are typically small enough (e.g., up to 50) to allow this complexity to pass within standard time limits.

