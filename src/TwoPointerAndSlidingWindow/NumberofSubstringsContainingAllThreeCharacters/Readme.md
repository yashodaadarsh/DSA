
# L7. Number of Substrings Containing All Three Characters (A, B, C)

This document summarizes the approach to solving the problem of counting the total number of valid substrings in a given string, where a substring is valid if it contains all three characters: 'a', 'b', and 'c'. The input string is guaranteed only to contain these three characters.

## Problem Statement

Given a string $S$ containing only the characters 'a', 'b', and 'c', find the total count of **consecutive substrings** within $S$ that include at least one instance of 'a', 'b', and 'c'.

**Example:**
If a substring is 'BBA', it is not valid because it lacks 'c'. If the substring is 'BBAC', it is valid because it contains 'a', 'b', and 'c'. For a specific example string provided in the sources, the expected answer is 9.

## Approach 1: Brute Force

The extreme, non-optimal solution involves checking every possible substring.

### Methodology
1.  Use nested loops (`I` and `J`) to iterate through all possible starting and ending points of a substring.
2.  Within the inner loop, track the characters seen using a **hash array of size three** (since only 'a', 'b', and 'c' are present). 'a' maps to index 0, 'b' to 1, and 'c' to 2.
3.  Mark an entry in the hash array as 1 if the corresponding character is encountered.
4.  If the sum of the elements in the hash array (Hash + Hash + Hash) is equal to 3, then all three characters are present, and the substring is valid. Increment the total answer.

### Optimization Insight (Precursor to Optimal Solution)
The brute force can be slightly optimized: the moment a valid substring is found (starting at `I` and ending at `J`), every subsequent substring starting at `I` and extending beyond `J` will also be valid. The number of additional valid substrings generated from that point can be calculated as `(Size of the string - J)`, allowing the inner loop to break.

### Complexity Analysis
*   **Time Complexity:** The Brute Force solution is approximately **$O(N^2)$** due to iterating through all nested substrings.
*   **Space Complexity:** **$O(1)$** because a fixed-size hash array (size three) is used.

## Approach 2: Optimal Solution (Sliding Window / Two Pointers)

The optimal solution aims to reduce the time complexity to $O(N)$ by utilizing a Two Pointers/Sliding Window technique focused on counting. The core idea is to count how many valid substrings *end* at the current character index.

### Methodology
1.  **Tracking Last Seen Indices:** Use an array or structure, `last_seen`, of size three (one slot for 'a', 'b', and 'c'), initialized to -1, to record the index where each character was last encountered.
2.  **Iteration and Update:** Iterate through the string using a single pointer (index `I` or `R`). When a character is encountered, update its corresponding position in the `last_seen` array with the current index.
3.  **Validation and Counting:** When standing at the current index, we must determine the minimal valid window that **ends** at this position. This minimal window is defined by the **leftmost** index among the three characters' last seen positions.
    *   Calculate the minimum index found across all three entries in the `last_seen` array: `min_index = Min(last_seen[a], last_seen[b], last_seen[c])`.
    *   If all three characters have been seen (meaning the `min_index` is not -1), the number of valid substrings ending at the current position is `min_index + 1`.
    *   This logic works because any substring starting from index 0 up to `min_index` (inclusive) and ending at the current position will contain all three required characters.
4.  **Accumulation:** Add the result (`min_index + 1`) to the overall count. If any character has not been seen (`min_index` is -1), then `1 + (-1)` results in 0, correctly adding nothing to the count.

### Pseudo Code Logic

```
function countSubstrings(S):
last_seen = [-1, -1, -1] // Indices for 'a', 'b', 'c'
count = 0

    for I from 0 to N-1:
        // Update the last seen index for the current character
        // (S[I] - 'a' gives 0, 1, or 2)
        last_seen[S[I] - 'a'] = I 
        
        // Find the index of the leftmost required character in the window
        min_index = Min(last_seen, last_seen, last_seen)
        
        // If all three characters are present (min_index >= 0), count valid substrings
        // ending at I. 
        count = count + (min_index + 1)
        
    return count
```

### Complexity Analysis
*   **Time Complexity:** The solution requires a single pass over the string, resulting in a time complexity of **$O(N)$**.
*   **Space Complexity:** **$O(1)$** as only a fixed-size array/variables are used regardless of the input size.