Based on the sources provided, here is a representation of the **LeetCode 1975: Maximum Matrix Sum** problem and solution in a `README.md` format:

***

# Maximum Matrix Sum (LeetCode 1975)

## Problem Description
You are given an **$n \times n$ integer matrix**. You can perform the following operation any number of times:
*   Choose any two **adjacent** elements (sharing a common border) and multiply both by -1.

The goal is to **maximize the summation** of all matrix elements after any number of operations.

## Key Observations and Thought Process
The solution is built on two primary insights:

1.  **Negative Sign Propagation:** You can move a negative sign to any position in the matrix by repeatedly applying the operation to adjacent pairs.
2.  **Even vs. Odd Negatives:**
    *   If the total count of negative numbers in the matrix is **even**, you can eventually make **every single element positive** by pairing the negatives up and moving them toward each other.
    *   If the total count of negative numbers is **odd**, at least **one element must remain negative**.

To maximize the sum when an element must remain negative, the best strategy is to ensure the negative sign ends up on the element with the **smallest absolute value**. This minimizes the "loss" to the total sum.

## Algorithm Steps
1.  Initialize `sum` to 0 (to store the sum of absolute values), `countNegatives` to 0, and `minAbsValue` to a very large number.
2.  Iterate through every element in the matrix:
    *   Add the **absolute value** of the element to the `sum`.
    *   If the element is less than 0, increment `countNegatives`.
    *   Track the **minimum absolute value** found in the entire matrix.
3.  **Final Calculation:**
    *   If `countNegatives` is **even**, return the `sum`.
    *   If `countNegatives` is **odd**, return `sum - (2 * minAbsValue)`.
        *   *Note: We subtract it twice because it was originally added to the sum as a positive value; subtracting it once makes the sum neutral for that element, and subtracting it again accounts for its negative value.*

## Complexity Analysis
*   **Time Complexity:** $O(n \times m)$ (or $O(n^2)$ for a square matrix) because we visit every element exactly once.
*   **Space Complexity:** $O(1)$ as no extra space is used beyond a few variables.

## Implementation (C++)
```cpp
long long maxMatrixSum(vector<vector<int>>& matrix) {
    long long sum = 0;
    int countNegatives = 0;
    int minAbsValue = INT_MAX;

    for (int i = 0; i < matrix.size(); i++) {
        for (int j = 0; j < matrix.size(); j++) {
            sum += abs(matrix[i][j]);
            if (matrix[i][j] < 0) countNegatives++;
            minAbsValue = min(minAbsValue, abs(matrix[i][j]));
        }
    }

    if (countNegatives % 2 == 0) return sum;
    return sum - (2LL * minAbsValue);
}
```
*(Code structure and logic based on)*

***

**Analogy for Understanding:**
Think of the negative sign l
ike a **hot potato**. You can pass it between any two neighbors in the matrix until it reaches someone else holding a hot potato, at which point both potatoes disappear (become positive). However, if there is an odd number of potatoes, one person will always be left holding it. To keep the group's "total happiness" (sum) highest, you make sure the person with the smallest "happiness score" (minimum absolute value) is the one left holding the hot potato.