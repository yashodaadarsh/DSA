# 1323. Maximum 69 Number

## Problem Description

You are given an integer `num` that is guaranteed to contain only the digits '6' and '9'. Your task is to **replace at most one digit** in this number. You can either change a '9' to a '6' or a '6' to a '9'. The ultimate goal is to return the **largest possible number** that can be created through this single replacement (or no replacement if the number is already maximized).

## Examples

* **Input:** `9669`  
  **Output:** `9969`  
  **Explanation:** Replace the first '6' from the left with a '9', resulting in `9969`.

* **Input:** `9996`  
  **Output:** `9999`  
  **Explanation:** Replace the '6' at the units place with a '9', yielding `9999`.

* **Input:** `999`  
  **Output:** `999`  
  **Explanation:** Already the maximum possible number. No replacement is needed.

---

## Solution Approaches

The problem can be solved using a **greedy strategy**:
- To maximize the number, you should change the **first '6' from the left** (most significant digit) into a '9'.
- This is because digits at higher place values have a greater impact on the magnitude of the number.
- If no '6' exists, the number is already at its maximum.

---

### Approach 1: Using String Conversion with Char Array

#### Idea:
- Convert number into a character array.
- Replace the first '6' with '9'.
- Convert back to integer.

*Time Complexity:* **O(n)**  
*Space Complexity:* **O(n)**

---

### Approach 2: Mathematical Approach (No Strings)

#### Idea:
- Traverse digits mathematically (using `% 10` and `/ 10`).
- Track the place value of the **leftmost '6'**.
- Add `3 × placeValue` to the number to flip it to `9`.

*Time Complexity:* **O(n)**  
*Space Complexity:* **O(1)**

---

### Approach 3: Using `String.replaceFirst`

#### Idea:
- Directly replace the first occurrence of '6' with '9' using `replaceFirst`.
- Convert back to integer.

*Time Complexity:* **O(n)**  
*Space Complexity:* **O(n)**

---

