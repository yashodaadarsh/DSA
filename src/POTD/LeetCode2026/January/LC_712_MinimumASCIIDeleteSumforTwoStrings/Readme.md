

# 712. Minimum ASCII Delete Sum for Two Strings

## 🧩 Problem Statement

Given two strings `s1` and `s2`, return the **minimum ASCII sum of deleted characters** required to make both strings **equal**.

You may delete characters from **either string**, and the cost of deleting a character is its **ASCII value**.

---

## 📌 Examples

### Example 1

```
Input:
s1 = "sea", s2 = "eat"

Output:
231
```

**Explanation**
Delete `'s'` from `"sea"` → 115
Delete `'t'` from `"eat"` → 116
Final cost = **231**

---

### Example 2

```
Input:
s1 = "delete", s2 = "leet"

Output:
403
```

**Explanation**
Final common string = `"let"`
Total deleted ASCII sum = **403**

---

## 💡 Key Insight

This problem is a **Dynamic Programming** variant of **string alignment**.

Instead of maximizing matches (like LCS), we:

* **Minimize deletion cost**
* Consider ASCII values instead of character counts

---

# 🧠 Approach 1: Top-Down DP (Recursion + Memoization)

### 🔹 State Definition

`dp[i][j]` → minimum ASCII delete sum to make
`s1[i…]` and `s2[j…]` equal

---

### 🔹 Base Cases

* If both strings are exhausted → cost = `0`
* If one string is exhausted → delete all remaining characters from the other string

---

### 🔹 Recurrence Relation

If characters match:

```
dp[i][j] = dp[i+1][j+1]
```

If characters don’t match:

```
dp[i][j] = min(
    s1[i] + dp[i+1][j],   // delete from s1
    s2[j] + dp[i][j+1]    // delete from s2
)
```

---

### 🔹 Complexity

| Metric | Value        |
| ------ | ------------ |
| Time   | **O(m × n)** |
| Space  | **O(m × n)** |

---

### ✅ Pros

* Intuitive and easy to derive
* Closely follows problem definition

### ⚠️ Cons

* Recursive overhead
* Uses extra stack space

---

# 🧠 Approach 2: Bottom-Up DP (Tabulation)

### 🔹 Idea

Build a DP table where:

```
dp[i][j] = minimum ASCII delete sum for s1[i…] and s2[j…]
```

---

### 🔹 Base Case Initialization

If one string is exhausted, delete all remaining characters of the other:

```java
dp[m][j] = sum of ASCII values of s2[j…]
dp[i][n] = sum of ASCII values of s1[i…]
```

---

### 🔹 Transition

If characters match:

```
dp[i][j] = dp[i+1][j+1]
```

If they don’t:

```
dp[i][j] = min(
    s1[i] + dp[i+1][j],
    s2[j] + dp[i][j+1]
)
```

---

### 🔹 Complexity

| Metric | Value        |
| ------ | ------------ |
| Time   | **O(m × n)** |
| Space  | **O(m × n)** |

---

### ✅ Pros

* Faster in practice
* No recursion
* Stack-safe

### ⚠️ Cons

* Requires careful base-case initialization


---
