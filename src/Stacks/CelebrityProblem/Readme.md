# The Celebrity Problem

This repository focuses on solving **The Celebrity Problem**, part of the Stack and Queue Playlist.

## Problem Description

The goal is to identify a "celebrity" within a group of $N$ people represented by an $N \times N$ matrix.

The matrix contains values of 0 or 1. If the value at index `(i, j)` is 1, it means **person $i$ knows person $j$**. People are typically numbered starting from 0 (e.g., 0, 1, 2, 3). Note that if person `i` knows person `j`, it does not automatically mean person `j` knows person `i`.

## Definition of a Celebrity

A person is a celebrity if they meet two criteria:
1.  **Known by All:** Everyone else (all $N-1$ people) must know the celebrity,.
2.  **Knows No One:** The celebrity must not know anyone else,.

Since a person cannot know themselves, the entire diagonal of the matrix is zero. There can be at most **one celebrity** or no celebrity at all,.

## Naive (Brute Force) Approach

The naive approach involves using auxiliary storage to count who knows whom.

### Algorithm Steps (Naive)

1.  Initialize two arrays of size $N$, `know me` and `I know`, both initialized to zero,.
2.  **Traverse the Matrix:** Iterate through the $N \times N$ matrix (using indices $I$ and $J$).
3.  **Update Counts:** If `Matrix[I][J]` equals 1:
    *   Increment `know me[J]` (Person $J$ is known by someone).
    *   Increment `I know[I]` (Person $I$ knows someone).
4.  **Find the Celebrity:** Iterate through the people (0 to $N-1$). A person $I$ is the celebrity if:
    *   `know me[I]` is equal to $N-1$ (known by everyone),.
    *   **AND** `I know[I]` is equal to 0 (knows no one),.
5.  If a match is found, return the index $I$. Otherwise, return -1,.

### Naive Complexity

*   **Time Complexity:** $\mathbf{O(N^2 + N)}$. This includes iterating through the $N \times N$ matrix and then iterating $N$ times to check the arrays.
*   **Space Complexity:** $\mathbf{O(2N)}$, due to storing the `know me` and `I know` arrays.

## Optimal Approach (Elimination using Pointers)

The optimal solution avoids $O(N^2)$ traversal by recognizing that at most one celebrity exists, allowing for efficient elimination,.

### Algorithm Steps (Optimal)

1.  **Initialization:** Set a `top` pointer to 0 and a `down` (or `bottom`) pointer to $N-1$,.
2.  **Elimination Loop:** While `top` is less than `down`:
    *   **Check 1: Does `top` know `down`?** Check `Matrix[top][down]`. If the value is 1, `top` cannot be the celebrity (because a celebrity knows no one). Therefore, eliminate `top` by incrementing the `top` pointer.
    *   **Check 2: Does `down` know `top`?** If Check 1 failed (i.e., `top` does not know `down`), check if `down` knows `top` (`Matrix[down][top] == 1`). If `down` knows `top`, `down` cannot be the celebrity, so eliminate `down` by decrementing the `down` pointer,.
3.  **Candidate Selection:** When the loop ends, `top` will equal `down`, identifying a single potential celebrity candidate,.
4.  **Final Verification:** Traverse the matrix to confirm the candidate satisfies the two criteria (known by all, knows no one):
    *   The candidate's entire row must be zero (knows no one),.
    *   The candidate's entire column must be one (known by all), excluding the diagonal element,.
5.  If verified, return the candidate; otherwise, return -1.

### Optimal Complexity

*   **Time Complexity:** $\mathbf{O(2N)}$ (or $\mathbf{O(N)}$). The elimination process performs $N$ comparisons, and the final verification involves a maximum of $N$ checks.
*   **Space Complexity:** $\mathbf{O(1)}$, as no external arrays or stacks are used, only a few pointers.