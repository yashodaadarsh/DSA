# Subarrays with K Different Integers (L11 / LeetCode 1248)

## Problem Description
This problem, part of the Two-Pointer and Sliding Window playlist, requires counting the **number of subarrays** that contain **exactly $K$ different integers**.

You are given an array of integers and an integer $K$. A subarray is considered valid only if the number of unique, distinct integers within it is precisely $K$.

## Example
If $K=3$, a subarray containing $\{1, 3, 4, 4\}$ is valid because it has three different integers (1, 3, 4). A subarray containing four different integers (e.g., $\{1, 2, 1, 3, 4, 5\}$) is invalid because it exceeds $K$.

## Approaches

### 1. Brute Force Solution (Naive Solution)

The extreme naive solution is to generate all possible subarrays.

**Algorithm Steps:**
1.  Use nested loops: An outer loop (I) for the starting index and an inner loop (J) for the ending index.
2.  Maintain a map data structure for each subarray to track the frequency of all unique elements seen so far.
3.  If the size of the map (which represents the count of different integers) is exactly equal to $K$, increment the total count.
4.  If the map size exceeds $K$, break out of the inner loop (J) because adding more elements cannot reduce the size of unique integers.

**Complexity Analysis (Brute Force):**
*   **Time Complexity:** O(N²).
*   **Space Complexity:** O(N) in the worst-case scenario, if all elements in the array are unique and stored in the map.

### 2. Optimal Solution: Sliding Window and Subtraction Principle

A typical two-pointer approach designed to find an *exact* count is unreliable because deciding when to expand the window (R) versus when to shrink it (L) leads to missing valid subarrays.

The optimal method solves the problem by converting the task of finding an "equal to $K$" count into solving for "lesser than or equal to $K$" twice.

#### The Subtraction Principle

The final count of subarrays with exactly $K$ distinct integers is determined by subtracting the count of subarrays with at most $K-1$ distinct integers from the count of subarrays with at most $K$ distinct integers:

$$\text{Count}(\text{Exactly } K) = \text{Count}(\text{Lesser than or equal to } K) - \text{Count}(\text{Lesser than or equal to } K - 1)$$

#### Algorithm: `Count(Lesser than or equal to K)`

A helper function is implemented using the standard Sliding Window technique to count subarrays with at most $K$ distinct integers.

1.  Initialize Left (L), Right (R), `count`, and a map (storing number and frequency).
2.  Iterate the Right pointer (R) forward, inserting the new element `nums[R]` into the map and updating its frequency.
3.  **Shrink Window:** Use a `while` loop to check if the map size (distinct integers) exceeds $K$.
    *   If the map size is too large, decrement the frequency of `nums[L]`.
    *   If the frequency of `nums[L]` drops to zero, the element must be erased from the map.
    *   Move L forward (`L++`).
4.  **Calculate Valid Subarrays:** Once the map size is $\leq K$, the current segment $[L, R]$ is valid. Because the largest valid subarray is valid, all its sub-parts (subarrays ending at R) are also valid. The total number of new valid subarrays created by the addition of R is the current window length: $R - L + 1$. This length is added to the total `count`.

**Optimal Complexity Analysis:**

The solution involves calling the O(N) helper function twice.

*   **Time Complexity:** O(N). Although there is a nested loop structure, both the L and R pointers traverse the array at most once, leading to an overall O(2N) time complexity, which simplifies to **O(N)**.
*   **Space Complexity:** O(N). The space complexity is determined by the size of the map, which in the worst case must store every unique element in the array.