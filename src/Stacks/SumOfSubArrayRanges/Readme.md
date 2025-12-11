# Sum of Subarray Ranges (L10)

## Introduction
This repository contains the solution and explanation for the "Sum of Subarray Ranges" problem, which is part of the Stack and Queue playlist.

## Problem Statement
The user is given an array of numbers and must return the **sum of all subarray ranges**.

### Key Definitions
1.  **Subarray:** A contiguous part of the original array.
2.  **Subarray Range:** The difference between the largest and the smallest element within a particular subarray.

### Example Calculation
If the sum of all subarray ranges is calculated for a given input, the final value returned must be the summation of all these ranges. For example, if the subarrays are generated, summing their respective ranges results in a total summation that boils down to 13.

*   *Example Range Calculation:* For a subarray like ``, the largest element is 4 and the smallest is 1, so the range is 3.

## Brute Force Approach

The Brute Force solution involves generating every possible subarray and calculating the range for each one.

1.  Initialize `sum` to zero.
2.  Traverse the array using an outer loop (`I`) from 0 till `N-1` to define the start of the subarray.
3.  Inside the loop, initialize `largest` and `smallest` elements to `array[I]`.
4.  Use an inner loop (`J`) starting from `I + 1` up to `N-1` to extend the subarray.
5.  In the inner loop, update `largest` by taking the maximum of the current `largest` and the new element `array[J]`.
6.  Similarly, update `smallest` by taking the minimum of the current `smallest` and `array[J]`.
7.  Add the current subarray range (`largest - smallest`) to the total `sum`.
8.  Return the final `sum`.

### Complexity (Brute Force)
*   **Time Complexity:** O(N²) (due to the nested loops used for subarray generation).
*   **Space Complexity:** O(1).

The O(N²) time complexity is typically not satisfactory and indicates a need for optimization toward O(N) or near about.

## Optimized Approach: Decomposition

The most efficient way to solve the Sum of Subarray Ranges problem is by recognizing that it can be mathematically decomposed:

$$\text{Sum of Subarray Ranges} = (\text{Summation of all Subarray Maximums}) - (\text{Summation of all Subarray Minimums})$$

This approach transforms the single complex problem into two separate, simpler subproblems that can be solved efficiently:

1.  **Sum of Subarray Maximums:** Finding the sum of the largest element across all subarrays.
2.  **Sum of Subarray Minimums:** Finding the sum of the smallest element across all subarrays.

### Implementation Details
The solution assumes that functions to solve the individual maximum and minimum problems (`sumMax` and `sumMin`) are available, likely utilizing Monotonic Stack techniques derived from related problems previously solved in the playlist (e.g., Sum of Subarray Minimums).

```python
def solve_subarray_ranges(array):
    # Assume sumMax and sumMin utilize optimized O(N) techniques
    max_sum = sumMax(array)
    min_sum = sumMin(array)
    
    # The result is the subtraction of the summations
    return max_sum - min_sum 
```

### Optimized Complexity Analysis
By utilizing solutions for Subarray Maximums and Subarray Minimums that are solved in near O(N) time and space complexity:

*   **Individual Subproblem Complexity:** Each subproblem (`sumMax` and `sumMin`) typically requires about O(5N) time and O(5N) space.
*   **Overall Time Complexity:** The total complexity involves adding the time taken for both functions, resulting in a complexity near O(10N), which is considered mathematically equivalent to **O(N)** and is significantly better than O(N²).
*   **Overall Space Complexity:** The space complexity is also near O(10N), or **O(N)**.