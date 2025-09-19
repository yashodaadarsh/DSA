
# L5. Next Greater Element (NGE) | Monotonic Stack Introduction

This document summarizes the methodology discussed in the video "L5. Next Greater Element | Stack and Queue Playlist," focusing on solving the Next Greater Element problem using the **Monotonic Stack** technique.

## 1. Problem Statement

Given an array of elements, the task is to find the **Next Greater Element (NGE)** for every element.

The NGE of an element $X$ is the **first** element to its **right** that is strictly greater than $X$. If no such element exists (i.e., all elements to the right are smaller or the end of the array is reached), the NGE is designated as -1.

*Example:* If the current element is 6, and the elements to the right are [0, 8, 1...], 8 is the NGE because it is the first element greater than 6 encountered when looking right.

## 2. Initial Approach (Brute Force)

The most straightforward approach uses nested loops:

1.  **Outer Loop (i):** Iterate through every element from index 0 to $n-1$.
2.  **Inner Loop (j):** For the current element `array[i]`, iterate through all elements to its right (starting from $i+1$) until the end of the array.
3.  **Find NGE:** If `array[j]` is found to be greater than `array[i]`, set `NG[i] = array[j]` and break the inner loop.

### Brute Force Complexity

*   **Time Complexity:** $O(N^2)$. (This is generally not acceptable in an interview setting, requiring optimization).
*   **Space Complexity:** $O(N)$ (required to store and return the answer array `NG`).

## 3. Optimized Approach: Monotonic Stack (O(N) Solution)

To optimize the solution, the standard traversal order must be changed, and a stack must be used to maintain order and efficiently find the NGE.

### A. Monotonic Stack Concept

A **Monotonic Stack** is a stack used to store elements in a **specific order** (either increasing or decreasing).

In this NGE problem, the elements in the stack must be maintained in a **decreasing order**. This intuition comes from the "light pole concept": an element can only see elements taller than itself to its right; any smaller elements are hidden and thus irrelevant for future calculations.

### B. Traversal and Intuition

1.  **Backward Traversal:** Since the NGE must be on the right, traversing the array from the **back** ($n-1$ to 0) ensures that when checking an element `array[i]`, all necessary elements to its right have already been processed and stored in the stack.

2.  **Maintaining Monotonicity:** When processing `array[i]`, any elements currently on the stack that are smaller than or equal to `array[i]` are useless. They can never be the NGE for any element that comes before `array[i]` (elements processed later in the backward traversal). Therefore, they must be `pop`ped off.

### C. Pseudo Code

The solution requires backward traversal and stack manipulation:

1.  Initialize an array `NG` (Next Greater Element results) of size $N$.
2.  Initialize a stack `St`.
3.  **Traverse backwards:** Loop `i` from $n-1$ down to 0.
    *   **Stack Maintenance (Pop Smaller/Equal Elements):**
        ```
        while (stack is not empty AND stack.top() <= array[i]):
            stack.pop()
        ```
        The elements must be removed if they are lesser than or **equal to** the current element `array[i]`.
    *   **Check Result:**
        *   If the stack is empty after popping, it means no element to the right is greater: `NG[i] = -1`.
        *   Otherwise, the element at the top of the stack is the NGE: `NG[i] = stack.top()`.
    *   **Push Current Element:**
        ```
        stack.push(array[i])
        ```
        The current element is pushed onto the stack to potentially serve as the NGE for elements processed further left.
4.  Return the `NG` list.

## 4. Optimized Complexity Analysis

The optimized approach achieves linear time complexity because elements are processed extremely efficiently.

*   **Time Complexity:** $O(2N)$ (effectively **$O(N)$**).
    *   The primary loop runs $N$ times.
    *   The `while` loop containing `stack.pop()` does not run $N$ times for every iteration. Since every element is pushed onto the stack exactly once, the total number of `pop` operations performed across the entire algorithm is limited to at most $N$ (because an element can be popped only once). Thus, the total time is proportional to $N + N$, resulting in $O(2N)$.
*   **Space Complexity:** $O(N)$.
    *   This is $O(N)$ for the result array `NG` plus $O(N)$ for the stack storage in the worst case.