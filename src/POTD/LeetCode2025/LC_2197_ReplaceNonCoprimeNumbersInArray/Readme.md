
# Leetcode 2197: Replace Non-Coprime Numbers in Array

## Problem Description

You are given an integer array `nums`. You need to perform the following steps repeatedly until no further operations are possible:

1.  **Find Adjacency:** Find any two adjacent numbers, let's say `x` and `y`, in the array that are **non-coprime**.
    *   Two values `x` and `y` are considered non-coprime if their **Greatest Common Divisor (GCD)** is greater than one (GCD(x, y) > 1).
2.  **Stop Condition:** If no such adjacent non-coprime numbers are found, the process stops.
3.  **Perform Replacement:** Otherwise, delete the two non-coprime numbers (`x` and `y`) and replace them with their **Least Common Multiple (LCM)**.
4.  **Repeat:** Repeat this process as long as you keep finding adjacent non-coprime numbers.
5.  **Return:** Return the final modified array.

**Important Notes:**
*   It has been shown that **replacing adjacent non-coprime numbers in any arbitrary order will always lead to the same final result**. This simplifies the approach as the order of merging doesn't affect the correctness of the final array.
*   The test cases are generated such that the values in the final array are less than or equal to 10^8.

## Example Walkthrough

Let's consider an example: `nums =`

1.  **Initial Array:** ``
2.  **Step 1 (Left to Right):**
    *   Consider `6` and `10`. GCD(6, 10) = 2 (which is > 1), so they are non-coprime.
    *   **LCM Calculation:** LCM(a, b) = (a * b) / GCD(a, b).
    *   LCM(6, 10) = (6 * 10) / 2 = 30.
    *   Replace `6` and `10` with `30`. The array effectively becomes ``.
3.  **Step 2:**
    *   Consider `30` (the newly merged number) and `15`. GCD(30, 15) = 15 (which is > 1), so they are non-coprime.
    *   LCM(30, 15) = (30 * 15) / 15 = 30.
    *   Replace `30` and `15` with `30`. The array effectively becomes ``.
4.  **Step 3:**
    *   Consider `30` and `7`. GCD(30, 7) = 1. They are coprime.
    *   No further adjacent non-coprime pairs are found.
5.  **Final Array:** ``.

*(The video demonstrates that processing from right-to-left or from the middle also yields the same result, confirming the problem statement's guarantee).*

## Key Concepts and Definitions

*   **Non-Coprime:** Two numbers `x` and `y` are non-coprime if their Greatest Common Divisor (GCD) is greater than 1.
*   **GCD (Greatest Common Divisor):** The largest positive integer that divides both numbers without leaving a remainder.
*   **LCM (Least Common Multiple):** The smallest positive integer that is a multiple of both numbers.
    *   **Formula:** `LCM(a, b) = (a * b) / GCD(a, b)`.
    *   To prevent potential integer overflow when calculating `a * b` for large numbers, it's safer to use the formula: `LCM(a, b) = (a / GCD(a, b)) * b`.

## Solution Approach

The problem can be efficiently solved by using a **stack-like data structure** (a `std::vector` in C++ can be used as a stack) and processing the input array from left to right. The crucial insight is that if a new number merges with the top of the stack, the *newly formed* merged number itself might need to merge with the element below it in the stack, and so on.

**Algorithm Steps:**

1.  **Initialize Result Vector:** Create an empty `std::vector<int> result` which will act as your stack.
2.  **Iterate Through `nums`:** Process each `num` in the input array `nums` from left to right.
3.  **Merging Logic:** For each `num`:
    *   **While Loop for Continuous Merging:** Enter a `while` loop that continues as long as:
        *   The `result` vector is not empty (`!result.empty()`).
        *   The current `num` and the `result.back()` (the top element of the stack) are non-coprime (i.e., `GCD(result.back(), num) > 1`).
    *   **Inside the While Loop (Merge Operation):**
        *   Get the `previous` adjacent element from `result.back()`.
        *   Calculate the `gcd_val` of `previous` and `num`.
        *   Remove `previous` from the `result` vector by calling `result.pop_back()`.
        *   Calculate the new `lcm_val` using the formula `(previous / gcd_val) * num` to avoid overflow.
        *   Update `num` to be this `lcm_val`. This updated `num` will then be checked against the *new* `result.back()` in the next iteration of the `while` loop.
4.  **Push to Result:** Once the `while` loop terminates (either `result` is empty or `num` is coprime with `result.back()`), push the final (potentially merged) `num` into the `result` vector (`result.push_back(num)`).
5.  **Return:** After processing all numbers in `nums`, return the `result` vector.

## Time and Space Complexity

*   **Time Complexity:** **O(N * log(Min(A, B)))**
    *   Each number from the input array `nums` is pushed onto the `result` vector (stack) at most once and popped at most once.
    *   In the worst case, a number might trigger multiple merges with elements already in the stack. However, each push and pop operation amortizes to O(1) in a stack.
    *   The dominant factor is the GCD calculation, which has a time complexity of `O(log(Min(A, B)))`, where A and B are the numbers for which GCD is calculated. Since GCD is called for each merge operation, and each number is involved in a merge at most a constant number of times across all merges (pushes/pops), the overall complexity is O(N * log(Min(A, B))).

*   **Space Complexity:** **O(N)**
    *   The `result` vector (stack) can store up to N elements in the worst case (e.g., if no merges occur). If the output array is not considered auxiliary space, then auxiliary space would be O(1).
```