
# Leetcode 2787: Ways to Express an Integer as Sum of Powers

## Problem Description

Given two positive integers `n` and `x`, the task is to **return the number of ways `n` can be expressed as the sum of the `x`-th powers of unique positive integers**.

In other words, we need to find the number of sets of unique integers `{n1, n2, ..., nk}` such that `n = n1^x + n2^x + ... + nk^x`.

Since the result can be very large, the answer should be returned **modulo `10^9 + 7`**.

## Examples

### Example 1:

**Input:** `n = 10, x = 2`
**Output:** `1`
**Explanation:** The only possible way to express 10 as a sum of unique integers raised to the power of 2 is `1^2 + 3^2 = 1 + 9 = 10`.

### Example 2:

**Input:** `n = 4, x = 1`
**Output:** `2`
**Explanation:** The two possible ways are:
1.  `1^1 + 3^1 = 1 + 3 = 4`
2.  `4^1 = 4`

## Approach: Recursion with Memoization ( KnapSack )

This problem is a classic example of a **"Take and Skip" type problem**, which falls under the **Knapsack category** of dynamic programming problems.

The core idea involves exploring possibilities by considering each positive integer `num` (starting from 1) and deciding whether to "take" its `x`-th power or "skip" it.

### Recursive Steps:

1.  **Initialization:** The recursive process starts by considering `num = 1`.
2.  **Calculate Current Power:** For the current `num`, calculate `currentPowerValue = num^x`.
3.  **Decision Points:** For each `num`, there are two primary choices:
    *   **Option 1: Take `currentPowerValue`**
        *   If `currentPowerValue` is less than or equal to the remaining `n` (i.e., `currentPowerValue <= n`), then recursively call the function. The new `n` will be `n - currentPowerValue` (representing the remaining sum needed), and the next number to consider will be `num + 1`.
        *   It's crucial to increment `num` to `num + 1` because the problem requires **unique integers**; `num` cannot be taken again.
    *   **Option 2: Skip `currentPowerValue`**
        *   Recursively call the function. In this case, `n` remains unchanged, and the next number to consider is `num + 1`.
4.  **Combine Results:** The total number of ways for the current state is the sum of the ways found from both the "take" and "skip" options (`take + skip`).

### Base Cases:

To define when the recursion stops and what values to return:

*   **`if n == 0`**: This condition indicates that the sum of the chosen powers has **exactly reached the target `n`**. This signifies a valid combination. In this case, **return 1** (one way found).
*   **`if n < 0`**: This means the sum of the chosen powers has **exceeded the target `n`**, leading to an invalid path. **Return 0** (no valid way found through this path).
*   **`if currentPowerValue > n`**: This is an optimization. If the `x`-th power of the current `num` is already greater than the remaining `n` (the target sum), then this `num` (and any subsequent larger numbers) cannot possibly be part of a valid sum. **Return 0** immediately, as no further valid combinations can be formed from this point onwards.

### Memoization:

To optimize the recursive solution and prevent re-computation of already solved subproblems (which arise due to **overlapping subproblems**), memoization is applied.

*   A 2D DP table (e.g., `dp[n_remaining][current_num]`) should be used to store the results of subproblems.
*   The table should be initialized with a sentinel value (e.g., -1) to indicate that a state has not yet been computed.
*   Before making a recursive call for a given `(n, num)` state, first check if the result for this state is already stored in the DP table. If it is (i.e., not -1), return the stored value directly.
*   After computing a result for a particular `(n, num)` state, store it in the DP table before returning it, so it can be reused later.

### Time and Space Complexity:

*   **Without Memoization:** The time complexity is **exponential** (e.g., `O(2^K)`, where `K` is the number of integers considered), as each number presents two choices (take or skip).
*   **With Memoization:**
    *   **Time Complexity:** `O(N * M)`, where `N` is the maximum possible value of the input `n` (up to 300) and `M` is the maximum possible value of `num` that needs to be considered. `M` can be up to `N` (e.g., when `x=1`, `num` can go up to `N`). Therefore, in the worst-case scenario (when `x=1`), the time complexity can be approximately `O(N^2)`.
    *   **Space Complexity:** `O(N * M)` for the memoization table, plus the space for the recursion stack.


