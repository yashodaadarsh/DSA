
# Diagonal Traverse | Leetcode 498 | Similar to Leetcode 1329 | codestorywithMIK

This repository contains the solution and explanation for Leetcode Problem 498, "Diagonal Traverse," as discussed in the YouTube video "Diagonal Traverse | Leetcode 498 | Similar to Leetcode 1329 | codestorywithMIK" by codestorywithMIK.

## Problem Description

The problem requires traversing a given matrix in a **diagonal fashion** and returning all elements in a specific order. The traversal pattern involves alternating directions: initially going upwards-right, then downwards-left, then upwards-right again, and so on.

For example, given a matrix:
```
1 2 3
4 5 6
7 8 9
```
The expected output should follow this diagonal pattern, with alternating directions: ``.

## Key Concepts and Techniques

The solution leverages a few core ideas:

1.  **`i + j` Technique for Diagonals:**
    *   To identify elements belonging to the same diagonal, we use the sum of their row index (`i`) and column index (`j`). Elements with the same `i + j` sum lie on the same diagonal.
    *   For example, in a 3x3 matrix:
        *   `(0,0)` has `i+j = 0`
        *   `(0,1)` and `(1,0)` have `i+j = 1`
        *   `(0,2)`, `(1,1)`, `(2,0)` have `i+j = 2`.

2.  **Using a Map for Storage:**
    *   A `map<int, vector<int>>` is used to store the diagonal elements.
    *   The `i + j` sum serves as the **key** for the map.
    *   The **value** associated with each key is a vector that stores all elements belonging to that specific diagonal.
    *   When iterating through the matrix, elements are added to their corresponding vector in the map.

3.  **Alternating Direction by Reversing Diagonals:**
    *   The crucial part of the problem is the **alternating traversal direction**.
    *   After storing all diagonal elements in the map, we iterate through the map.
    *   For **alternate diagonals** (e.g., the first, third, fifth diagonals), the order of elements needs to be **reversed** to match the desired traversal direction.
    *   For other diagonals (e.g., the second, fourth, sixth), the elements are taken in the order they were stored.

## Solution Steps

The solution involves the following high-level steps:

1.  **Initialize a Map:** Create a `std::map<int, std::vector<int>>` where the key is `i + j` and the value is a vector of integers to store elements of that diagonal.
2.  **Populate the Map:**
    *   Iterate through the matrix using nested loops for `i` (rows) and `j` (columns).
    *   For each element `matrix[i][j]`, calculate its diagonal sum `sum = i + j`.
    *   Add `matrix[i][j]` to the vector associated with `sum` in the map: `map[sum].push_back(matrix[i][j])`.
3.  **Process and Assemble Result:**
    *   Initialize a `std::vector<int>` called `result` to store the final answer.
    *   Iterate through the `map` (which naturally maintains keys in sorted order, i.e., diagonal order).
    *   Maintain a flag or counter to determine if the current diagonal is "even" or "odd" (representing alternate diagonals).
    *   If the current diagonal needs to be reversed (e.g., `(i+j)` is an even sum in terms of map iteration or based on a counter), then **reverse `it.second` (the vector of elements for that diagonal)**.
    *   After potential reversal, append all elements from `it.second` to the `result` vector.
4.  **Return Result:** Return the `result` vector.

`