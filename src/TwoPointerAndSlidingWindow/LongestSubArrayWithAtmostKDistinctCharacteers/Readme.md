
# L6. Longest Substring With At Most K Distinct Characters

This problem involves finding the longest substring within a given string that contains **at most K distinct characters**. This concept is commonly solved using the Two Pointers and Sliding Window technique.

## Problem Statement

Given a string (S) and an integer (K), the task is to find the **longest substring** (any consecutive portion of the string) such that the number of distinct characters within that substring is **less than or equal to K**.

For example, if K=2, and the substring is 'AABBC', it has three distinct characters (A, B, C), and is therefore not valid.

## Approach 1: Brute Force (Exploring All Substrings)

The extreme approach is to explore all possible substrings.

### Methodology
1. Iterate through the string, considering every character as a potential starting point for a substring.
2. For each starting character (index `I`), iterate outwards (index `J`) to generate all substrings starting at `I`.
3. Use a **map data structure** (hashmap) to track the distinct characters encountered and their frequencies within the current substring.
4. If the size of the map (the count of distinct characters) is less than or equal to K, the substring is valid, and the `max_length` is updated to `J - I + 1`.
5. If the map size exceeds K, stop extending the current substring (break the inner loop), as adding more characters will only make it less valid.

### Brute Force Pseudo Code

```
function longestSubstringBruteForce(S, K):
max_length = 0

    // Outer loop iterates through starting positions (I)
    for I from 0 to N-1:
        // Clear map for new set of substrings starting at I
        Map = {} 
        
        // Inner loop iterates through ending positions (J)
        for J from I to N-1:
            // Add character S[J] to map and increase frequency
            Map[S[J]] += 1
            
            // Check if valid
            if Map.size <= K:
                // Update max_length
                max_length = Max(max_length, J - I + 1)
            else:
                // If distinct characters exceed K, break and move I
                break
                
    return max_length
```

### Complexity Analysis
*   **Time Complexity:** The Brute Force solution is typically $O(N^2)$ because of the nested loops exploring all substrings. Map operations (insertion/lookup) may add a factor, such as $\log(256)$ if using balanced maps for up to 256 possible characters, but $O(N^2)$ dominates the time complexity.
*   **Space Complexity:** $O(256)$ in the worst-case scenario, as the map stores characters (e.g., all 256 English alphabets).

---

## Approach 2: Optimized Solution (Two Pointers and Sliding Window)

Since the problem involves finding the longest substring, the interviewer will typically expect an optimization from $O(N^2)$ towards **$O(N)$ time complexity**. This is achieved using the **Two Pointers and Sliding Window technique**.

### Methodology
This approach uses two pointers, `L` (Left) and `R` (Right), defining a window, and a hashmap to track frequencies of distinct characters within that window.

1. **Initialization:** Set `L=0`, `R=0`, and `max_length=0`.
2. **Expansion:** Iterate using the Right pointer (`R`) to expand the window, adding the character `S[R]` to the hashmap and increasing its frequency.
3. **Contraction (Validation Check):** After expansion, check the size of the hashmap. If the number of distinct characters (`Map.size`) is **greater than K**, the window is invalid, and must be contracted.
    *   Use an inner `while` loop to contract the window from the left until it becomes valid.
    *   To contract, decrement the frequency of the character at `S[L]` in the map.
    *   **Crucially:** If the frequency of `S[L]` drops to zero, the character must be **removed entirely from the map**.
    *   Increment the Left pointer (`L = L + 1`).
4. **Update Max Length:** Once the window is valid (`Map.size <= K`), update the maximum length: `max_length = Max(max_length, R - L + 1)`.
5. **Continue:** Increment `R` (`R = R + 1`) to continue iterating.

### Sliding Window Pseudo Code (O(2N) Structure)

This structure is robust and results in $O(2N)$ time, which is generally acceptable.

```
function longestSubstringSlidingWindow(S, K):
max_length = 0
L = 0
R = 0
Map = {} // Stores character and integer frequency

    // R iterates through the string size
    while R < S.size:
        
        // 1. Expansion: Put S[R] into the map
        Map[S[R]] += 1 
        
        // 2. Contraction: If map size exceeds K, shrink the window from the left (L)
        while Map.size > K:
            // Remove character S[L] from the map count
            Map[S[L]] -= 1
            
            // If the count of S[L] reaches zero, remove the key from the map
            if Map[S[L]] == 0:
                erase Map[S[L]] // Removal from map data structure
            
            // Move the left pointer
            L = L + 1
            
        // 3. Update Max Length (After achieving a valid segment)
        // Check condition map.size <= K is implicitly met by the while loop above
        max_length = Max(max_length, R - L + 1)
        
        // 4. Move R for the next iteration
        R = R + 1
        
    return max_length
```

### Complexity Analysis
*   **Time Complexity:** The Right pointer (`R`) iterates $N$ times. The Left pointer (`L`) only moves forward and iterates at most $N$ times in total throughout the entire process. This results in a time complexity of **$O(2N)$**, which simplifies to **$O(N)$**.
*   **Space Complexity:** $O(256)$ (or $O(1)$ constant space relative to the input size, assuming the alphabet size is constant).

***
*Note on Optimization:* Some interviewers may ask to eliminate the "extra N" (to optimize $O(2N)$ down to a tight $O(N)$), which involves modifying the contraction step (the inner `while` loop) to avoid trimming the window down more than necessary once a new maximum length is found. This optimization generally requires converting the inner `while` loop into an `if` statement to handle only the single required removal, but the underlying complexity remains $O(N)$.
***
