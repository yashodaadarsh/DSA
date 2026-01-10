
---

# 1458. Max Dot Product of Two Subsequences

## 🧩 Problem Statement

Given two integer arrays `nums1` and `nums2`, return the **maximum dot product** between **non-empty subsequences** of `nums1` and `nums2` having the **same length**.

A subsequence is formed by deleting elements without changing the relative order of the remaining elements.

---

## 📌 Examples

### Example 1

```
Input:
nums1 = [2,1,-2,5]
nums2 = [3,0,-6]

Output:
18
```

### Example 2

```
Input:
nums1 = [3,-2]
nums2 = [2,-6,7]

Output:
21
```

### Example 3

```
Input:
nums1 = [-1,-1]
nums2 = [1,1]

Output:
-1
```

---

## 💡 Key Insight

This is a **Dynamic Programming** problem similar to **Longest Common Subsequence**, but instead of length, we maximize a **dot product**.

⚠️ Important:

* The subsequences must be **non-empty**
* Dot product can be **negative**
* We must consider **skipping elements** in either array

---

# 🧠 Approach 1: Top-Down DP (Recursion + Memoization)

### 🔹 Idea

At each index `(i, j)` we have four choices:

1. Take `nums1[i] * nums2[j]` (start a new subsequence)
2. Extend an existing subsequence → add result from `(i+1, j+1)`
3. Skip element from `nums2`
4. Skip element from `nums1`

We memoize results to avoid recomputation.

---

### 🔹 Recurrence Relation

```
dp[i][j] = max(
    nums1[i] * nums2[j],
    nums1[i] * nums2[j] + dp[i+1][j+1],
    dp[i][j+1],
    dp[i+1][j]
)
```

---

### 🔹 Base Case

If we reach the end of either array:

```
return -∞ (large negative number)
```

This ensures **at least one pair is selected**.

---

### 🔹 Complexity

| Metric | Value                                       |
| ------ | ------------------------------------------- |
| Time   | **O(n × m)**                                |
| Space  | **O(n × m)** (memo table + recursion stack) |

---



# 🧠 Approach 2: Bottom-Up DP (Tabulation)

### 🔹 Idea

Build a DP table where:

```
dp[i][j] = maximum dot product using nums1[0..i] and nums2[0..j]
```

Each cell considers:

1. Taking both current elements
2. Extending a previous subsequence
3. Skipping from nums1
4. Skipping from nums2

---

### 🔹 Transition

```
dp[i][j] = max(
    nums1[i] * nums2[j],
    nums1[i] * nums2[j] + dp[i-1][j-1],
    dp[i][j-1],
    dp[i-1][j]
)
```

Boundary conditions are handled carefully when `i == 0` or `j == 0`.

---

### 🔹 Complexity

| Metric | Value        |
| ------ | ------------ |
| Time   | **O(n × m)** |
| Space  | **O(n × m)** |

---
