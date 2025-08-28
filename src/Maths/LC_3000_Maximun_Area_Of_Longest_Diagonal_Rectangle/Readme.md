
# LeetCode 3000: Maximum Area of Longest Diagonal Rectangle (Easy)

## Problem Description

You are given a **2D zero-indexed integer array `dimensions`**.
*   For each index `i`, `dimensions[i]` represents the **length** of rectangle `i`.
*   For each index `i`, `dimensions[i]` represents the **width** of rectangle `i`.

The task is to **return the area of the rectangle having the longest diagonal**.

**Tie-breaking condition:** If there are multiple rectangles with the same longest diagonal, you must **return the area of the rectangle having the maximum area** among them.

## Key Concepts

### Diagonal Calculation
*   The diagonal of a rectangle can be calculated using the Pythagorean theorem: `sqrt(length^2 + width^2)`.
*   **Important Optimization**: When comparing diagonals, it is not necessary to calculate the square root. You can simply compare `length^2 + width^2` because `sqrt(A) > sqrt(B)` if and only if `A > B` (for non-negative A, B). This simplifies the calculation and avoids floating-point issues.

### Area Calculation
*   The area of a rectangle is calculated as `length * width`.



## Solution Approach (Algorithm)

The solution involves iterating through the given `dimensions` array and keeping track of the maximum diagonal encountered so far and the corresponding maximum area.

1.  **Initialization:**
    *   Initialize `maxDiagonal` to 0 (or the diagonal squared of the first rectangle).
    *   Initialize `maxArea` to 0 (or the area of the first rectangle).

2.  **Iterate through `dimensions`:**
    *   For each rectangle `i` in the `dimensions` array:
        *   Extract its length (`l = dimensions[i]`) and width (`w = dimensions[i]`).
        *   Calculate the **current diagonal squared**: `currentDiagonal = (l * l) + (w * w)`.
        *   Calculate the **current area**: `currentArea = l * w`.

3.  **Comparison and Update Logic:**
    *   **If `currentDiagonal` is greater than `maxDiagonal`**:
        *   This means we found a new longest diagonal.
        *   Update `maxDiagonal = currentDiagonal`.
        *   Update `maxArea = currentArea`.
    *   **Else If `currentDiagonal` is exactly equal to `maxDiagonal`**:
        *   This means we found another rectangle with the same longest diagonal.
        *   Apply the tie-breaking rule: Update `maxArea = maximum(maxArea, currentArea)`.

4.  **Return Result:**
    *   After iterating through all rectangles, `maxArea` will hold the final answer.

## Time Complexity

The time complexity of this solution is **O(N)**, where `N` is the number of rectangles in the `dimensions` array. This is because the algorithm iterates through the `dimensions` array exactly once.
```