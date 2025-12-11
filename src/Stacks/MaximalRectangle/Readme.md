# Maximal Rectangle (L13)

## Introduction
This repository addresses the "Maximal Rectangle" problem, which involves finding the largest area rectangle composed entirely of ones within a given 2D binary matrix. The solution leverages the concepts derived from the "Largest Rectangle in Histogram" problem.

## Problem Statement
Given an $N \times M$ 2D matrix composed solely of 1s and 0s, the goal is to calculate and return the maximum area of a rectangular region containing **only ones**.

*   Any rectangle that contains a zero cannot be considered.
*   For example, an area of 6 (a $2 \times 3$ rectangle) might be the maximal area found.

## Prerequisite
Solving this problem relies heavily on the algorithm used for **Largest Rectangle in Histogram**. That approach must be understood and available as a sub-function to solve the maximal rectangle efficiently.

## Core Approach: Reduction to 1D Histogram Problems
The critical insight is to transform the 2D matrix problem into a sequence of 1D Largest Rectangle in Histogram problems.

1.  We process the matrix row by row.
2.  For each row, we calculate the continuous height of '1's accumulated from the rows above it. This accumulated height forms the "bars" of a histogram for that specific row.
3.  The `Largest Histogram` function is applied to this newly constructed 1D array of bar heights.
4.  The overall maximal area is the maximum value returned by the `Largest Histogram` function across all rows.

## Implementation Details: Calculating Bar Heights (Prefix Sum)
To efficiently determine the height of the histogram bars for each row, the concept of **prefix sum** is used. We pre-compute a 2D array storing the accumulated heights of 1s.

### Height Calculation Logic
The height calculation is performed by summing the vertical sequence of ones (top-to-down summations):

1.  We iterate column-wise ($J$) and then row-wise ($I$).
2.  If the current cell `Matrix[I][J]` contains a '1', we increment a running sum (the bar height).
3.  If the current cell `Matrix[I][J]` encounters a **'0'**, the accumulation of height stops at that point, and the running sum must be reinitialized to zero.
4.  The running sum is stored in the `prefix_sum` matrix. This matrix now contains all the necessary bar heights required to run the histogram algorithm on each row.

## Pseudocode Algorithm

```
function MaximalRectangle(Matrix):
    N = number of rows
    M = number of columns
    max_area = 0
    
    // 1. Initialize Prefix Sum Array (for bar heights)
    prefix_sum = new N x M array (initialized to zeros)

    // 2. Compute Bar Heights (Prefix Sum Calculation)
    for J from 0 to M-1: // Iterate through columns
        sum = 0
        for I from 0 to N-1: // Iterate through rows (top-to-down summation)
            if Matrix[I][J] is '0':
                sum = 0 // Reset sum if a zero is encountered
            else:
                sum += 1 
            prefix_sum[I][J] = sum // Store the bar height

    // 3. Compute Max Area over all Histograms
    for I from 0 to N-1: // Iterate through each row
        // Pass the row (a 1D array of bar heights) to the largest histogram function
        current_max = LargestHist(prefix_sum[I]) 
        max_area = Max(max_area, current_max)
    
    return max_area
```

## Complexity Analysis

| Metric | Complexity | Rationale |
| :--- | :--- | :--- |
| **Time Complexity** | O($N \times M$) | The computation of the prefix sum takes O($N \times M$). The subsequent iteration over $N$ rows, each calling the `Largest Histogram` function (which runs in O($2M$) time), results in a total complexity of O($N \times M + N \times 2M$), simplifying to **O($N \times M$)**. |
| **Space Complexity** | O($N \times M$) | Space is required to store the auxiliary `prefix_sum` 2D array. |

---
**Connecting Concepts:**
The Maximal Rectangle problem is effectively solved by stacking the 2D problem on top of the 1D Largest Histogram solution. You calculate the maximum possible height for a rectangle ending at any given cell, and then use the specialized 1D histogram solver to find the maximum lateral width that height can achieve.