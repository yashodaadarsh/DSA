
# L5. Fruit Into Baskets | Two Pointers and Sliding Window Playlist

This document explains the solution for the "Fruit Into Baskets" problem, which requires finding the maximum number of fruits that can be picked under the constraint of using only two baskets, solved using the Two-Pointers and Sliding Window technique.

## Problem Statement

You are visiting a farm where trees produce different types of fruit. You are given an array representing the sequence of fruits produced by the trees.

1.  You are given **two baskets**.
2.  Each basket can hold **only a single unique type** of fruit (e.g., one basket holds only apples (Type 3), and the other holds only oranges (Type 2)).
3.  You must pick fruits consecutively—you **cannot skip** any trees once you start picking,.

The objective is to find the **maximum total number of fruits** you can pick.

This problem simplifies to finding the **maximum length subarray** that contains **at most two types of fruits** (or two unique numbers).

## Approach 1: Brute Force Solution

The extreme naive solution for any subarray problem is to generate all possible subarrays,.

### Mechanism (O(N²))

1.  Use a nested loop structure where the outer loop (`I`) determines the starting point of the subarray, and the inner loop (`J`) determines the end point.
2.  A `Set` data structure is used within the inner loop to track how many unique types of fruits (numbers) are present in the current subarray,.
3.  If the `set.size` is less than or equal to 2, the current segment is valid, and the maximum length is updated using $J - I + 1$.
4.  If the `set.size` exceeds 2, the current subarray is invalid. The inner loop breaks because any further extension will also be invalid,.

### Complexity Analysis

*   **Time Complexity:** O(N²).
*   **Space Complexity:** O(1) or O(3), as the size of the set holding unique fruit types never exceeds three,.

## Approach 2: Optimal Sliding Window (Two Pointers)

Since the Brute Force solution is O(N²), the optimal approach uses the **Sliding Window** technique to achieve linear time complexity. This method uses a Left pointer (**L**) and a Right pointer (**R**) and a **Map** to track the frequency of fruit types within the current window,.

### O(2N) Solution (Standard Sliding Window)

This initial sliding window model is common but can be optimized further.

1.  **Expansion (R moves):** The right pointer (R) moves forward, and the fruit type at `array[R]` is added to the map (increasing its frequency),.
2.  **Validity Check:** The algorithm checks if the `map.size` (unique fruit types) is greater than 2 (or K),.
3.  **Shrinking (L moves):** If the map size is greater than 2, the window is invalid. A **`while` loop** is used to shrink the window from the left to make it valid,.
    *   Inside the `while` loop, the frequency of `array[L]` is decremented.
    *   If the frequency of `array[L]` hits zero, that entry is erased from the map,.
    *   The left pointer `L` is moved forward (`L++`).
4.  **Length Update:** Once the segment is valid (`map.size` $\le 2$), the maximum length is updated using $R - L + 1$.

*   **Time Complexity:** O(2N),. The time is linear because L and R each traverse the array at most $N$ times.

### O(N) Solution (Optimized Sliding Window)

The O(2N) complexity comes from the potentially extensive movement of L driven by the internal `while` loop in the worst-case scenario,. By converting the `while` loop for shrinking into a simple `if` condition, the algorithm is optimized to O(N).

The goal of this optimization is to prevent the window length from increasing when the constraint is violated, rather than forcing the window to become valid instantly,.

### Pseudocode (O(N) Optimized)

The constant constraint check and map operations (add, erase) are considered O(1) because the map size is extremely small (maximum size 3),,.

```
function max_fruits_in_baskets(array):
L = 0         // Left Pointer
max_length = 0
// Map stores fruit type and frequency (K=2, baskets allowed)
map_data_structure

    // R (Right pointer) iterates through the array
    for R from 0 till array.size - 1:
        
        // 1. Expansion: Add array[R] to the map
        map[array[R]]++ 
            
        // 2. Shrinking: If the window is invalid (map size > K, where K=2)
        // Note: Using 'if' instead of 'while' achieves O(N) optimization
        if map.size > 2:
            
            // Decrement the frequency of the element at the left pointer
            map[array[L]]-- 
            
            // If the element's frequency drops to 0, remove it completely
            if map[array[L]] == 0:
                map.erase(array[L]) 
            
            // Move L forward by one step
            L++ 

        // 3. Update Max Length: Length is calculated regardless of whether L moved.
        // If the window was invalid, L moved, ensuring the length doesn't increase beyond the max length.
        // If the window was valid, L didn't move, and the length increases.
        current_length = R - L + 1
        max_length = Max(max_length, current_length)

    return max_length
```

### Complexity Analysis (Optimized)

*   **Time Complexity:** O(N). The Right pointer (R) moves $N$ times, and the Left pointer (L) also moves only $N$ times cumulatively throughout the entire process.
*   **Space Complexity:** O(1), as the map size is always constrained to a maximum of three elements.