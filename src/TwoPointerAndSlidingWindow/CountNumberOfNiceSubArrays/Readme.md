# Count Number of Nice Subarrays (L10 / LeetCode 1248)

## Problem Description
You are given an array of integers, `nums`, and an integer value $K$.

The task is to **count the total number of subarrays** (contiguous portions of the array) where the count of odd integers within that subarray is **exactly equivalent** to the given integer $K$. Subarrays meeting this criterion are referred to as "nice subarrays".

For instance, if $K=3$, a subarray is considered "nice" if it contains exactly three odd numbers.

## Core Insight: Transformation to Binary Array

The key to solving this problem efficiently is recognizing that it is exactly similar to a known problem: **Binary Subarrays With Sum** (L9).

This transformation is achieved by mapping the input array to a binary array:
1.  All **odd numbers** are treated as $1$.
2.  All **even numbers** are treated as $0$.

By making this conversion, the original problem ("count subarrays with $K$ odd numbers") becomes mathematically identical to the previous problem ("count subarrays where the sum is equivalent to $K$").

## Solution Strategy: Leveraging the O(N) Optimal Approach

Since the problem is the exact same as "Binary Subarrays With Sum," the most optimal solution for that problem is applied here. This approach utilizes the Two-Pointer and Sliding Window technique, typically achieving O(N) time complexity and O(1) space complexity.

The optimal strategy relies on the **Subtraction Principle** to avoid ambiguity caused by zeros:
$$\text{Count}(\text{Odd Count} = K) = \text{Count}(\text{Odd Count} \leq K) - \text{Count}(\text{Odd Count} \leq K - 1)$$

### Implementation Adjustment (Modulo 2)

When implementing the Two-Pointer/Sliding Window solution, a slight modification is necessary to handle the original numbers:

*   Instead of simply adding or subtracting `nums[R]` or `nums[L]` to the running sum, you must use the **modulo 2** operator (`modul 2`) on the element.
*   If the element is odd, `element % 2` yields $1$, which is correctly added to the sum.
*   If the element is even, `element % 2` yields $0$, which is correctly added to the sum.

By using this modulo transformation, the exact same logic and code structure used for finding binary subarrays with a sum $K$ can be copied and applied successfully.

### Complexity Analysis (Optimal Solution)
The overall complexity mirrors the O(N) optimal solution for Binary Subarrays with Sum:

*   **Time Complexity:** O(N).
*   **Space Complexity:** O(1).