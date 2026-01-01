
### 1. Constant Window Problems
This pattern involves problems where the size of the window (K) is constant.

*   **Example:** Given an array and an integer K (e.g., K=4), find the maximum sum obtainable by picking K consecutive elements.
*   **Mechanism/Template:** You use two pointers, **L (left)** and **R (right)**.
    1.  First, compute the sum of the initial constant window (from index 0 to K-1).
    2.  To move to the next window, the window shifts. You must subtract the element at L from the current sum, increment L (`L++`), increment R (`R++`), and then add the new element at R to the sum.
    3.  This continues until R reaches the end of the array boundary. The current sum is continuously compared against a `Max sum` variable.

### 2. Longest Subarray/Substring with a Condition (Most Common Pattern)
This is the most frequent type of problem encountered in this topic, where the task is to find the **longest** subarray or substring that satisfies a certain condition (e.g., the sum is less than or equal to K).

The video presents three approaches for this pattern:

*   **Brute Force (O(N²)):** The brute force method involves generating all possible subarrays and checking them against the given condition. This requires two nested loops (one starting at index `i` and one starting at `j` from `i`) to generate all consecutive segments. An optimization is possible by breaking the inner loop (`j`) if the sum exceeds the limit K.

*   **Better Solution (Sliding Window, O(2N)):** This uses the standard sliding window approach with L and R pointers, starting with L=0, R=0. The window mechanism involves two key concepts:
    1.  **Expansion:** The right pointer (R) moves forward, adding the new element to the sum. This is done to increase the window size and find the longest possible result.
    2.  **Shrinking:** If the window becomes **invalid** (violates the condition, e.g., sum > K), the left pointer (L) moves forward, subtracting elements from the sum, using a `while` loop. The shrinking continues until the window becomes valid again.
    3.  If the window is valid, the `max length` variable is updated using the current window size (`R - L + 1`).
    *   The time complexity is O(2N) because both L and R move at most N times across the array.

*   **Optimal Solution (Sliding Window, O(N)):** This optimization is achieved primarily when the question asks only for the *length* of the longest subarray, not the subarray itself. Instead of using a `while` loop for shrinking when the condition is violated, the `while` loop is converted to an **`if`** condition. This limits the shrinking to just one step (L++) when the condition is violated, ensuring that the time complexity is reduced to O(N) by preventing the nested operation of the extensive shrinking loop.

### 3. Number of Subarrays with a Condition
This pattern asks for the **count** of subarrays that meet a specific constraint.

*   **Mechanism:** These problems are often challenging to solve directly using standard expansion and shrinking because it is difficult to determine when to expand or shrink. Instead, the typical approach breaks the "sum equals K" problem into two problems solvable using the Pattern 2 template:
    1.  Find the number of subarrays where the sum is **lesser than or equal to K**.
    2.  Find the number of subarrays where the sum is **lesser than or equal to K - 1**.
    3.  The final answer is the subtraction of the second result from the first.

### 4. Shortest/Minimum Window or Length
This is described as a rare pattern requiring finding the **minimum** window that satisfies a condition.

*   **Mechanism:** You move the R pointer, expanding the window until a **valid window** is achieved. Once a valid window is found, you immediately attempt to **shrink** the window (by moving L) to determine the smallest possible valid length, and this minimum valid length is stored as the answer.