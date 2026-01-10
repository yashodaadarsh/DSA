Below is a clean **`README.md`** you can directly use for this problem and solution.

---

# Maximum Product of Splitted Binary Tree (LeetCode 1339)

## 🧩 Problem Statement

Given the root of a binary tree, split the binary tree into **two subtrees** by removing **exactly one edge** such that the **product of the sums** of the two subtrees is **maximized**.

Return the **maximum product** of the sums of the two subtrees.

⚠️ Since the result can be very large, return it **modulo (10⁹ + 7)**.
⚠️ The product must be maximized **before** applying the modulo.

---

## 📌 Example

**Input**

```
root = [1,2,3,4,5,6]
```

**Output**

```
110
```

**Explanation**

Removing the edge between nodes `1` and `3` results in:

* Subtree 1 sum = 11
* Subtree 2 sum = 10
* Product = `11 × 10 = 110`

---

## 💡 Approach

This problem can be solved efficiently using **two DFS traversals**.

---

### 🔹 Step 1: Compute Total Sum of Tree

Traverse the entire tree and compute the sum of all node values.

This total sum helps determine the sum of the **complement subtree** after cutting an edge.

---

### 🔹 Step 2: Try Splitting at Every Edge

Perform a **postorder traversal**:

* Compute the sum of each subtree.
* Consider cutting the edge above the current subtree.
* Calculate product:

  ```
  product = subtreeSum × (totalSum − subtreeSum)
  ```
* Track the **maximum product** across all possible cuts.

---

## 🧠 Why Postorder Traversal?

Postorder traversal ensures:

* Left subtree sum is known
* Right subtree sum is known
* Current subtree sum can be computed correctly

This is crucial for evaluating valid splits.

---

## ⏱️ Time & Space Complexity

| Complexity | Value                                                |
| ---------- | ---------------------------------------------------- |
| Time       | **O(n)**                                             |
| Space      | **O(h)** (recursion stack, where `h` is tree height) |

---

## ❗ Important Observations

* **Do NOT apply modulo before comparing products**
* Use `long` for multiplication to prevent overflow
* Apply modulo **only to the final result**

---

## ✅ Final Algorithm Summary

1. Compute total tree sum.
2. Traverse tree again to compute subtree sums.
3. For each subtree, compute product with remaining tree.
4. Track maximum product.
5. Return result modulo `10⁹ + 7`.

---

## 🧪 Edge Cases

* Tree with only one node → no valid split
* Skewed trees
* Large node values (overflow-safe handling required)

---


