
# Row with Maximum Number of 1s (Binary Search on 2D Arrays)

---

## Problem Statement

You are given an **N x M 2D matrix** with the following characteristics:
*   All elements within the matrix are either **zeros or ones**.
*   **Every row of the matrix is sorted**.

The primary goal is to **find and return the index of the row that contains the maximum number of ones**.

**Important Tie-breaking Rule**: If there are multiple rows that contain the same maximum number of ones, you must **return the row with the smallest index**.

**Example Scenario**:
Consider a matrix with the following counts of ones per row:
*   Row 0: 3 ones
*   Row 1: 0 ones
*   Row 2: 4 ones
*   Row 3: 0 ones
*   Row 4: 4 ones

In this example, both Row 2 and Row 4 have 4 ones, which is the maximum count. According to the tie-breaking rule, the answer to be returned would be **2**, as it is the smallest index among the rows with the maximum count.

---

## Naive Approach (Brute Force)

A straightforward, brute-force solution involves iterating through every element of the matrix to count the ones in each row and then determining the row with the maximum count.

### How it Works:
1.  Initialize `max_count` to -1 (as a count of -1 is never possible).
2.  Initialize `row_index` to -1.
3.  Traverse through each row `i` of the matrix, from `0` to `n-1`.
4.  For each row `i`, iterate through all its columns `j` from `0` to `m-1`.
5.  Sum the elements `array[i][j]` in the current row to calculate `count_rows` (this effectively counts the ones in that row).
6.  After counting ones for a row, **if `count_rows` strictly exceeds `max_count` (`count_rows > max_count`)**, then:
    *   Update `max_count` to `count_rows`.
    *   Update `row_index` to the current row `i`.
    *   The **strict greater-than comparison (`>`) is crucial** for ensuring that if multiple rows have the same maximum count, the one with the smallest index (encountered first) is chosen.
7.  After traversing all rows, return the final `row_index`.

### Complexity:
*   **Time Complexity**: **O(N * M)**, where N is the number of rows and M is the number of columns, as every element of the matrix is visited.
*   **Space Complexity**: O(1), as no additional data structures proportional to the input size are used.

**Why Optimize?**: Given that "every row is sorted," an interviewer would typically expect a more optimized solution than O(N * M).

---

## Optimized Approach (Binary Search)

The key to optimizing this problem lies in leveraging the fact that **every row is sorted**. This property allows us to apply binary search to efficiently determine the number of ones in each row.

### Key Insights:
*   We **cannot optimize the outer loop, which traverses through each row**; this still takes O(N) steps.
*   However, we **can significantly optimize the process of counting ones within each individual row**.
*   Since each row is sorted, it can be treated as an **independent 1D sorted array**.

### How Binary Search Optimizes Counting Ones:
1.  Instead of iterating through `M` elements to count ones in a row (an O(M) operation), **binary search can find the index of the first occurrence of '1'** in that row in **O(log M) time**.
2.  Once the index of the first '1' is found (let's call it `first_one_occurrence_index`), the number of ones in that row can be calculated as **`M - first_one_occurrence_index`**.
    *   For example, if a row has `M = 5` columns and the first '1' is located at index 2, then there are `5 - 2 = 3` ones.
3.  This binary search operation is performed **for every row individually**.
4.  Standard binary search functions like `lower_bound` (to find the first element not less than '1') or `upper_bound` of '0' (to find the first element greater than '0') can be used to locate the first '1'.

### Algorithm:
1.  Initialize `countMax` to 0 and `index` to -1.
    *   It is important to initialize `countMax` to 0 and `index` to -1. If no row contains any ones (e.g., a row of all zeros), `lower_bound` for '1' would return `M` (hypothetical index after the last element), resulting in `M - M = 0` ones. This value of 0 would correctly not update `countMax` (which is already 0), ensuring that `index` remains -1 as required for cases where no row has at least one '1'.
2.  Iterate through each row `i` from `0` to `n-1`.
3.  For the current row (`Matrix[i]`), perform a binary search (e.g., using `lower_bound` for '1') to find the index of its first '1'. Let this be `first_one_occurrence_index`.
4.  Calculate `count_of_ones = M - first_one_occurrence_index`.
5.  **If `count_of_ones` is strictly greater than `countMax` (`count_of_ones > countMax`)**:
    *   Update `countMax = count_of_ones`.
    *   Update `index = i`.
6.  After iterating through all rows, **return the final `index`**.

### Optimized Complexity:
*   **Time Complexity**: **O(N * log M)**. This is derived from iterating through N rows, and performing a logarithmic time (log M) binary search operation on each row. This represents a significant improvement over O(N * M) when M is large.
*   **Space Complexity**: O(1), as no additional data structures proportional to the input size are used.
```