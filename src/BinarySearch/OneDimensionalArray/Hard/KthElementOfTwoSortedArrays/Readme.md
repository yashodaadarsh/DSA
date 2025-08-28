
# K-th Element of Two Sorted Arrays | Binary Search Approach


##  Core Concept & Prerequisite

**This problem is an extension of the "Median of Two Sorted Arrays" problem**. It is highly recommended to understand the core concept used in solving the "Median of Two Sorted Arrays" problem before attempting this one, as the solution relies heavily on similar principles.

##  Problem Statement

You will be given two sorted arrays, `array1` and `array2`, and an integer `K`.
The task is to **find the K-th smallest element that would result if these two arrays were hypothetically merged into a single sorted array**.

**Example:**
If `array1 = [2, 3, 6, 7, 9]` and `array2 = [1, 4, 8, 10],`, and `K = 5`:
1.  Hypothetically merging them would result in ` [1, 2, 3, 4, 6, 7, 8, 9, 10]`.
2.  The 5th element in this merged array is `6`.
Your function should return `6`.

**Note:** You do not actually need to merge the arrays. The goal is to return just the K-th element.

##  Approach: Binary Search

The solution utilizes a **binary search approach**, similar to the "Median of Two Sorted Arrays" problem.

1.  **Splitting Strategy:**
    *   In the median problem, we aimed for an `n/2` split (or `n/2` and `n/2 + 1` for even elements, or `(n+1)/2` for odd).
    *   For the K-th element problem, the goal is to form **`K` elements on the left side** and the remaining `N - K` elements on the right side, where `N` is the total number of elements in both arrays.
    *   We determine how many elements (`cut1`) to take from `array1` and how many (`cut2`) to take from `array2` such that `cut1 + cut2 = K`.
    * cut1 = mid1 and cut2 = mid2 in solution.

2.  **Identifying the K-th Element:**
    *   Once the left half (containing `K` elements) is correctly formulated, **the K-th element is simply the maximum element within this left half**. This means finding `max(L1, L2)`, where `L1` is the last element taken from `array1` for the left half, and `L2` is the last element taken from `array2` for the left half.
    *   The median problem involved `(Max(L1, L2) + Min(R1, R2)) / 2` for even counts or `Max(L1, L2)` for odd counts. For the K-th element, we are solely interested in `Max(L1, L2)`.

3.  **Adjusting Binary Search Range (`low` and `high`)**:
    *   Crucially, the `low` and `high` bounds for the binary search on `array1` need to be adjusted to prevent incorrect calculations or out-of-bounds issues.
    *   **`high`**: The maximum number of elements we can pick from `array1` is the minimum of `K` (since we only need `K` total elements for the left partition) and `N1` (the size of `array1`).
        ```
        high = min(K, N1)
        ```
    *   **`low`**: The minimum number of elements we *must* pick from `array1` depends on `K` and the size of `array2` (`N2`). If `K` is larger than `N2`, we must pick at least `K - N2` elements from `array1`. If `K` is small enough that we can pick all `K` elements from `array2`, then `low` can be 0.
        ```
        low = max(0, K - N2)
        ```
    *   These adjustments ensure that `cut1` (elements picked from `array1`) and `cut2` (elements picked from `array2`) always form a valid partition where `cut1 + cut2 = K` and elements are available.

##  Time Complexity

The time complexity of this binary search solution is **O(log(min(N1, N2)))**, where `N1` and `N2` are the sizes of `array1` and `array2` respectively. This is because the binary search is performed on the smaller of the two arrays.

