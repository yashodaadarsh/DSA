# Power of Four (LeetCode 342)

## Problem Statement

The problem asks you to determine if a given integer `n` is a power of four. This means checking if `n` can be expressed as 4 raised to some integer power (e.g., 4^x).

*   If `n` is a power of four, return `true`.
*   Otherwise, return `false`.

**Examples:**
*   `n = 16`: True (since 4^2 = 16)
*   `n = 5`: False
*   `n = 1`: True (since 4^0 = 1)
*   `n = 0`: False (0 is not a power of four)

This problem was reportedly asked by **Two Sigma**, a New York-based company.

---

## Approaches

### 1. Iterative Division

This approach involves repeatedly dividing the given number `n` by 4 until it can no longer be divided.

**Logic:**
1.  If `n <= 0`, return `false`.
2.  While `n % 4 == 0`, divide `n` by 4.
3.  If the final `n` is 1, return `true`; otherwise, return `false`.

**Time Complexity:**  
**O(log₄ n)** — because we divide by 4 in each step.

---

### 2. Mathematical (Logarithm)

Uses the change-of-base logarithmic formula to determine if `n` is a power of four.

**Logic:**
1.  If `n <= 0`, return `false`.
2.  Calculate `x = log₄ n = log(n) / log(4)`.
3.  If `x` is an integer, return `true`; otherwise, return `false`.

**Time Complexity:**  
**O(log n)** — due to logarithm computation.

---

### 3. Bit Manipulation + Modulo Arithmetic

Uses two properties:
1.  Powers of four are also powers of two → `(n & (n - 1)) == 0`.
2.  `(n - 1)` is divisible by 3 for powers of four → `(n - 1) % 3 == 0`.

**Logic:**
1.  If `n <= 0`, return `false`.
2.  Check if `n` is a power of two: `(n & (n - 1)) == 0`.
3.  Check if `(n - 1) % 3 == 0`.
4.  Return `true` if both are satisfied.

**Time Complexity:**  
**O(1)** — constant-time bitwise and modulo operations.

---

### 4. Bit Manipulation + `Integer.bitCount` Parity Check

This approach uses the fact that in a power of four, the number of `1` bits in `n - 1` is **even**.

**Logic:**
1.  If `n <= 0`, return `false`.
2.  `(n & (n - 1)) == 0` → ensures `n` is a power of two.
3.  `Integer.bitCount(n - 1) % 2 == 0` → checks if the count of `1` bits in `n - 1` is even, which is only true for powers of four.
4.  Return `true` if both conditions are satisfied.

**Time Complexity:**  
**O(1)** — bit counting is hardware-optimized.

---
