# Binary Subarrays With Sum (L9 / LeetCode 930)

## Problem Description
This problem focuses on finding the **total number of subarrays** within a given array where the sum of the elements is exactly equal to a specified integer `goal`.

A key constraint is that the input array is a **binary array**, meaning it contains only the integers one (1) and zero (0). A subarray is defined as a consecutive portion of an array.

## Approaches

### 1. Brute Force Solution (The Brute)

The brute force approach involves generating all possible subarrays and calculating their sum to check if it equals the `goal`.

**Complexity Analysis (Brute Force):**
*   **Time Complexity:** High (often O(N²) or worse) [21, 20, implied].

### 2. Hashing / Prefix Sums Solution (The Better Solution)

This solution utilizes the standard method for solving the general problem of counting subarrays with a sum equal to $K$, which works even when the array contains positive and negative integers. This technique uses an external map data structure to track prefix sums.

This hashing solution is considered the **most optimal solution** for the general "Count Subarray where the sum equals K" problem. However, for the specific problem of binary arrays, it is considered the "better" solution, as the special binary constraint allows for further optimization in space.

**Complexity Analysis (Hashing/Prefix Sums):**
*   **Time Complexity:** O(N).
*   **Space Complexity:** O(N) (due to the use of the external map).

### 3. Sliding Window / Two Pointers (The Optimal Solution)

The optimal solution eliminates the need for the external map, achieving O(1) space complexity by using the Two-Pointer and Sliding Window approach.

#### The Subtraction Principle
Directly using a standard sliding window to find subarrays where the sum is *equal* to the `goal` is difficult because the presence of zeros in the binary array means that removing an element from the left (L) may not affect the sum, causing ambiguity in pointer movement.

The solution is achieved indirectly by solving a related problem: counting the number of subarrays where the sum is **less than or equal to** a target value. The final answer is calculated using the subtraction principle:

$$\text{Count}(\text{Sum} = \text{Goal}) = \text{Count}(\text{Sum} \leq \text{Goal}) - \text{Count}(\text{Sum} \leq \text{Goal} - 1)$$

#### Algorithm: `Count(Sum <= K)`

A helper function is used to calculate the number of subarrays whose sum is less than or equal to the input $K$.

1.  Initialize Left (L), Right (R), `sum`, and `count` to zero.
2.  Iterate the Right pointer (R) forward and add the current element `nums[R]` to `sum`.
3.  **Shrink Window:** If `sum` exceeds $K$ (`goal`), subtract `nums[L]` from `sum` and increment L. This continues as long as the sum is exceeding the goal.
4.  **Calculate Valid Subarrays:** Once the window is valid, the number of valid subarrays ending at R is the current window length, calculated as `R - L + 1`. This length is added to the total `count`.
5.  Move R forward (`R++`).

**Edge Case:** If the input `goal` (or `Goal - 1`) is less than zero, the function should return 0, because an array containing only 1s and 0s cannot yield a negative sum. This case is particularly relevant when the goal is 0, and `Goal - 1` results in -1.

**Optimal Complexity Analysis:**

The helper function `Count(Sum <= K)` runs in **O(N)** time. In the worst-case scenario, the time complexity of the function is O(2N) because both the L and R pointers traverse the array at most once. Since the overall solution calls this function twice, the resulting complexities are:

*   **Time Complexity:** O(N).
*   **Space Complexity:** O(1) (as the external map is eliminated).