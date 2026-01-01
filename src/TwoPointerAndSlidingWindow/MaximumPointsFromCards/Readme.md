
# L2. Maximum Points You Can Obtain from Cards | Two-Pointers and Sliding Window

This solution addresses the problem of maximizing the score when picking exactly $K$ cards from an array of $N$ cards, with the constraint that cards can only be picked consecutively from the front or the back of the array.

## Problem Statement

You are given an array of points for $N$ different cards (`nums`) and an integer $K$. You must select exactly $K$ cards.

The crucial condition is that you can only pick cards from the **front** or the **back** of the array; you cannot skip cards or pick from the middle. Your objective is to **maximize** the total number of points obtained.

**Example:** If $K=4$, possible configurations include picking all 4 from the front, or 3 from the front and 1 from the back, or 2 from the front and 2 from the back, and so on.

## Approach: Sliding Window Simulation

The optimal solution iterates through all possible ways to choose $K$ cards, where the pick is composed of $L$ cards from the left and $R$ cards from the right, such that $L + R = K$. This is efficiently solved using the Sliding Window concept by calculating the initial sum and then iteratively swapping the outermost element from the left pick with the outermost element from the right.

### Algorithm Steps

1.  **Initialization:** Calculate the sum of the initial window, which consists of picking all $K$ cards from the left side of the array (`leftSum`). Initialize `rightSum` to zero and set the maximum sum found so far (`maxSum`) to `leftSum`.
2.  **Iteration (Sliding/Swapping):** Loop $K$ times to simulate shifting the $K$ elements from the left side to the right side.
    *   **Shrink Left:** Remove one element from the `leftSum`. This element is located at index `i`, which starts at $K-1$ and moves down to 0.
    *   **Expand Right:** Add one element from the back of the array to the `rightSum`. The index for the right pick must start at $N-1$ and move backward (decrement) in each step.
    *   **Update Maximum:** Calculate the current total sum (`leftSum + rightSum`) and update `maxSum` if the current sum is greater.
3.  **Result:** After the loop completes, `maxSum` holds the maximum points obtainable.

## Pseudocode

The following pseudocode demonstrates the O(K) complexity solution:

```
function maxPoints(nums, K):
N = length(nums)
leftSum = 0
rightSum = 0
maxSum = 0

    // Step 1: Calculate initial leftSum (picking K cards from the front)
    for i from 0 to K - 1:
        leftSum = leftSum + nums[i]
    
    // Initial maximum sum is when all K are picked from the left
    maxSum = leftSum

    // Initialize right pointer to the last index of the array
    right_index = N - 1 

    // Step 2: Iterate to shift the window 
    // i goes from K-1 down to 0, representing the index being removed from the left
    for i from K - 1 down to 0:
        
        // Remove one element from the left sum (Shrink)
        leftSum = leftSum - nums[i]
        
        // Add one element from the right side (Expand)
        rightSum = rightSum + nums[right_index]
        
        // Update the right index for the next pick (move backward)
        right_index = right_index - 1 
        
        // Update the maximum sum found so far
        maxSum = Max(maxSum, leftSum + rightSum)
        
    // Step 3: Return the result
    return maxSum
```

## Complexity Analysis

This approach achieves optimal performance by avoiding nested operations:

*   **Time Complexity:** O(2K), which simplifies to **O(K)**. This is derived from O(K) for the initial left sum calculation and O(K) for the subsequent iteration loop.
*   **Space Complexity:** **O(1)**, as only a few variables (`leftSum`, `rightSum`, `maxSum`, `right_index`) are used.
