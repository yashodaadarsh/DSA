
# 3195. Find the Minimum Area to Cover All Ones I (LeetCode 3195)

## Problem Description

You are given a **2D binary array `grid`**. The task is to find a **rectangle with horizontal and vertical sides** that has the **smallest possible area**. This rectangle must be large enough to **cover all the '1's** present in the input `grid`. You need to return the minimum possible area of such a rectangle.

The problem is currently marked as **Medium** on LeetCode, but the video suggests it could potentially be reclassified as **Easy** due to its straightforward observation.

## Example

Consider an example where the '1's in the grid require a rectangle with a **length of 3** and a **width of 2**. The minimum area required to cover all '1's in this specific case would be **3 * 2 = 6**.

Another example demonstrates how the rectangle expands:
If '1's are present from column 1 to column 4 (inclusive) and row 2 to row 3 (inclusive), the initial length would be (4 - 1 + 1) = 4, and the width would be (3 - 2 + 1) = 2, resulting in an area of 4 * 2 = 8. If a '1' appears in column 5, the rectangle expands to cover it, making the new length (5 - 1 + 1) = 5, and the area 5 * 2 = 10. Similarly, '1's in different rows or columns would expand the vertical or horizontal boundaries respectively.

## Solution Approach

The core idea to find the minimum area rectangle covering all '1's is to identify the **extreme boundaries** (topmost, bottommost, leftmost, and rightmost) where any '1' is located.

1.  **Initialize Boundary Variables**:
    To track the extreme positions of '1's, four variables are initialized:
    *   `min_row`: Set to a very large value (e.g., `m` or `Integer.MAX_VALUE`) to ensure any encountered row index will be smaller.
    *   `max_row`: Set to a very small value (e.g., `-1` or `Integer.MIN_VALUE`) to ensure any encountered row index will be larger.
    *   `min_col`: Set to a very large value (e.g., `n` or `Integer.MAX_VALUE`).
    *   `max_col`: Set to a very small value (e.g., `-1` or `Integer.MIN_VALUE`).

2.  **Traverse the Grid**:
    *   Iterate through each cell `(i, j)` of the 2D `grid` using nested loops.
    *   **If `grid[i][j]` is equal to '1'**:
        *   Update `min_row`: `min_row = min(min_row, i)`. This finds the **topmost row** containing a '1'.
        *   Update `max_row`: `max_row = max(max_row, i)`. This finds the **bottommost row** containing a '1'.
        *   Update `min_col`: `min_col = min(min_col, j)`. This finds the **leftmost column** containing a '1'.
        *   Update `max_col`: `max_col = max(max_col, j)`. This finds the **rightmost column** containing a '1'.

3.  **Calculate Length and Width**:
    After iterating through the entire grid, `min_row`, `max_row`, `min_col`, and `max_col` will hold the extreme indices of '1's.
    *   The **length** of the rectangle is calculated as the horizontal span:
        *   **`length = max_col - min_col + 1`**. The `+1` is crucial because of zero-based indexing to correctly include both the start and end columns.
    *   The **width** of the rectangle is calculated as the vertical span:
        *   **`width = max_row - min_row + 1`**. Similarly, `+1` accounts for zero-based indexing.

4.  **Calculate Minimum Area**:
    *   The final **minimum area** is the product of the calculated length and width:
        *   **`area = length * width`**.

## Time and Space Complexity

*   **Time Complexity**: **O(M * N)**, where M is the number of rows and N is the number of columns in the grid. This is because the algorithm involves a single traversal of the entire 2D grid to find the extreme '1's.
*   **Space Complexity**: **O(1)**, as only a constant number of extra variables are used to store the boundary indices (`min_row`, `max_row`, `min_col`, `max_col`).
