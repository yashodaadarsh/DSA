
# Finding the Median of Two Sorted Arrays of Different Sizes


## Problem Statement

Given two sorted arrays, `array1` (size `N1`) and `array2` (size `N2`), the goal is to find the median of these two arrays when combined.

### What is a Median?

The median of a sorted list of numbers is the middle value.
*   **If the total number of elements (`N = N1 + N2`) is odd:** The median is the single middle element. 
*   **If the total number of elements (`N = N1 + N2`) is even:** There isn't a single middle element. The median is the average of the two middle elements.

The key challenge is that the input arrays are sorted, and this property should be leveraged for efficient solutions.

## Approaches

We will explore three approaches: Brute Force, a Space-Optimized Better Approach, and the Optimal Binary Search Approach.

### 1. Brute Force Approach (Merge and Find Median)

#### Concept
The simplest approach is to **merge both sorted arrays into a new, single sorted array**, and then find the median from this merged array. Since both input arrays are already sorted, merging them into a third sorted array is straightforward.

#### Logic
1.  Initialize an empty array, `array3`, and two pointers, `i` and `j`, pointing to the first elements of `array1` and `array2` respectively.
2.  Iterate while `i < N1` and `j < N2`:
    *   Compare `array1[i]` and `array2[j]`.
    *   Add the smaller element to `array3` and increment its corresponding pointer (`i` or `j`).
3.  After the loop, if there are any remaining elements in `array1` or `array2`, add them directly to `array3`.
4.  Once `array3` is fully populated and sorted, calculate the median:
    *   Let `N = N1 + N2` be the total number of elements.
    *   **If `N` is odd:** The median is `array3[N / 2]`.
    *   **If `N` is even:** The median is `(array3[N / 2] + array3[(N / 2) - 1]) / 2.0`. (Note: Typecasting to `double` is crucial for correct floating-point results).

#### Time Complexity
*   **O(N1 + N2)**: We iterate through all elements of both arrays once to merge them.

#### Space Complexity
*   **O(N1 + N2)**: An auxiliary `array3` is used to store all merged elements.

#### Limitations
This approach is generally **partially accepted** but results in a **Time Limit Exceeded (TLE)** error for larger inputs due to the linear time complexity and extra space, which often needs to be optimized further.

### 2. Better Approach (Optimized Space)

#### Concept
This approach refines the brute force by **eliminating the need for an extra array `array3`** to store all merged elements. Instead, we only keep track of the specific elements required to calculate the median.

#### Logic
1.  Determine the total number of elements `N = N1 + N2`.
2.  Identify the indices of the elements needed for the median:
    *   `index2_val = N / 2`
    *   `index1_val = (N / 2) - 1` (if `N` is even).
    *   For odd `N`, only `index2_val` is directly used for the median.
3.  Simulate the merging process using pointers `i` and `j` for `array1` and `array2`, and an `index_tracker` (or `count`) to represent the current position in the hypothetical merged array.
4.  Maintain two variables, `element1` and `element2`, initialized to -1 (or `INT_MIN`).
5.  In the loop, when `index_tracker` matches `index1_val`, store the current smallest element as `element1`. When `index_tracker` matches `index2_val`, store the current smallest element as `element2`.
6.  Once the relevant elements are found, calculate the median:
    *   **If `N` is odd:** The median is `element2`.
    *   **If `N` is even:** The median is `(element1 + element2) / 2.0`.
7.  **Optimization:** The process can stop as soon as both `element1` and `element2` are found.

#### Time Complexity
*   **O(N1 + N2)**: We still iterate through elements in a similar fashion to the brute force to find the median-contributing elements.

#### Space Complexity
*   **O(1)**: No extra array is used; only a few variables are needed to store pointers and median elements.

#### Limitations
While space is optimized, the time complexity remains linear, leading to **Time Limit Exceeded (TLE)** for larger inputs. Further optimization in time complexity is required.

### 3. Optimal Approach (Binary Search)

#### Concept
This approach leverages the **sorted property** of the arrays to apply binary search, achieving a logarithmic time complexity. The core idea is to find a **"partition"** point within the arrays that divides them into a "left half" and a "right half" such that all elements in the left half are less than or equal to all elements in the right half, and the halves have the correct number of elements for the median.

#### Key Intuition
1.  **Binary Search on Smaller Array:** To minimize time complexity, perform the binary search on the *smaller* of the two arrays (`N1` or `N2`). Ensure `array1` is always the smaller array by swapping if necessary.
2.  **Symmetry:** We need to find `(N1 + N2 + 1) / 2` elements for the left half to correctly determine the median (this formula works for both even and odd total elements, simplifying the logic).
3.  **Partitioning:** We choose `cut1` elements from `array1` and `cut2` elements from `array2`.
    *   `cut1` will be determined by binary search within `array1` (from `0` to `N1`).
    *   `cut2` is derived: `cut2 = (N1 + N2 + 1) / 2 - cut1`.

#### Logic
1.  **Initialization:**
    *   Let `low = 0` and `high = N1` (the size of the smaller array) for the binary search range for `cut1`.
    *   Initialize `L1, L2, R1, R2` (left max from array1/2, right min from array1/2) with `INT_MIN` and `INT_MAX` to handle edge cases where a partition might be at the very beginning or end of an array.
2.  **Binary Search Loop (`while (low <= high)`):**
    *   Calculate `mid1 = (low + high) / 2` (this is `cut1`).
    *   Calculate `mid2 = (N1 + N2 + 1) / 2 - mid1` (this is `cut2`).
    *   Determine `L1`, `L2`, `R1`, `R2`:
        *   `L1 = (mid1 == 0) ? INT_MIN : array1[mid1 - 1]`
        *   `L2 = (mid2 == 0) ? INT_MIN : array2[mid2 - 1]`
        *   `R1 = (mid1 == N1) ? INT_MAX : array1[mid1]`
        *   `R2 = (mid2 == N2) ? INT_MAX : array2[mid2]`.
    *   **Validity Check (Cross-Comparison):**
        *   If `L1 <= R2` and `L2 <= R1`: This is a **valid partition**.
            *   Calculate the median based on `N = N1 + N2`:
                *   **If `N` is odd:** Median is `max(L1, L2)`.
                *   **If `N` is even:** Median is `(max(L1, L2) + min(R1, R2)) / 2.0`.
            *   Return the median.
        *   Else if `L1 > R2`: This means `mid1` is too large (we've taken too many from `array1`, or too few from `array2`). We need to reduce `cut1`. So, `high = mid1 - 1`.
        *   Else (`L2 > R1`): This means `mid1` is too small (we've taken too few from `array1`, or too many from `array2`). We need to increase `cut1`. So, `low = mid1 + 1`.
3.  If the loop completes without finding a valid partition (should not happen for valid inputs), a dummy return value might be needed.

#### Time Complexity
*   **O(log(min(N1, N2)))**: The binary search is performed on the smaller array.

#### Space Complexity
*   **O(1)**: Only a few variables are used.

## Conclusion

The **Binary Search Approach** is the most optimal solution for this problem, providing significant performance benefits over the brute force and space-optimized linear solutions, especially for large input arrays. It effectively utilizes the sorted nature of the input arrays to find the median in logarithmic time.
```