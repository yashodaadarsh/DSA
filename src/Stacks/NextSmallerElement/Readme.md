# L7. Next Smaller Element (Nearest Smaller Element on the Right) | Stack and Queue

This document summarizes the approach for solving the **Nearest Smaller Element (NSE) on the Right** problem, also known as the Next Smaller Element, utilizing the **Monotonic Stack** technique, as detailed in the video "L7. Next Smaller Element | Stack and Queue Playlist."

## 1. Problem Statement

The goal is to determine the **Next Smaller Element (NSE)** for every integer in a given input array.

The NSE of an element $X$ is defined as the **first** element strictly smaller than $X$ found when looking to the **right** of $X$.

If no smaller element exists on the right (e.g., at the end of the array or if all subsequent elements are greater), the result for that element is **-1**.

*Example:* If the array is `[4, 8, 5, 2, 25]`,
- NSE of 4 → 2
- NSE of 8 → 5
- NSE of 5 → 2
- NSE of 2 → -1
- NSE of 25 → -1

## 2. Brute Force Solution

The basic solution involves iterating through the array and using a nested loop to check all following elements:

1.  Iterate over every element $I$ from 0 to $n-1$.
2.  For each element $I$, start a second loop forward from $J = I + 1$ to $n-1$.
3.  If `array[J]` is found to be strictly **smaller than** `array[I]`, set the result `NSE[I] = array[J]` and break.

### Brute Force Complexity

*   **Time Complexity:** **$O(N^2)$**.
*   **Space Complexity:** **$O(N)$** is used to store the answer array (it is recommended not to modify the input array).

The interviewer will require optimization away from $O(N^2)$.

## 3. Optimized Approach: Monotonic Stack (O(N) Solution)

Since we are looking for the nearest smaller element on the **right**, the array iteration must start from the **rightmost end** (reverse direction: $n-1$ to 0).  
A Stack data structure is used to maintain relevant upcoming elements efficiently.

### A. Monotonic Stack Type

We need to maintain a stack in an **increasing order** (from bottom to top) because we are searching for the nearest *smaller* element.

### B. Implementation Logic (Pseudo Code)

The optimized solution uses a reverse traversal and stack manipulation:

1.  Initialize a result array `NSE` (size $N$) and a stack `St`.
2.  **Traverse Backward:** Loop `I` from $n-1$ down to 0.

3.  **Stack Maintenance (Pop Greater/Equal Elements):**  
    While the stack is not empty **AND** the element at the stack's top (`stack.top()`) is **greater than or equal to** the current element (`array[I]`), continuously **pop** elements.  
    These elements are irrelevant because they are greater than or equal to the current element and thus cannot be the NSE for any earlier elements.

4.  **Determine NSE Result:**
    *   If the stack is empty after popping, it means there is no smaller element on the right: **`NSE[I] = -1`**.
    *   Otherwise, the element now at the top of the stack is the Next Smaller Element: **`NSE[I] = stack.top()`**.

5.  **Push Current Element:** Push the current element (`array[I]`) onto the stack to potentially serve as the NSE for earlier elements.

6.  Return the `NSE` list.

*Note:* For the last element in the array, the NSE is always -1 because there are no elements on the right.

### C. Complexity Analysis

*   **Time Complexity:** **$O(N)$**.  
    Each element is pushed onto the stack exactly once and popped at most once.
*   **Space Complexity:** **$O(N)$** for the stack plus $O(N)$ for the result array.

---

## 4. Java Implementation

```java
package Stacks.NextSmallerElement;
import java.util.*;

class NextSmallerElement {
    static ArrayList<Integer> nextSmallerEle(int[] arr) {
        int[] smaller = findNextSmaller(arr);
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : smaller) {
            list.add(num);
        }
        return list;
    }

    private static int[] findNextSmaller(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() >= nums[i]) {
                st.pop();
            }
            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(nums[i]);
        }
        return res;
    }
}
