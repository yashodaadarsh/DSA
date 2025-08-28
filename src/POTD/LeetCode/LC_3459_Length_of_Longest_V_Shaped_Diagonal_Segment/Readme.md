
# LeetCode 3459: Length of Longest V-Shaped Diagonal Segment | Made Easy


## Problem Description

You are given a 2D integer matrix `grid` of size `n x m`, where each element is either `0`, `1`, or `2`. The goal is to find the **length of the longest V-shaped diagonal segment**.

### V-Shaped Diagonal Segment Definition:

*   **Starts with `1`**: Every segment must begin at a cell containing the value `1`.
*   **Sequence**: After the initial `1`, subsequent elements must follow an infinite alternating sequence of `2, 0, 2, 0, ...`.
*   **Initial Movement**: The segment starts along one of four diagonal directions:
    *   Top-left to Bottom-right (`i+1, j+1`)
    *   Bottom-right to Top-left (`i-1, j-1`)
    *   Top-right to Bottom-left (`i+1, j-1`)
    *   Bottom-left to Top-right (`i-1, j+1`)
*   **Direction Changes**:
    *   The segment **continues the sequence in the same diagonal direction** initially chosen.
    *   **At most one 90-degree clockwise turn** to another diagonal direction is allowed. Once a turn is made, no further turns are permitted.
    *   The sequence (`2, 0, 2, 0...`) must always be maintained, even after a turn.

If no valid segment exists, return `0`.

## Solution Approach

The problem can be efficiently solved using a **Depth-First Search (DFS) / Recursion with Memoization** approach.

### High-Level Steps:

1.  **Iterate Starting Points**: Traverse the entire `n x m` `grid`. Whenever a cell with value `1` is encountered (`grid[i][j] == 1`), consider it as a potential starting point for a V-shaped segment.
2.  **Explore Initial Directions**: From each starting cell (`i, j`), there are four possible diagonal directions to begin the segment. For each direction, initiate a recursive call.
3.  **Recursive `solve` Function**: A recursive function (e.g., `solve(i, j, d, canTurn, next_val, grid)`) is used to explore paths:
    *   `i, j`: Current row and column coordinates.
    *   `d`: An integer representing the current diagonal direction (index from 0 to 3, corresponding to pre-defined delta `(dr, dc)` pairs). This allows determining the next cell in the *current* diagonal path.
    *   `canTurn`: A boolean flag indicating whether the segment is still allowed to make its one-time 90-degree clockwise turn. It starts as `true` and becomes `false` after a turn.
    *   `next_val`: The value (`2` or `0`) that is expected in the *next* cell according to the sequence.
    *   `grid`: The input matrix.

### `solve` Function Logic:

At each step in the `solve` function, from the current cell `(i, j)` and current direction `d`, two options are considered for the next move, provided they are valid (within bounds and match `next_val`):

1.  **Keep Moving Straight**: Continue in the same diagonal direction `d`.
    *   The next cell `(i', j')` is calculated using `(i + directions[d], j + directions[d])`.
    *   `d` remains the same.
    *   `canTurn` remains the same.
    *   `next_val` alternates (`2` becomes `0`, `0` becomes `2`).
    *   Recursively call `solve` with these new parameters and add `1` (for the current cell) to the returned length.

2.  **Make a 90-Degree Clockwise Turn**: This option is only available if `canTurn` is `true`.
    *   The new direction `d'` is calculated as `(d + 1) % 4`, effectively moving to the next diagonal in a clockwise order. The `directions` array is designed such that `directions[d+1]` represents a 90-degree clockwise turn from `directions[d]`.
    *   `canTurn` becomes `false` for all subsequent recursive calls from this path.
    *   `next_val` alternates (`2` becomes `0`, `0` becomes `2`).
    *   Recursively call `solve` with the new direction `d'` and `canTurn` as `false`, adding `1` to the returned length.

The `solve` function returns the maximum length obtained from these two options (if applicable). Base cases include returning `0` if the next cell is out of bounds or its value does not match `next_val`.

### `directions` Array:

A global array, `directions`, is used to define the delta `(dr, dc)` for each of the four diagonal movements and their 90-degree clockwise transitions.
Example `directions` (as inferred from the video):
```
[
,   // d=0: (i+1, j+1) - e.g., Top-Left to Bottom-Right
[1, -1],  // d=1: (i+1, j-1) - e.g., Top-Right to Bottom-Left (90-deg clockwise from d=0)
[-1, -1], // d=2: (i-1, j-1) - e.g., Bottom-Right to Top-Left (90-deg clockwise from d=1)
[-1, 1]   // d=3: (i-1, j+1) - e.g., Bottom-Left to Top-Right (90-deg clockwise from d=2)
]
```
The modulo `4` ensures circular transition for `d` (e.g., from `d=3` to `d=0`).

## Memoization

To prevent redundant computations due to overlapping subproblems in the recursion, **memoization** is crucial.

*   **DP State**: The state for memoization is defined by `(i, j, d, canTurn)`.
    *   `i, j`: Current cell coordinates.
    *   `d`: Current diagonal direction index (0-3).
    *   `canTurn`: Boolean (represented as 0 or 1) indicating if a turn is still allowed.
    *   The `next_val` parameter is implicitly handled because the `2,0,2,0` sequence is fixed and alternating; it does not introduce new unique states beyond the other four parameters.
*   **Memoization Table**: A 4D array, `dp[N][M]`, is used to store results of `solve` calls. Initialize it with `-1` (or a similar sentinel value) to indicate unvisited states.
*   Before computing a `solve` call, check if `dp[i][j][d][canTurn]` already has a value other than `-1`. If so, return the stored value. Otherwise, compute the result, store it in the `dp` table, and then return it.

## Complexity Analysis

*   **Time Complexity**: **O(M * N)**
    *   `M * N` represents the total number of cells in the grid.
    *   Each cell `(i, j)` can be visited with different combinations of `d` (4 options) and `canTurn` (2 options: `true`/`false`).
    *   With memoization, each unique state `(i, j, d, canTurn)` is computed at most once.
    *   Therefore, the total number of states is `M * N * 4 * 2`, which simplifies to `O(M * N)`.
*   **Space Complexity**: **O(M * N)**
    *   Primarily for the memoization table `dp[M][N]`.
    *   The recursion stack depth would also be proportional to the length of the longest path, which in the worst case could be `O(M*N)`.
```