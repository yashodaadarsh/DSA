# Remove K Digits (L14)

## Introduction
This repository provides the solution and explanation for the "Remove K Digits" problem, which is a key concept within the Stack and Queue playlist. The solution utilizes a greedy approach implemented efficiently using a stack data structure.

## Problem Statement
Given a string `num` containing digits (0 to 9) and an integer `K`, the task is to **remove K digits** from the string such that the remaining string represents the **smallest possible number**.

**Example:** If the resulting smallest number is desired, the intuition is to keep the smaller digits at the start and get rid of the larger ones.

## Intuition and Greedy Approach
The core strategy for finding the smallest possible number is a greedy approach focused on minimizing the initial digits.

*   **Goal:** Keep smaller digits at the start and remove the larger ones.
*   **Mechanism:** Since initial digits heavily influence the magnitude of the number, we prioritize removing larger digits found earlier in the traversal.

## Algorithm Approach (Using Stack)
To track and efficiently remove larger preceding digits, a **stack data structure** is used, leveraging its Last-In, First-Out (LIFO) properties. The algorithm traverses the input string `num` from left to right.

### Core Logic

1.  **Traversal and Comparison:** Iterate through the input string `s` (or `num`).
2.  **Removal Condition (Monotonicity Check):** While the stack is not empty, and the current digit being processed (`s[i]`) is smaller than the digit at the top of the stack (`stack.top()`), and there are still removals left (`K > 0`), the larger element on the stack must be removed:
    *   **Check:** If the digit value of `stack.top()` is greater than the digit value of `s[i]`, remove the element from the stack (`stack.pop()`).
    *   **Update K:** Decrease $K$ by one (`K = K - 1`) because a removal has occurred.
3.  **Insertion:** After the removal loop is completed, push the current digit `s[i]` onto the stack (`stack.push(s[i])`).

### Edge Case Handling

Several edge cases must be addressed to ensure a correct final result:

1.  **Remaining Removals (K > 0):** If, after the entire string traversal, $K$ is still greater than zero (meaning not enough larger elements were found and removed), the remaining digits to remove must be taken off the stack. Since the stack, in this scenario, holds elements in non-decreasing order (e.g., `1 2 3 4 5 6`), the largest elements are at the end (the top of the stack). Therefore, **trim off the last K values** from the stack.

2.  **Empty Result (K = N):** If the initial $K$ is equal to the length of the number ($N$), every digit is removed. In this case, the result should be a string containing a **single zero** ("0").

3.  **Leading Zeros:** After collecting the digits from the stack, if the resulting number starts with leading zeros (e.g., `0010`), these are invalid for the smallest number. The valid number should be `100`. Therefore, ensure the initial zeros are trimmed off.

### Final Processing Steps

1.  **Collect Digits:** Collect all elements from the stack into a resultant string.
2.  **Reverse:** Since elements were pushed and popped from the stack, they are in reverse order (e.g., `9121` instead of `1219`). The resultant string must be **reversed**.
3.  **Trim Leading Zeros:** Start traversing the resultant string from the beginning (which corresponds to the back of the stack) and remove any leading zeros.
4.  **Final Empty Check:** If, after trimming zeros, the result string is empty (which happens if the stack contained only zeros), return a **zero** ("0"). Otherwise, return the reversed and trimmed result.

## Complexity Analysis

| Metric | Complexity | Explanation |
| :--- | :--- | :--- |
| **Time Complexity** | O(N + K) | The initial traversal is O(N). The while loop for removing elements is amortized O(N) because each element is pushed and popped at most once. The subsequent steps (handling remaining K, reversal, and trimming zeros) contribute up to O(N). Overall, the complexity is approximately O(3N + K), which simplifies to **O(N)**. |
| **Space Complexity** | O(N) | Space is required for the stack (O(N)) and the resultant string (O(N)), leading to an overall space complexity of **O(N)**. |