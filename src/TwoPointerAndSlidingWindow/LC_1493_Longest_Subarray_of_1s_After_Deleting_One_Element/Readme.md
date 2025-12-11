# Longest Subarray of 1's After Deleting One Element | Leetcode-1493

## Problem Description

Given a binary array (containing only 0s and 1s), you need to **delete exactly one element** from it. The goal is to find the **longest subarray that contains only 1s** in the remaining array.

**Key Constraints/Notes:**
*   You **must** delete one element.
*   If your array contains zeros, you'll generally delete a zero to maximize the length of a subarray of 1s.
*   **Edge Case: Array contains only 1s:** If there are no zeros in the array, you still have to delete one element, which will be a '1'. In this case, the answer will be `(array_length - 1)`.  
    Example: `[1,1,1]` → deleting one `1` results in `[1,1]`, so the length is 2.
*   **Edge Case: Array contains only 0s or no 1s:** The answer would be 0, as no subarray of 1s can be formed.

---

## Approaches Discussed

### 1. Brute Force Approach (O(N^2) Time Complexity)

This approach iterates through each element of the array. If a '0' is encountered, it assumes that '0' is deleted and then calculates the longest subarray of '1's in the modified array.

*   **How it Works:**
    1.  Initialize `result` to store the maximum length found.
    2.  Iterate through the `nums` array using a pointer `i`.
    3.  If `nums[i]` is '0', consider this '0' as the element to be deleted for the current iteration.
    4.  Call a helper function (e.g., `findMaxLenAfterSkipping`) that iterates through the entire array *again*, ignoring the element at `skip_index` (which is `i`).
    5.  Inside the helper function, maintain `current_length` and `max_length` for subarrays of '1's. When a '0' (that is not the `skip_index`) is encountered, or the array ends, update `max_length`.
    6.  Update `result` with the maximum length returned by the helper function.
    7.  **Special Handling for No Zeros:** If the array contains no '0's (meaning `count_of_zeros == 0`), the function directly returns `(array_size - 1)` because one '1' must still be deleted.
*   **Time Complexity:** **O(N^2)** due to the nested loops (one loop for `i`, and another full traversal inside the helper function).

---

### 2. Standard Sliding Window Approach (O(N) Time Complexity)

This is the recommended approach for interviews, built on the general sliding window template. It uses two pointers and keeps track of the number of zeros in the current window.

*   **How it Works:**
    1.  Initialize two pointers, `i` (left) and `j` (right), both starting at 0.
    2.  Maintain `zero_count` to track zeros within the current window `[i, j]`.
    3.  Iterate `j` from 0 to `N-1`:
        *   If `nums[j]` is '0', increment `zero_count`.
        *   **Condition Check:** If `zero_count` becomes greater than 1, shrink the window from the left.
            *   While `zero_count > 1`, if `nums[i] == 0`, decrement `zero_count`. Then increment `i`.
        *   After adjustment, calculate the current valid window length as `(j - i)`.
        *   Update `max_length` with `max(max_length, current_window_length)`.
    4.  The time complexity is **O(N)** because each element is visited at most twice.

---

### 3. Cleaner Sliding Window Approach (O(N) Time Complexity)

This approach is an optimization of the standard sliding window, aiming for cleaner code by directly adjusting the left pointer `i`. It tracks the index of the *last seen zero*.

*   **How it Works:**
    1.  Initialize `i` (left pointer) to 0.
    2.  Initialize `last_zero_index` to -1.
    3.  Initialize `result` to 0.
    4.  Iterate `j` (right pointer) from 0 to `N-1`.
    5.  **If `nums[j]` is '0':**
        *   Set `i = last_zero_index + 1`.
        *   Update `last_zero_index = j`.
    6.  After each iteration of `j`, calculate `(j - i)` and update `result`.
*   **Time Complexity:** **O(N)**, since both `i` and `j` move forward linearly.

---

### 4. Two-Segment Tracking Approach (O(N) Time Complexity)

This approach treats the array as **segments of consecutive 1s separated by zeros**. When we encounter a zero, we check the total length that could be formed by merging the segment of 1s before the zero and the segment of 1s after the zero.

**Java Implementation:**
```java
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int l = 0 , r = 0;
        int size = 0 , prev = 0 ;
        for( r = 0 ; r < n ; r++ ){
            if( nums[r] == 0 ){
                size = Math.max( size , prev + r - l );
                prev = r - l;
                l = r+1;
            }
        }
        if( r - l == n ) return n-1; // all ones case
        return Math.max( size , prev + r - l );
    }
}
