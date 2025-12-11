# Online Stock Span Problem

This repository addresses the **Online Stock Span Problem**, which is part of the Stack and Queue Playlist.

## Problem Description

The goal is to implement a mechanism, typically encapsulated in a `StockSpanner` class, to track stock prices and calculate the "stock span" on demand,.

The process begins with an initial `stock spanner` call, marking the start of the stock market. Subsequently, the `next` function is called repeatedly, receiving an integer representing the current day's stock price.

The task is to return an integer that defines the **maximum consecutive days** for which the stock price was **less than or equal to the current day's price**.

## Example Walkthrough

Consider the following sequence of stock prices received by the `next` function:

| Price | Current Index | Logic | Span Returned |
| :---: | :---: | :---: | :---: |
| 7 | Day 1 (Index 0) | No previous days to compare; returns 1. | 1 |
| 2 | Day 2 (Index 1) | The previous price (7) is not $\le$ 2; only the current day counts. | 1 |
| 1 | Day 3 (Index 2) | The previous price (2) is not $\le$ 1; only the current day counts. | 1 |
| 3 | Day 4 (Index 3) | Consecutive days 3, 1, 2 are all $\le 3$. | 3 |
| 3 | Day 5 (Index 4) | Consecutive days (current 3, previous 3, 1, 2) are all $\le 3$. | 4 |
| 8 | Day 7 (Index 6) | Every previous price is less than or equal to 8. | 7 |

## Brute Force Approach

The simplest approach (Brute Force) involves maintaining a **dynamic list or array** (defined globally under the `StockSpanner` class) that stores all previous stock prices.

When the `next(value)` function is called:
1. The new `value` is inserted into the dynamic list,.
2. A counter is initialized to 1 (counting the current day, as it is always $\le$ itself).
3. Iteration starts from the index immediately preceding the current day and continues backwards toward the beginning (index 0),.
4. If a previous day's value is less than or equal to the current `value`, the counter is incremented.
5. If a previous day's value is greater than the current `value`, the counting stops, and the loop breaks.
6. The final count is returned.

### Brute Force Complexity

*   **Time Complexity:** For every `next` function call, the complexity is $O(\text{Number of days till now})$ in the worst possible case, as the solution might iterate through the entire history.
*   **Space Complexity:** $O(\text{Total number of next calls till now})$, as all stock prices must be stored.

## Optimal Approach (Optimization using Stack)

The Brute Force solution is inefficient because it requires repeated backward traversal. The optimization relies on recognizing that the required span is determined by the distance to the **Previous Greater Element (PGE)**,.

The span can be calculated as: **Current Index - Index of the Previous Greater Element**.

To efficiently find the PGE, a **Stack data structure** is used. The stack stores pairs of `(Value, Index)`.

### Optimized Algorithm Steps

1.  Initialize a global **stack** (storing `<Value, Index>`) and an index tracker (reinitialized to -1 when `StockSpanner` is called),.
2.  In the `int next(value)` function:
    a. Increment the global index (`index = index + 1`).
    b. **Process Stack:** While the stack is not empty and the value at the top of the stack is **less than or equal to** the current `value`, pop that pair. This is crucial because smaller elements can never be the PGE for elements appearing later.
    c. **Calculate Answer:**
    *   If the stack is now empty, it means the current element is the greatest so far. The PGE index is considered -1,. The span is calculated as `Current Index - (-1)`,.
    *   If the stack is not empty, the PGE is at the top. The span is calculated as `Current Index - Stack.top().index`.
    d. **Push Current Element:** Push the pair `(value, index)` onto the stack.
    e. Return the calculated span.

### Optimal Complexity

By using the stack to efficiently track the PGE, the traversal backward is avoided, leading to a much better overall performance.

*   **Overall Time Complexity:** For $N$ total `next` calls, the time complexity is **$O(2N)$** (or $O(N)$), because each element is pushed onto and popped from the stack at most once.
*   **Overall Space Complexity:** $O(N)$, as the stack may store up to $N$ elements in the worst case (e.g., when the prices are strictly decreasing).

This optimal approach is analogous to having a librarian (the stack) who only keeps track of the biggest books on the shelf (the PGEs). When a new book arrives, all books that are smaller or the same height as the new one are removed because they are now hidden by the new book and will never be the tallest reference point again. The span is the distance back to the last remaining taller book (the true PGE).