
# LeetCode: Implement Min Stack (L4)

This video addresses the problem of designing a custom stack, referred to as a **Minimum Stack**, which must support standard stack operations (`push`, `pop`, `top`, `size`) and an additional **`getMin`** operation. The goal is to achieve $O(1)$ time complexity for all operations.

## Problem Challenge

In a standard stack implementation, scanning the entire stack to find the minimum element results in an unacceptable time complexity of **$O(N)$**. Therefore, a custom design is required to maintain the minimum element history while adhering to $O(1)$ time complexity for `getMin`.

## Approach 1: Storing Pairs (Initial Solution)

The first proposed solution modifies the stack to store two elements for every push operation, achieving $O(1)$ time complexity with $O(2N)$ space complexity.

### Implementation Details:

1.  **Data Structure:** The stack stores a **pair of integers** (`integer, integer`).
    *   The **first integer** stores the actual `Val` being pushed.
    *   The **second integer** stores the **minimum seen till now** in the stack.

2.  **`Push` Operation:**
    *   If the stack is empty, both the stored value and the minimum are set to the input `Val`.
    *   If not empty, the value pushed is `Val`, and the minimum stored is the lesser of the current `Val` and the previous minimum (which is found at `stack.top().second`).

3.  **`GetMin` Operation:**
    *   The minimum value is retrieved directly from the top element's second component (`stack.top().second`).

### Complexity (Approach 1):

*   **Time Complexity:** $O(1)$ for all operations.
*   **Space Complexity:** $O(2N)$.

## Approach 2: Optimized Space (The Final Solution)

To reduce space complexity from $O(2N)$ to **$O(N)$**, the approach is optimized by storing only a single element in the stack but using a global variable (`minimum`) to track the current minimum.

The core challenge in this approach is retrieving the *previous minimum* when the current minimum value is popped. This is solved by inserting a mathematically **modified value** into the stack only when the incoming value (`Val`) is smaller than the current `minimum`.

### Data Structures:

1.  A standard stack (storing single integers).
2.  A global variable `minimum` (initialized to a large value like `INT_MAX` or `LONG_MAX`).

### Implementation Details:

#### 1. `Push(Val)`

*   **Case 1: `Val` is greater than or equal to `minimum`:** The minimum doesn't change. Simply push `Val` onto the stack.
*   **Case 2: `Val` is less than `minimum`:** The minimum is modified.
    *   Instead of pushing `Val`, push a **modified value**.
    *   **Modified Value Formula:** $2 \times \text{Val} - \text{Current Minimum}$ (The current minimum here refers to the previous minimum before the update).
    *   Update the global `minimum` variable to the new value: $\text{minimum} = \text{Val}$.

#### 2. `Pop()`

When popping, the element must be checked to see if it was a modified value:

*   Perform `stack.pop()` and store the popped element as `X`.
*   **Check Condition:** If $X < \text{minimum}$, it indicates that $X$ was a modified value.
    *   **Restore Previous Minimum:** Calculate the previous minimum using the formula derived from the modification step: **Previous Min $= 2 \times \text{Current Minimum} - \text{X}$**.
    *   Update the global `minimum` to this restored value.

#### 3. `Top()`

The true top element needs to be determined based on whether the stored value was modified:

*   Retrieve the top element `X = stack.top()`.
*   If $X$ is greater than the current `minimum`, return $X$ (the value was unmodified).
*   If $X$ is smaller than the current `minimum` (meaning it's a modified value), the current `minimum` variable holds the actual original value that should be considered the top. **Return `minimum`**.

#### 4. `GetMin()`

*   Simply return the global `minimum` variable.

### Complexity (Approach 2):

*   **Time Complexity:** **$O(1)$** for all operations.
*   **Space Complexity:** **$O(N)$** (storing one element per operation). This is the optimized approach.
```