# Largest Rectangle in Histogram (L12)

## Introduction
This repository provides the solution for the "Largest Rectangle in Histogram" problem, part of the Stack and Queue playlist. This problem requires finding the maximum area of a rectangle that can be formed within a given histogram.

## Problem Statement
You are given an array of integers, where each integer represents the **height** of a bar in a histogram. The **width** of every individual bar is constant and equal to one.

The task is to **return the area of the largest rectangle** that can be formed using the bars in the histogram.

### Example of Area Calculation
If you have a bar of height 5, and it spans a total width of 2 contiguous blocks, the area formed is $5 \times 2 = 10$.

## Key Insight: Dependence on Smaller Elements
To determine the largest rectangle that can be formed *for a specific bar* (at index $i$), you must find how far it can extend to the left and to the right without being constrained by a smaller height.

1.  **Leftward Constraint:** The rectangle breaks when the **Previous Smaller Element (PSE)** is encountered.
2.  **Rightward Constraint:** The rectangle breaks when the **Next Smaller Element (NSE)** is encountered.

### Area Calculation Formula
Once the indices of the PSE and NSE are known for bar $i$, the area can be calculated precisely:

$$\text{Area} = \text{Height}[i] \times (\text{NSE}[i] - \text{PSE}[i] - 1)$$

*   If no PSE exists, its index is assigned **-1**.
*   If no NSE exists, its index is assigned **$N$** (the size of the array).

## Optimal Solution: Single-Pass Monotonic Stack
While a simple brute-force approach has an O(N²) complexity, and a two-pass approach (pre-computing NSE and PSE separately) has a complexity near O(5N), the **most optimal solution** involves a single traversal using a Monotonic Stack, achieving O(N) time complexity by computing both NSE and PSE on the fly.

### Algorithm Breakdown

The single-pass solution utilizes a stack to store the **indices** of the bars, maintaining an increasing height order (to implement the PSE logic while traversing).

#### 1. Traversal and Computation (The Main Loop)
We traverse the array from index $I = 0$ to $N-1$.

*   **Removal Condition:** We check if the current bar height (`array[I]`) is smaller than the height of the bar referenced by the index at the top of the stack (`stack.top()`).
    *   If the current element $I$ is smaller, the element at the top of the stack (`element`) must be popped out because we have found its **Next Smaller Element (NSE)**, which is the current index $I$.
*   **Area Calculation:** When an element is popped out, its rectangle area can be computed:
    *   **Height:** `array[element]`.
    *   **NSE:** The current index $I$.
    *   **PSE:** The index of the new element at the stack's top, or **-1** if the stack is now empty.
    *   Area is calculated: `array[element] * (I - PSE - 1)`.
    *   The `Max Area` variable is updated.
*   **Insertion:** After the removal loop, the current index $I$ is pushed onto the stack.

#### 2. Handling Remaining Elements (Post-Traversal)
Once the main traversal is complete, any elements remaining in the stack mean they never encountered a smaller element to their right.

*   An individual iteration runs while the stack is non-empty.
*   For these remaining elements, the **Next Smaller Element (NSE)** is the size of the array ($N$).
*   The **Previous Smaller Element (PSE)** is found by looking at the element remaining below it in the stack, or **-1** if the stack becomes empty.
*   Area is calculated and `Max Area` is updated.

The final `Max Area` is returned.

## Complexity Analysis

| Metric | Complexity | Rationale |
| :--- | :--- | :--- |
| **Time Complexity** | O(N) | The algorithm involves one main loop (O(N)) and stack operations (push/pop) that are amortized O(N), as each element is pushed and popped at most once throughout the entire process. |
| **Space Complexity** | O(N) | Space is used for the stack data structure, which can hold up to $N$ indices in the worst case. |

---
**Conceptual Analogy:**
Finding the largest rectangle is like trying to build the tallest possible tower for each block on a street. You can only extend the tower laterally until you hit a house (a smaller bar) on either side. The stack helps you efficiently identify those boundary houses (PSE and NSE) for every block in a single walk-through.