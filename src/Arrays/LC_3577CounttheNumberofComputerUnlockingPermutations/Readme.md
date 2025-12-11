# Leetcode 3583: Count Special Triplets


## Problem Description

You are given an integer array `nums`. A special triplet is defined by a triplet of indices (i, j, k) such that the following conditions are met:

1.  **Index Order:** $i < j < k$.
2.  **Value Relationship:** If $nums[j]$ is $x$, then $nums[i]$ must be $2x$ and $nums[k]$ must also be $2x$.
    *   In other words: $nums[i] = nums[j] \times 2$ AND $nums[k] = nums[j] \times 2$.

**Goal:** Return the total number of special triplets in the array.

**Constraint:** Since the answer may be large, return the result modulo $8 \times 10^9 + 7$.

---

## Approach 1: Two-Pass Solution

This approach uses two maps to achieve a linear time complexity solution, O(N).

### Core Idea

Instead of checking the left and right sides of every element $J$ (which would be an O($N^2$) brute-force solution), we maintain frequency counts for elements seen to the left and elements remaining to the right of the current index $J$. We treat the current element as the middle index $J$ (value $x$), and search for the required value $2x$ in the left map (for $I$) and the required value $2x$ in the right map (for $K$).

### Implementation Details

1.  **Initialization (Pass 1):** Initialize two unordered maps, `MP_Left` and `MP_Right`, to store frequencies. Iterate through the entire array once to populate `MP_Right` with the frequencies of all elements, as initially, all elements are on the right side of the starting index.
2.  **Iteration (Pass 2):** Iterate through the array (treating the current element `num` as the potential $J$):
    *   **Update Right Map:** Since the current element `num` is now at index $J$, it must be removed from the right-hand side map, so its frequency in `MP_Right` is decreased.
    *   **Calculate Count:** Search for $2 \times num$ (the required $2x$ value) in both `MP_Left` and `MP_Right`.
        *   Let `Count_L` be the frequency of $2 \times num$ in `MP_Left`.
        *   Let `Count_R` be the frequency of $2 \times num$ in `MP_Right`.
    *   **Update Result:** The total number of triplets formed using the current `num` as $J$ is `Count_L * Count_R`. This multiplication accounts for all possible combinations of the $2x$ elements found on the left and right. Add this product to the total result, ensuring type casting to `long long` and applying the modulo operation.
    *   **Update Left Map:** Before moving to the next index, the current element `num` has now shifted to the left-hand side, so its frequency must be updated/incremented in `MP_Left`.

### Complexity
*   **Time Complexity:** O(N) (specifically O(2N) due to the initialization pass and the main iteration pass, which simplifies to O(N)).
*   **Space Complexity:** O(N) (due to the use of two maps to store element frequencies).

---

## Approach 2: One-Pass Solution

This approach also uses maps but processes the information to find triplets in a single iteration over the array, achieving O(N) complexity.

### Core Idea

We track two things in two separate maps as we iterate:
1.  **`Valid_I`**: Simple frequency counts of elements seen so far (the element is acting as $I$ or just a general number seen on the left).
2.  **`Valid_J`**: The count of valid **pairs** (i, j) that have been formed, indexed by the value of $J$. A valid J element ($x$) must have been preceded by a $2x$ element ($I$).

As we iterate over the current number `num`, we ask two key questions:

### Implementation Steps (for current element `num`)

1.  **Question 1: Can `num` be a Valid K?**
    *   If `num` acts as the K element ($2x$), then the required J element must be $x$ (i.e., `num / 2`).
    *   Check if `num` is even. If it is, look up the frequency of `num / 2` in the `Valid_J` map.
    *   The frequency of `num / 2` in `Valid_J` indicates how many valid (i, j) pairs exist that can complete a triplet with the current K. Add this frequency to the total result.

2.  **Question 2: Can `num` be a Valid J?**
    *   If `num` acts as the J element ($x$), the required I element must be $2x$ (i.e., `num * 2$).
    *   Check the `Valid_I` map (frequency map of past elements) for the frequency of $num \times 2$.
    *   This frequency represents the number of valid (i, j) pairs ending at the current `num`. Add this frequency count to the `Valid_J[num]` entry.

3.  **Update Validity as I:** After addressing the triplet counting and J validity checks, the current `num` has now been seen. Update its frequency in the `Valid_I` map.

### Complexity
*   **Time Complexity:** O(N) (since we iterate over the array only once).
*   **Space Complexity:** O(N) (due to the two maps: `Valid_I` and `Valid_J`).