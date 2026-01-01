
# L4. Max Consecutive Ones III | Two Pointers and Sliding Window Playlist

This document summarizes the solution for the "Max Consecutive Ones Part three" problem, which utilizes the Two-Pointers and Sliding Window techniques to find the maximum length of consecutive ones after flipping at most $K$ zeros.

## Problem Statement

You are given an array containing only 1s and 0s, and an integer $K$. Your task is to find the **maximum length of consecutive ones** you can achieve if you are allowed to flip **at most $K$ zeros** to ones.

The core challenge can be converted into finding the **longest subarray** that contains **at most $K$ zeros**. The segment itself does not need to be printed; only the maximum length is required.

## Approach 1: Brute Force (O(N²))

The brute force method involves generating all possible subarrays and checking the count of zeros within each one.

1.  **Generation:** Nested loops are used, where the outer loop starts the subarray (I) and the inner loop extends it (J).
2.  **Validation/Stopping:** Keep a count of zeros. If the number of zeros encountered exceeds $K$ (the maximum allowed), stop extending the current subarray (J loop breaks).
3.  **Length:** The length of the valid subarray is calculated as $J - I + 1$.

This results in a time complexity of **O(N²)**, which is generally considered too slow for interviews.

## Approach 2: Optimal Sliding Window (O(N))

Since the problem involves finding the **longest subarray** based on a condition, the Two-Pointer/Sliding Window technique is used to achieve linear time complexity. The video discusses two variations of the sliding window, with the most optimized version eliminating an inner loop for cleaner code and guaranteed O(N) performance.

### Mechanism Overview

The solution uses a right pointer (R) to expand the window and a left pointer (L) to shrink it when the zero count exceeds $K$.

1.  **Initialization:** Initialize `max_length` (maximum length found), `L` (left pointer), `R` (right pointer), and `zeros` (current count of zeros in the window) to zero.
2.  **Expansion (R moves):** The right pointer (R) drives the main loop, moving forward through the array. If the element `nums[R]` is 0, the `zeros` count is incremented.
3.  **Shrinking Condition:** If the count of `zeros` exceeds $K$, the window has become invalid.
4.  **Shrinking (L moves - O(N) Optimization):** To ensure the window remains valid (i.e., `zeros` $\le K$), the left pointer (L) must move forward. In the most optimal solution, this shrinking is managed by an **`if` condition** instead of a continuous `while` loop:
    *   If `nums[L]` is 0, decrement the `zeros` count.
    *   The left pointer `L` is moved forward one place (`L++`) regardless of whether the element was 0 or 1.
    *   This technique prevents the window size from increasing when the constraint is violated, ensuring the `max_length` update remains accurate based on the longest valid segment found so far.
5.  **Length Update:** If the window is valid (`zeros` $\le K$), the current length (`R - L + 1`) is compared against and potentially updates the `max_length`.

### Pseudocode (Most Optimal O(N) version)

```
function maxConsecutiveOnesIII(nums, K):
L = 0         // Left Pointer
zeros = 0     // Count of zeros in the current window
max_length = 0

    // R (Right pointer) iterates through the array and drives the expansion
    for R from 0 till size - 1:
        
        // 1. Expansion: Update zero count
        if nums[R] == 0:
            zeros = zeros + 1 // or zeros++
            
        // 2. Shrinking: If the window is invalid (zeros > K)
        if zeros > K:
            // Check if the element being removed from the left is a zero
            if nums[L] == 0:
                zeros = zeros - 1 // zeros--
            
            // Move L forward, regardless of whether nums[L] was 0 or 1
            L = L + 1 

        // 3. Update Max Length: Only update if the window is currently valid (or held constant)
        // Note: Due to the movement logic, the max length must be checked after potential L movement
        // We know that if zeros > K, the length is not updated to be longer than the previous max.
        // We can check if the current window is valid or simply calculate the length.
        if zeros <= K:
             current_length = R - L + 1
             max_length = Max(max_length, current_length)
        
    return max_length
```

## Complexity Analysis

This approach provides optimal performance by ensuring both pointers traverse the array at most $N$ times.

*   **Time Complexity:** O(N). This complexity is achieved because the algorithm avoids an internal, nested `while` loop, ensuring every operation within the main loop is O(1).
*   **Space Complexity:** O(1), as no external space is used relative to the input size.