
# Search in a 2D Matrix - II | Binary Search on 2D


## Problem Statement

You will be given a **matrix of integers of size `n x m`**. Your task is to **find the coordinates** (row number and column number) where a given `target` integer is located. If the target is found, you should return its `(row, column)` coordinates; otherwise, return `(-1, -1)` or `false` if the question asks for a boolean value.

**Key Property of the Matrix:**
*   **Every individual row** will be **sorted from left to right**.
*   **Every individual column** will be **sorted from top to bottom**.

**Important Note:** The **entire matrix is NOT sorted** in itself, unlike "Search in a 2D Matrix - I". For example, a `15` might be greater than a `2` directly below it, meaning a full matrix sort doesn't hold, only row-wise and column-wise sorting.

## Solutions and Approaches

We will explore different methods to solve this problem, from brute force to an optimized approach.

### 1. Extreme Brute Force Solution

This is the most straightforward but least efficient approach.

*   **Logic:**
    *   **Traverse through every element** in the entire 2D matrix.
    *   For each element `Matrix[i][j]`, **check if it equals the `target`**.
    *   If `target` is found, **return its row and column numbers**.
    *   If the entire matrix is traversed and the `target` is not found, return `(-1, -1)` (or `false`).
*   **Time Complexity:** **O(N * M)**, where N is the number of rows and M is the number of columns.
*   **Space Complexity:** **O(1)**.

### 2. Better Solution: Binary Search on Each Row

This approach leverages the fact that each individual row is sorted.

*   **Logic:**
    *   **Iterate through each row `i`** from `0` to `n-1`.
    *   Consider each `Matrix[i]` as a **1D array**.
    *   **Perform a binary search** on this individual 1D row array to find the `target`.
    *   If the binary search successfully finds the `target` in a row, it will return its column index. In this case, **return `(i, column_index)`**.
    *   If after checking all rows, the `target` is not found, **return `(-1, -1)`** (or `false`).
*   **Time Complexity:** **O(N * log M)**, because we iterate through N rows, and for each row, we perform a binary search which takes `log M` time.
*   **Space Complexity:** **O(1)**.

### 3. Optimal Solution: Pointer Approach (Elimination Tactic)

This is the most efficient method, utilizing the sorted properties of both rows and columns to eliminate parts of the search space.

*   **Key Insight:** While some starting points (like top-left or bottom-right) are not effective because both adjacent directions increase/decrease, **starting from either the top-right or bottom-left corner allows for effective elimination**. From these corners, one direction (row or column) is increasing while the other is decreasing, enabling a decision to be made.

*   **Recommended Starting Point:** **Top-Right Corner**.
    *   Initialize a pointer at `row = 0` (first row) and `col = M - 1` (last column).

*   **Algorithm Steps:**
    1.  Initialize `row = 0` and `col = M - 1` (assuming `M` is the number of columns).
    2.  Use a `while` loop that continues as long as `row` is within matrix bounds (`row < N`) and `col` is within matrix bounds (`col >= 0`).
    3.  Inside the loop, retrieve the `current_element = Matrix[row][col]`.
    4.  **Compare `current_element` with `target`:**
        *   If `current_element == target`: The target is found. **Return `true`** (or `(row, col)`).
        *   If `current_element < target`: This means the `target` must be a larger value. Since elements to the left in the current row are smaller or equal, and elements in the current column are sorted downwards (increasing), the `target` **cannot be in the current row to the left**. Therefore, we move **down one row**: `row++`.
        *   If `current_element > target`: This means the `target` must be a smaller value. Since elements below in the current column are larger or equal, and elements in the current row are sorted rightwards (increasing), the `target` **cannot be in the current column below**. Therefore, we move **left one column**: `col--`.
    5.  If the loop completes (meaning `row` went out of bounds or `col` went out of bounds) and the `target` was not found, **return `false`** (or `(-1, -1)`).

*   **Elimination Logic:** At each step, this approach effectively **eliminates either an entire row or an entire column** from the search space.

*   **Time Complexity:** **O(N + M)**. In the worst case, the pointer traverses from one corner to the opposite corner, taking at most `N` steps vertically and `M` steps horizontally.
*   **Space Complexity:** **O(1)**.

*   **Connection to Binary Search:** Although not a direct binary search, the "concept of binary search" through **elimination tactics** is fundamentally applied here, as we continuously narrow down the search space.

---

This `README.md` provides a comprehensive overview of the approaches to solve "Search in a 2D Matrix - II", with a focus on the most optimal and efficient method.
```