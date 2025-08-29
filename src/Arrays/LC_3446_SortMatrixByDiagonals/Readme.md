
# Sort Matrix by Diagonals | 2 Approaches | Without Map | Leetcode 3446


## Problem Description

You are given an `n x n` matrix of integers called `grid`. Your task is to return the modified matrix such that:
*   The **diagonals in the bottom-left triangle** (including the main middle diagonal) are sorted in **non-increasing (descending) order**.
*   The **diagonals in the top-right triangle** are sorted in **non-decreasing (ascending) order**.

**Example:**
For a given `2D grid`, a diagonal like `1, 8, 6` (bottom-left triangle) would be sorted to `8, 6, 1`. A diagonal like `2, 7` (top-right triangle) would be sorted to `2, 7`.

## Motivation

The video emphasizes the importance of focused study. Even two hours of focused, zero-distraction study can have a significantly greater impact than six hours of distracted and unfocused study.

## Approach 1: Straightforward Simulation (Without Map)

This approach directly implements the problem's requirements without using any additional data structures like a map. It involves iterating through the diagonals, extracting their elements, sorting them, and then placing them back into the grid.

### Identifying and Traversing Diagonals

*   **Bottom-Left Triangle Diagonals:** These diagonals start from `(row, 0)` for `row` values from `0` to `n-1`. For instance, the first diagonal starts at `(0, 0)`, the second at `(1, 0)`, and so on. They must be sorted in **non-increasing (descending) order**.
*   **Top-Right Triangle Diagonals:** These diagonals start from `(0, column)` for `column` values from `1` to `n-1`. For instance, the first top-right diagonal starts at `(0, 1)`, the next at `(0, 2)`, etc.. They must be sorted in **non-decreasing (ascending) order**.
*   **Diagonal Traversal:** To move along any diagonal from a starting cell `(i, j)`, you continuously increment both the row and column indices: `(i+1, j+1)`.

### Steps

1.  **Iterate through Bottom-Left Diagonals:**
    *   Loop `row` from `0` to `n-1`. For each `row`, the diagonal starts at `(row, 0)`.
    *   **Extract Elements:** Traverse the diagonal (`i`, `j` starting from `row`, `0` and incrementing `i`, `j`) and push all elements into a temporary `std::vector`.
    *   **Sort:** Sort the temporary vector in **descending order** (non-increasing).
    *   **Place Back:** Traverse the same diagonal path again, taking elements from the sorted vector and placing them back into the `grid`.
2.  **Iterate through Top-Right Diagonals:**
    *   Loop `column` from `1` to `n-1`. For each `column`, the diagonal starts at `(0, column)`.
    *   **Extract Elements:** Similar to step 1, extract elements into a temporary `std::vector`.
    *   **Sort:** Sort the temporary vector in **ascending order** (non-decreasing).
    *   **Place Back:** Place the sorted elements back into the `grid`.

### Time and Space Complexity (Approach 1)

*   **Time Complexity:** O(N² log N)
    *   Iterating through `N` diagonals. For each diagonal, extracting elements takes O(N), sorting takes O(N log N), and placing back takes O(N). The dominant factor is `N * (N log N)` for `N` diagonals, resulting in O(N² log N).
*   **Space Complexity:** O(N)
    *   A temporary `std::vector` is used to store elements of a single diagonal. In the worst case, a diagonal can have `N` elements.

## Approach 2: Using Map with `i - j` Property

This approach utilizes a unique property of diagonals: for any element `(i, j)` in a diagonal, the difference `(i - j)` remains constant for all elements within that same diagonal.

### Key Property and Diagonal Classification

*   **`i - j` is Constant:** Every diagonal has a unique constant value for `(i - j)`.
    *   For the main diagonal (e.g., `(0,0), (1,1), (2,2)`), `i - j` is `0`.
    *   For diagonals in the bottom-left triangle, `i - j` is typically **non-negative** (`>= 0`).
    *   For diagonals in the top-right triangle, `i - j` is typically **negative** (`< 0`).

### Steps

1.  **Populate Map:**
    *   Initialize an `std::map<int, std::vector<int>>` (or `std::unordered_map`) where the key is `(i - j)` and the value is a vector storing all elements of that diagonal.
    *   Iterate through the entire `grid` using nested loops `(i, j)`.
    *   Calculate `diag = i - j`.
    *   Push `grid[i][j]` into `mp[diag]`.
2.  **Sort Diagonal Elements in Map:**
    *   Iterate through each key-value pair (`it`) in the map.
    *   **For Bottom-Left Diagonals (`it.first >= 0`):** These need to be filled in **descending order**. To achieve this efficiently by `pop_back` (constant time removal), the vector `it.second` is sorted in **ascending order**. When elements are `pop_back`ed from this ascending sorted vector, they will be retrieved in descending order.
    *   **For Top-Right Diagonals (`it.first < 0`):** These need to be filled in **ascending order**. To achieve this efficiently by `pop_back`, the vector `it.second` is sorted in **descending order**. When elements are `pop_back`ed from this descending sorted vector, they will be retrieved in ascending order.
3.  **Fill Grid from Map:**
    *   Iterate through the `grid` again using nested loops `(i, j)`.
    *   Calculate `diag = i - j`.
    *   Retrieve the **last element** from `mp[diag]` using `mp[diag].back()`.
    *   Place this element into `grid[i][j]`.
    *   Remove the element from the map's vector using `mp[diag].pop_back()` to prepare for the next element on that diagonal.

### Time and Space Complexity (Approach 2)

*   **Time Complexity:** O(N² log N)
    *   Populating the map takes O(N²). Sorting all vectors in the map (in the worst case, many short diagonals or a few long ones) contributes O(N² log N). Filling the grid again takes O(N²).
*   **Space Complexity:** O(N²)
    *   The `std::map` stores all `N x N` elements of the grid in its vectors in the worst case.

## Important Note for Interviewers

It is crucial to **ask the interviewer if you are allowed to modify the input grid**. If not, a separate 2D grid should be created to store the modified result.

Both approaches are considered simple and effective for solving this problem.
```