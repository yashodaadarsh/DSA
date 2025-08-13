
# Power of Three (LeetCode 326)

## Problem Description

Given an integer `n`, return `true` if it is a power of three. Otherwise, return `false`.
An integer `n` is a power of three if there exists an integer `x` such that `n = 3^x`.

### Examples

*   **Example 1:**
    *   `n = 27`
    *   **Output: true** (since 27 = 3 * 3 * 3 = 3^3)

*   **Example 2:**
    *   `n = 7`
    *   **Output: false** (since 7 is not 3 to the power of something)

*   **Edge Cases:**
    *   `n = 0` or any negative number are **not** powers of three. For such inputs, the function should return `false`.

## Follow-up Question

Can you solve it **without loops or recursion in constant time**?

## Approaches

Here are four different approaches to solve this problem, ranging from simple iterative/recursive solutions to more optimized constant-time solutions.

### Approach 1: Iterative Division

*   **Description**: Continuously divide `n` by 3 as long as `n` is greater than 0 and `n` is perfectly divisible by 3 (`n % 3 == 0`). If, after all divisions, the final value of `n` is 1, then the original number was a power of three. If `n` is not 1 at the end, or if it was initially `0` or negative, it is not a power of three.
    *   **Example**: For `n = 27`:
        *   27 % 3 == 0, `n = 27 / 3 = 9`
        *   9 % 3 == 0, `n = 9 / 3 = 3`
        *   3 % 3 == 0, `n = 3 / 3 = 1`
        *   `n` is 1, so return `true`.
    *   **Example**: For `n = 15`:
        *   15 % 3 == 0, `n = 15 / 3 = 5`
        *   5 % 3 != 0, stop. `n` is 5, not 1, so return `false`.
*   **Time Complexity**: **O(log₃ n)**, because `n` is repeatedly divided by 3 until it reaches 1.
*   **Space Complexity**: **O(1)** (constant space), as no extra memory is used.

### Approach 2: Recursive Division

*   **Description**: This approach is similar to iterative division but uses recursion.
    *   **Base Cases**:
        *   If `n <= 0`, return `false`.
        *   If `n == 1`, return `true` (since 3^0 = 1).
    *   **Recursive Step**: If `n` is not divisible by 3 (`n % 3 != 0`), return `false`. Otherwise, recursively call the function with `n / 3`.
*   **Time Complexity**: **O(log₃ n)**, due to `n` being divided by 3 in each recursive call.
*   **Space Complexity**: **O(log₃ n)**, due to the recursion call stack growing with the depth of recursive calls.

### Approach 3: Logarithm Approach

*   **Description**: If `n` is a power of three, then `n = 3^x` for some integer `x`. By taking the logarithm of both sides (e.g., base 10), we get `log₁₀(n) = x * log₁₀(3)`. This means `x = log₁₀(n) / log₁₀(3)`. The method involves calculating this `x` and then checking if `x` is a perfect integer. This can be done by comparing `x` (as a `double` or `float`) with its integer cast (e.g., `(double)x == (int)x`).
*   **Time Complexity**: **O(1)**, as logarithmic operations are typically constant-time operations.
*   **Space Complexity**: **O(1)** (constant space).

### Approach 4: Precomputed Maximum Power of Three

*   **Description**: This is an efficient and "one-liner" solution that directly addresses the follow-up question for constant time without loops or recursion.
    *   The largest integer power of three that can be stored in a standard 32-bit signed integer is **3^19**, which equals **1,162,261,467**. (Note: 3^20 would exceed the 32-bit integer limit).
    *   The logic is that if `n` is a power of three, and `n` is positive, then `n` **must** be a divisor of the largest 32-bit power of three (1,162,261,467).
    *   Therefore, the check involves two conditions:
        1.  `n` must be greater than zero (`n > 0`).
        2.  The maximum 32-bit power of three must be perfectly divisible by `n` (`1162261467 % n == 0`).
    *   If both conditions are true, `n` is a power of three.
*   **Key Constant**:
    ```
    MAX_POWER_OF_THREE_32_BIT = 1162261467; // Which is 3^19
    ```
*   **Time Complexity**: **O(1)**, as it involves a single modulo operation and a comparison.
*   **Space Complexity**: **O(1)** (constant space).

```
```