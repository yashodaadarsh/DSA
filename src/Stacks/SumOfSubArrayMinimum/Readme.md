
# L9. Sum of Subarray Minimums | Stack and Queue Playlist

This document summarizes the approach for solving the **Sum of Subarray Minimums** problem, which involves calculating the sum of the minimum elements found across all possible subarrays of a given array. The video discusses both the brute force and the optimized $O(N)$ solution using the **Monotonic Stack** technique.

## 1. Problem Statement

Given an array of numbers, the task is to find the **sum of the minimums** of all possible subarrays.

The resulting sum might be very large, so the result must be calculated modulo $10^9 + 7$.

## 2. Brute Force Approach ($O(N^2)$)

The straightforward approach involves generating all possible subarrays, finding the minimum element in each subarray, and summing these minimums.

### Implementation (Conceptual)

1.  Initialize `sum = 0`.
2.  Use a nested loop structure:
    *   Outer loop (`I`) iterates from 0 to $N-1$ (defining the start of the subarray).
    *   Inner loop (`J`) iterates from $I$ to $N-1$ (extending the subarray).
3.  Inside the inner loop, keep track of the current `minimum` element for the subarray `I` to `J`. Update `minimum` every time a new element `array[J]` is added.
4.  Add the current `minimum` to the `sum` (applying the modulo operation after addition).

### Brute Force Complexity

*   **Time Complexity:** $O(N^2)$ due to the nested loops.
*   **Space Complexity:** $O(1)$ (using constant external space).

## 3. Optimal Approach: Contribution Method ($O(N)$ Solution)

Since the $O(N^2)$ time complexity is inefficient, the problem must be optimized. The optimized solution breaks down the problem from summing subarray minimums to finding the **individual contribution** of each element to the final answer.

### A. Contribution Logic

For a specific element $X$ at index $I$, the contribution is calculated as:

$$
\text{Contribution} = (\text{Total subarrays where } X \text{ is the minimum}) \times X
$$

The key is determining how many subarrays have $X$ as their minimum.

### B. Finding the Number of Subarrays (Left and Right Count)

For an element at index $I$ to be the minimum of a subarray, that subarray must not contain any element smaller than $X$.

This requires determining two quantities using index differences:

1.  **Left Count (L):** The number of elements to the left (including the element itself) that are greater than $X$. This is determined by the index of the **Previous Smaller or Equal Element (PSEE)**.
    *   $L = \text{Current Index } (I) - \text{PSEE Index}$.
2.  **Right Count (R):** The number of elements to the right (including the element itself) that are greater than $X$. This is determined by the index of the **Next Smaller Element (NSE)**.
    *   $R = \text{NSE Index} - \text{Current Index } (I)$.

The total number of subarrays for which $X$ is the minimum is the product of these two counts: $\text{Total Subarrays} = L \times R$.

### C. Handling Edge Cases (Equal Elements)

When multiple equal elements exist (e.g., ``), they can lead to duplicate counting of subarrays if not handled carefully.

To solve this, a strict rule is applied:

*   When calculating the **Next Smaller Element (NSE)**, we include elements that are equal in the pop condition (`array[stack.top()] >= array[I]`).
*   When calculating the **Previous Smaller or Equal Element (PSEE)**, we include the equals condition in the check for the preceding element (`array[stack.top()] > array[I]`). By seeking a *strictly smaller* previous element (or the start of the array), we ensure that the subarrays where the current element is the minimum are uniquely counted. This is why the function used is `find previous smaller or equal element (PSEE)`.

### D. Finding NSE and PSEE using Monotonic Stack

Both `find NSE` and `find PSEE` functions are implemented using the **Monotonic Stack** pattern, which stores **indexes** rather than values.

1.  **Find NSE:** Traverses backward (from $N-1$ to 0). Pops elements that are greater than or **equal to** the current element (`array[stack.top()] >= array[I]`). If no NSE is found, the index is assigned $N$.
2.  **Find PSEE:** Traverses forward (from 0 to $N-1$). Pops elements that are strictly **greater than** the current element (`array[stack.top()] > array[I]`). If no PSEE is found, the index is assigned $-1$.

### E. Final Calculation

The total sum is computed by iterating through all elements and adding the contribution:

$$\text{Total} = \sum_{I=0}^{N-1} (\text{Right} \times \text{Left} \times \text{array}[I])$$

It is critical to use `long long` (`1LL`) during multiplication to avoid integer overflow before applying the modulo operation.

## 4. Optimal Complexity Analysis

The use of Monotonic Stacks ensures that all auxiliary calculations are completed in linear time.

*   **Time Complexity:** $O(N)$. Calculating NSE and PSEE is $O(2N)$ each, and the final computation is $O(N)$, resulting in an overall complexity around $O(5N)$, which is linear time.
*   **Space Complexity:** $O(N)$. This includes $O(N)$ for storing the NSE and PSEE results, and $O(N)$ for the stacks used in those computation steps.
```