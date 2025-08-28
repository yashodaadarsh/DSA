
# Search in a 2D Matrix - I | Binary Search of 2D

This repository/document explains how to efficiently search for a target value in a given 2D matrix using various approaches, focusing on an **optimized binary search method**.

## Problem Statement

You will be given a **sorted 2D Matrix**. The problem specifies that if you observe the elements, they are in a sorted fashion, and the **entire Matrix is sorted in itself**. For example, `3, 4, 7, 9, 12, 13, 16, 18, 20, 21, 23, 29` are in a sorted fashion.

Your task is to find out if a given `target` integer, which will also be provided, is present in this matrix or not.
*   If the target is found, you must **return `true`**.
*   If the target is not found, you must **return `false`**.

**Example Matrix Structure and Indexing:**
The matrix size is `n x m`. For instance, `n` could be 3 (rows) and `m` could be 4 (columns).
*   Row indexing will be 0, 1, 2 (for n=3).
*   Column indexing will be 0, 1, 2, 3 (for m=4), following 0-based indexing.

## Solutions and Approaches

We will explore three main approaches, ranging from the most naive to the most optimal.

### 1. Extreme Naive Solution

This approach involves **traversing every element in the entire 2D matrix**.

*   **Logic:**
    *   **Iterate through each row `i`** from `0` to `n-1`.
    *   **Iterate through each column `j`** from `0` to `m-1`.
    *   **Check if `Matrix[i][j]` is equivalent to the `target`**.
    *   If found, **return `true`**.
    *   If the loops complete without finding the target, **return `false`**.
*   **Time Complexity:** **O(N * M)**, where N is the number of rows and M is the number of columns.
*   **Space Complexity:** **O(1)**.

### 2. Better Solution: Binary Search on Each Row

This approach leverages the fact that **each individual row in the matrix is sorted**.

*   **Logic:**
    *   **Iterate through each row `i`** from `0` to `n-1`.
    *   For each row, first **check if the `target` could possibly exist within that row**. This involves checking if the first element of the row (`Matrix[i]`) is smaller than or equal to the `target`, AND if the `target` is smaller than or equal to the last element of the row (`Matrix[i][M-1]`).
    *   If the `target` falls within this range, **perform a binary search on that specific 1D row array** (`Matrix[i]`). You know how to write a binary search function for a 1D array.
    *   If the binary search finds the `target`, **return `true`**; otherwise, it will return `false`.
*   **Time Complexity:** **O(N * log M)**. This is because we iterate through N rows, and for at most one relevant row, we perform a binary search which takes `log M` time.
*   **Space Complexity:** **O(1)**.

### 3. Optimal Solution: Conceptual Flattening with Binary Search

This is the most efficient approach, achieving a better complexity than O(N). The core idea is to **treat the 2D matrix as a single, hypothetically flattened 1D sorted array**.

*   **Key Idea:**
    *   Imagine the entire 2D matrix is **flattened into a 1D array** in your mind, not in actual memory. This conceptual 1D array would also be sorted, e.g., `3, 4, 7, 9, 12, 13, 16, 18, 20, 21, 23, 29`.
    *   The size of this hypothetical 1D array would be `n x m` (N * M).
    *   You can then apply a standard **binary search on this conceptual 1D array** to find the target.
    *   **Crucial Point:** You **do not actually flatten the matrix in real world memory**. Flattening would take O(N * M) time and space, negating the optimization benefit. Instead, you dynamically **calculate the 2D coordinates (row, column) from a 1D index** during the binary search.

*   **Steps for Binary Search on the Hypothetical 1D Array:**
    1.  Initialize `low = 0` (the 1D index of the first element) and `high = (N * M) - 1` (the 1D index of the last element, since there are `N * M` elements).
    2.  While `low <= high`:
        *   Calculate the **`mid` index**: `mid = low + (high - low) / 2`.
        *   **Convert the 1D `mid` index to its corresponding 2D coordinates (row, column):**
            *   **`row = mid / M`** (integer division, where `M` is the number of columns).
            *   **`col = mid % M`** (modulo operator, where `M` is the number of columns).
        *   Access the element at these 2D coordinates: `current_element = Matrix[row][col]`.
        *   **Compare `current_element` with `target`**:
            *   If `current_element == target`, you've found it, so **return `true`**.
            *   If `current_element < target`, the target must be in the "right half" of the conceptual 1D array, so update **`low = mid + 1`**.
            *   If `current_element > target`, the target must be in the "left half" of the conceptual 1D array, so update **`high = mid - 1`**.
    3.  If the loop completes without finding the target, **return `false`**.

*   **Intuition for 1D to 2D Coordinate Conversion (`index` to `row`, `col`):**
    *   **For `row = index / M`**: Each row has `M` elements. Dividing the 1D `index` by `M` tells you how many full rows have been "passed," which determines the current row number. For example, for an `index` of 5 in a matrix with `M=4` columns, `5 / 4 = 1`, placing it in row 1 (0-indexed).
    *   **For `col = index % M`**: The modulo `M` operation gives the remainder after accounting for full rows, directly translating to the column index within the current row (0-indexed). For example, for an `index` of 5 in a matrix with `M=4` columns, `5 % 4 = 1`, placing it in column 1 (0-indexed) of that row. This works because the first element of each row always has a 1D index that is a multiple of `M` (e.g., 0, 4, 8).

*   **Time Complexity:** **O(log (N * M))** because a binary search is performed over `N * M` elements.
*   **Space Complexity:** **O(1)**.

---

This method provides the most efficient way to search in a sorted 2D matrix by treating it as a single sorted 1D space without explicit memory allocation for flattening.
```