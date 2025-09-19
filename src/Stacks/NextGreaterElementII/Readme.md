
# LeetCode: Next Greater Element - II (L6)

This document summarizes the approach for solving the **Next Greater Element - II** problem, which requires finding the NGE in a circular array, using the **Monotonic Stack** technique. This problem is an extension of the basic Next Greater Element problem.

## 1. Problem Statement and Twist

The core task is to find the **Next Greater Element (NGE)** for every element in a given array. The NGE is the first element strictly greater than the current element when looking to the right.

The key **twist** in this version (Part II) is the requirement for **circular traversal**:

*   If the NGE is not found when looking strictly to the right, the search must continue in a **circular direction** back to the start of the array.
*   If no greater element is found after a full circular search, the NGE is **-1**.

## 2. Brute Force Solution

A straightforward, non-optimized solution involves standing at each element and running two loops:

1.  **Loop 1 (Rightward Check):** Look from index $J = I + 1$ till $N - 1$.
2.  **Loop 2 (Circular Check):** If not found, look from $J = 0$ till $I - 1$.

### Brute Force Complexity

*   **Time Complexity:** $O(N^2)$.
*   **Space Complexity:** $O(N)$ (to store the result).

This approach is considered inefficient, and optimization is required.

## 3. Optimized Approach: Monotonic Stack with Circular Indexing

The optimal solution aims to reduce the time complexity using the **Monotonic Stack** concept, which requires $O(N)$ knowledge of the preceding NGE problem. The circular nature is simulated using index manipulation, avoiding actual array duplication.

### A. Simulating the Circular Array

To simulate the circular check for every element, we **hypothetically double the array** to cover $2N$ elements. Instead of physically coding a $2N$ length array, we use the modulo operator (`% N`) to map the hypothetical indices back to the original array values:

*   If $N=5$, indices 0 through 4 are normal.
*   Hypothetical index 5 maps to $5 \pmod 5 = 0$.
*   Hypothetical index 6 maps to $6 \pmod 5 = 1$.

The traversal runs for $2N$ iterations, covering two full rounds of the original array.

### B. Implementation Logic

The solution involves traversing the hypothetical doubled array backward, from index $2N - 1$ down to 0:

1.  **Initialize:** Create a result array `NG` (size $N$) and a stack `St`.
2.  **Backward Traversal:** Loop `I` from $2N - 1$ down to 0.
3.  **Get Current Value:** Calculate the original array index using `index = I % N`. The value is `array[index]`.
4.  **Maintain Monotonicity:** While the stack is non-empty AND the element at the `stack.top()` is less than or equal to the current value (`array[I % N]`), continuously perform `stack.pop()`. This maintains the decreasing nature of the stack.
5.  **Calculate NGE (If Needed):** The NGE result is only needed for the elements in the original array (where $I < N$).
    *   If $I < N$:
        *   If the stack is empty, $NG[I] = -1$.
        *   If the stack is not empty, the top element is the NGE: $NG[I] = stack.top()$.
6.  **Push Current Element:** Push the current element's value onto the stack: `stack.push(array[I % N])`.

### C. Complexity Analysis

*   **Time Complexity:** $O(4N)$ (explicitly presented as such, but often simplified to $O(N)$). This is because at most $2N$ elements are pushed, and at most $2N$ elements are popped across the entire $2N$ iteration loop.
*   **Space Complexity:** $O(2N)$ for the stack in the worst case, plus $O(N)$ for storing the `NG` answer array.