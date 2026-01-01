# Longest Repeating Character Replacement (L8)

## Problem Description
This problem involves the concepts of the **Two-Pointer and Sliding Window** techniques.

You are given a string composed entirely of uppercase letters and an integer, K. The integer K signifies the maximum number of characters you are allowed to select and convert to any other possible uppercase letter.

After performing at most K conversions, you must determine the **longest substring** that contains all equal characters.

For instance, if you have a substring of length five like `a a b a b` and K=2, you can convert both B's to A's, resulting in a length of 5 where all characters are equal.

## Key Concept: Calculating Conversions
To minimize the required changes (and thus maximize the ability to use the allowed K conversions), you should convert all characters that are *not* the most frequently appearing character within the given substring.

The number of characters (conversions) you need to change in any given substring is calculated by the formula:

$$\text{Conversions Required} = \text{Length of Substring} - \text{Max Frequency}$$

A segment is considered valid only if the number of required changes is less than or equal to K.

## Approaches

### 1. Brute Force (Naive Solution)

The extreme brute force approach is to generate all possible substrings.

1.  Start with every character as the potential starting point (I) of a substring.
2.  Iterate through all possible endpoints (J).
3.  Maintain a hash array (size 26) to track the frequency of uppercase characters within the current substring `[I, J]`.
4.  Track the `Max_frequency` found in that substring.
5.  Calculate the number of changes: `changes = (J - I + 1) - Max_frequency`.
6.  If `changes <= K`, update the `max_length`.
7.  If the condition is violated (`changes > K`), break out of the inner loop (J) and move to the next starting point (I).

**Complexity Analysis (Brute Force):**
*   **Time Complexity:** O(N^2).
*   **Space Complexity:** O(26) (for the hash array).

### 2. Optimized Solution (Sliding Window and Two Pointers)

Since O(N^2) is inefficient, the solution is optimized to O(N) using the two-pointer and sliding window approach.

#### Variables and Setup
Initialize Left pointer (L) and Right pointer (R) to 0. Initialize `max_length`, `Max_frequency`, and a hash map (size 26) to zero.

#### Algorithm Steps (Optimized O(N) Version)
1.  **Expand Window:** While R is less than the string size, move R forward.
2.  **Update Frequencies:** Update the frequency of the character at `s[R]` in the hash map.
3.  **Update Max Frequency:** Update `Max_frequency` to be the maximum of the current `Max_frequency` and the current frequency of the character at R.
4.  **Check Validity and Shrink Window (When necessary):** Check if the window is invalid. The length is `R - L + 1`.
    *   If `(R - L + 1) - Max_frequency > K`, the segment is invalid, and the window must be trimmed from the left.
    *   If trimming is necessary, decrement the frequency of the character at `s[L]` in the hash map. Then, move L forward by one (`L = L + 1`).
    *   ***Optimization Insight:*** When trimming the window, **do not** decrease the value of the overall `Max_frequency`. Since we are looking for the *longest* answer, say length 6, reducing the `Max_frequency` (which contributed to the previous best answer, e.g., length 5) will never help achieve the new longest length. We only need to check if the new R position can create a valid window of size `Max_length + 1`.
5.  **Update Max Length (If Valid):** If the segment remains valid (`(R - L + 1) - Max_frequency <= K`), update `max_length` to be the current window length (`R - L + 1`).
6.  **Continue:** Move R forward (`R++`).
7.  **Return:** Return `max_length`.

**Complexity Analysis (Optimized):**
By avoiding rescanning the 26 hash indices when shrinking the window (the optimization described above), the time complexity improves.

*   **Time Complexity:** O(N).
*   **Space Complexity:** O(26).