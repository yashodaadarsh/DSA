
# L3. Longest Substring Without Repeating Characters | Two Pointers and Sliding Window Playlist

This document explains the solution for finding the longest substring within a given string that does not contain any repeating characters, utilizing the Two-Pointers and Sliding Window technique.

## Problem Statement

Given an input string that may contain any of the 256 possible characters (lowercase, uppercase, special characters, etc.), the task is to determine the **length** of the longest **substring** (a consecutive portion of the string) in which every character appears only once.

**Key Definition:** A substring must be consecutive in nature.

**Example:** For the string "c a d b z a...", the substring "c a d b z" has a length of 5 and contains no repeating characters, whereas "c a d b z a" is invalid because 'a' appears twice.

## Approach 1: Brute Force (Inefficient)

The most straightforward, but non-optimal, approach is to generate all possible substrings and then check if each one contains repeating characters.

1.  **Generation:** Use nested loops. The outer loop (`I`) determines the start of the substring, and the inner loop (`J`) extends the substring one character at a time.
2.  **Validation:** Within the inner loop, use a hashing technique (like a hash array or map) to track characters seen so far within the current substring defined by the range $I$ to $J$.
3.  **Optimization:** If a repeating character is encountered during the inner loop (at index $J$), the loop must be broken immediately because any further extension will still contain that repeat.
4.  **Complexity:** This approach explores $O(N^2)$ substrings, resulting in a time complexity of **O(N²)**. The space complexity is $O(256)$ for the hash array used for tracking.

## Approach 2: Optimal Sliding Window (Two Pointers)

Since this problem involves finding the maximum length of a substring based on a condition, the optimal solution utilizes the **Two-Pointer/Sliding Window** technique to achieve linear time complexity. This technique is constructive, adapting based on the specific problem constraints.

### Algorithm Mechanism

The approach uses two pointers, **L (left)** and **R (right)**, to define the current window $[L, R]$.

1.  **Data Structure:** Instead of a simple boolean hash, an array of size 256 (covering all possible ASCII characters) is used. This array, initialized to $-1$, stores the **last index** where a character was seen. Using an array ensures $O(1)$ access time, which is faster than map lookups.
2.  **Expansion (R Movement):** The right pointer (R) moves forward iteratively (`R++`) to expand the current window and check if the character `s[R]` can be included.
3.  **Condition Check:** When R moves, the algorithm checks the hash array for the current character `s[R]`.
    *   If the character `s[R]` was previously seen, the algorithm needs to check if that previous occurrence is **within the current active window** (i.e., if the stored index of $s[R]$ is greater than or equal to $L$).
4.  **Shrinking (L Movement):** If the character `s[R]` is found to be repeating within the current window, the left pointer (L) must move forward to eliminate the previous occurrence.
    *   $L$ is updated to one position *after* the previous occurrence of $s[R]$: $L = hash[s[R]] + 1$.
5.  **Length and Recording:**
    *   After adjusting $L$ (if necessary), the current valid length is calculated: $R - L + 1$.
    *   The `maxLength` variable is updated with the maximum length found so far.
    *   The hash array is updated to record the current index $R$ as the last known position of character $s[R]$.

The process continues until R reaches the end of the string.

### Pseudocode

```
function longestSubstringWithoutRepeating(s):
N = length(s)

    // Hash array (size 256) initialized to -1 to store the last seen index of each char
    hash_array = Array(256, initialized to -1) 
    
    L = 0 // Left pointer
    max_length = 0
    
    // R (Right pointer) controls the main loop expansion
    for R from 0 till N - 1:
        
        current_char = s[R]
        
        // 1. Check for repetition within the current window [L, R]
        // If hash[current_char] is -1, it hasn't been seen, so it proceeds.
        // If hash[current_char] >= L, it's a repeat within the window.
        if hash_array[current_char] >= L:
            
            // If repeating, update L to one position past the previous occurrence
            L = hash_array[current_char] + 1
        
        // 2. Calculate and update maximum length
        current_length = R - L + 1
        max_length = Max(max_length, current_length)
        
        // 3. Record the current index (R) as the last seen index for the character
        hash_array[current_char] = R
        
    return max_length
```

## Complexity Analysis

This approach provides optimal performance for this class of problem:

*   **Time Complexity:** **O(N)**. Since both $L$ and $R$ traverse the array at most $N$ times, and the core operations within the loop (hash lookups, max updates) are $O(1)$, the complexity is linear.
*   **Space Complexity:** **O(256)**. This is constant space complexity, as the size of the auxiliary hash array is fixed regardless of the input string length (it depends only on the number of possible characters).