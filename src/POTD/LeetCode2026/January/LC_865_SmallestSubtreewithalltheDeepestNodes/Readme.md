
---

# 865. Smallest Subtree with All the Deepest Nodes

## 🧩 Problem Statement

Given the root of a binary tree, the **depth** of a node is the number of edges from the root to that node.

A node is called **deepest** if it has the **maximum depth** among all nodes in the tree.

Your task is to return the **smallest subtree** that contains **all the deepest nodes**.

> A subtree consists of a node and all of its descendants.

---

## 📌 Examples

### Example 1

```
Input:
root = [3,5,1,6,2,0,8,null,null,7,4]

Output:
[2,7,4]
```

### Example 2

```
Input:
root = [1]

Output:
[1]
```

### Example 3

```
Input:
root = [0,1,3,null,2]

Output:
[2]
```

---

## 💡 Key Insight

The answer is the **Lowest Common Ancestor (LCA)** of **all deepest nodes**.

Instead of explicitly finding all deepest nodes and computing their LCA, we can solve this efficiently using **two DFS traversals**.

---

# 🧠 Approach: Two DFS Traversals

---

## 🔹 Step 1: Find Maximum Depth

Traverse the tree to compute the **maximum depth** of the tree.

```java
maxDepth = findDepth(root, 0);
```

* Use DFS
* Track depth level
* Store the maximum depth found

---

## 🔹 Step 2: Find the Smallest Subtree

Traverse the tree again:

* Compute depth of left and right subtrees
* If **both subtrees reach the maximum depth**, then the current node is a candidate
* Update the result node when such a condition is met

This ensures we return the **lowest (smallest) node** that contains all deepest nodes.

---

## 🔍 Core Condition

```java
if (left == maxDepth && left == right) {
    subTree = root;
}
```

✔ Both left and right subtrees reach the deepest level
✔ Current node becomes the smallest subtree containing all deepest nodes

---

## 🧠 Why This Works

* Deepest nodes must lie in either:

    * Both left and right subtrees → current node is LCA
    * Only one subtree → recurse further down
* The last node satisfying the condition is the **deepest valid subtree**

---

## ⏱️ Time & Space Complexity

| Metric | Value                      |
| ------ | -------------------------- |
| Time   | **O(n)**                   |
| Space  | **O(h)** (recursion stack) |

Where:

* `n` = number of nodes
* `h` = height of the tree

---

## 🧪 Edge Cases Handled

✔ Single-node tree
✔ Skewed trees
✔ Multiple deepest nodes
✔ Deepest node is a leaf

---

## 🏁 Final Algorithm Summary

1. Compute the maximum depth of the tree.
2. Traverse again to find the lowest node whose left and right subtrees reach this depth.
3. Return this node as the smallest subtree.

---
