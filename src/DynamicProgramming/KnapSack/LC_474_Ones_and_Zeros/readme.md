## LeetCode 474: Ones and Zeroes | Dynamic Programming (0/1 Knapsack Variation)


---

### Problem Description

You are given an array of binary strings (`strs`) and two integers, `m` and `n`.

The task is to **return the size of the largest subset of `strs`** such that the total number of zeros in that subset is at most `m`, and the total number of ones is at most `n`.

**Constraints:**
*   `m` (maximum zeros) and `n` (maximum ones) go up to 100.
*   The number of strings in `strs` (the index `i`) goes up to 600.

**Example:**
If `strs = ["10", "0001", "111001", "1", "0"]`, and you have $m=5$ zeros and $n=3$ ones.
A subset of size 4 (e.g., `"10"`, `"0001"`, `"1"`, `"0"`) can be chosen because the total count of zeros (5) and ones (3) respects the limits.

---

### Approach 1: Why Greedy Fails

An intuitive initial approach, especially for beginners, is to use a **Greedy strategy**.

**Greedy Hypothesis:** To maximize the size of the subset, we should prioritize strings that are "smaller" (i.e., those that consume less of the available zero limit `m` and one limit `n`). This approach involves sorting the strings based on their length or usage of `m` and `n` and taking the smallest ones first.

**Failure Analysis:**
While the greedy approach might yield the correct result for certain test cases, such as the initial example provided (where the answer was 4), it is **not guaranteed to work universally** and is known to fail.

The core issue is that taking the smallest strings early might deplete a crucial resource (`m` or `n`) needed later for a combination of strings that would ultimately yield a larger subset size. In a counterexample, the initial zero count was exhausted, preventing a subsequent small string from being taken, proving the assumption of sorting and taking the starting elements fails.

**Conclusion:** We cannot rely entirely on the Greedy approach, forcing the exploration of all possibilities.

---

### Approach 2: Dynamic Programming (0/1 Knapsack)

The problem structure perfectly matches that of the **0/1 Knapsack problem**.

#### Knapsack Analogy

1.  **Items:** Each binary string in `strs` is an item.
2.  **Weights/Costs:** Instead of one weight, each string has **two weights**: the number of zeros and the number of ones it contains.
3.  **Capacity:** The limits `m` (maximum zeros) and `n` (maximum ones) define the container capacity.
4.  **Goal:** In traditional Knapsack, the goal is often to maximize value. Here, the goal is to maximize the **count of items taken** (the size of the subset).

#### Solution Strategy: Recursion with Memoization (Top-Down DP)

Since we must explore all possibilities (take or skip each string), a recursive approach modeled after Knapsack is used, enhanced with memoization to handle repeating subproblems.

##### 1. Pre-calculation

Before starting the recursion, it is efficient to **pre-calculate** the zero and one count for every string in the input. This information is stored in a helper vector (e.g., `Count`), preventing repeated string traversal inside the recursive calls. Each element in `Count` stores a pair representing `(count_of_zeros, count_of_ones)` for the corresponding string.

##### 2. Recursive Structure (`Solve(Count, m, n, i)`)

The function is called with the pre-calculated counts, the remaining capacities (`m` and `n`), and the current index (`i`).

**Base Cases:**

1.  If the index `i` is out of bounds (`i >= Count.size()`).
2.  If capacity is completely depleted (`m == 0` AND `n == 0`).
    *   In both cases, return 0.

**Recurrence Relation:**

For the element at index `i`, we decide between `Take` and `Skip`:

1.  **Take (Include the element):**
    *   **Capacity Check:** We can only take the element if its zero count is `<= m` AND its one count is `<= n`.
    *   If possible: `Take = 1 + Solve(..., m - zeros[i], n - ones[i], i + 1)`.
2.  **Skip (Exclude the element):**
    *   `Skip = Solve(..., m, n, i + 1)` (capacities `m` and `n` remain unchanged).

The result is the maximum of the two options: `Return max(Take, Skip)`.

##### 3. Memoization Implementation

To memoize the results, a **3D DP array** (`t`) is used, as three state variables are changing: remaining zeros (`m`), remaining ones (`n`), and the current index (`i`).

*   `t` dimensions are based on the constraints: `t`.
*   Memoization ensures that repeated subproblems are solved only once.

---
