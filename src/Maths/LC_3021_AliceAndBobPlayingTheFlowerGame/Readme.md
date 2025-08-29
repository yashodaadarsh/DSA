
# Alice and Bob Playing Flower Game | Leetcode 3021


## Problem Description

Alice and Bob play a turn-based game on a field with two lanes of flowers.
*   **Lane 1** has `x` flowers.
*   **Lane 2** has `y` flowers.

**Game Rules:**
*   **Alice takes the first turn**.
*   In each turn, a player must **choose one lane** and **pick one flower** from that lane.
*   **The current player wins if, at the end of their turn, there are no flowers left at all**. This means **the player who plays the last turn wins the game**.

**Task:**
Given two integers `n` and `m`, compute the number of possible pairs `(x, y)` that satisfy the following conditions:
1.  Alice must win the game according to the described rules.
2.  The number of flowers `x` must be in the range `1 <= x <= n`.
3.  The number of flowers `y` must be in the range `1 <= y <= m`.

**Example:**
If `n = 3` and `m = 2`, Alice wins in 3 possible pairs: `(1, 2)`, `(2, 1)`, and `(3, 2)`.

## Key Observation and Game Logic

The core insight for this game is related to the **total number of flowers (`x + y`)**:

*   **Alice wins if the total number of flowers (`x + y`) is odd**.
    *   Since Alice always takes the first turn, if the total count is odd, she will always be the one to pick the odd-numbered flower (1st, 3rd, 5th, etc.). If the total number of flowers is odd, the last flower will be an odd-numbered pick, which means Alice will make that pick and win.
*   **Bob wins if the total number of flowers (`x + y`) is even**.
    *   If the total count is even, Bob will pick the even-numbered flower (2nd, 4th, 6th, etc.). The last flower will be an even-numbered pick, so Bob would win.

Therefore, for Alice to win, we need to find pairs `(x, y)` such that **`x + y` is an odd number**.

## Mathematical Approach

For `x + y` to be odd, one of the following two conditions must be true:
1.  **`x` is odd AND `y` is even.**
2.  **`x` is even AND `y` is odd.**

We need to count how many odd and even numbers are available for `x` (within `1` to `n`) and for `y` (within `1` to `m`).

**Counting Odd and Even Numbers in a Range (1 to k):**
*   **Total odd numbers:** `(k + 1) / 2` (integer division, which acts as a ceiling for `k/2`).
*   **Total even numbers:** `k / 2` (integer division, which acts as a floor for `k/2`).

Applying this to our problem:
*   **Number of odd `x` values** (from `1` to `n`): `(n + 1) / 2`
*   **Number of even `x` values** (from `1` to `n`): `n / 2`
*   **Number of odd `y` values** (from `1` to `m`): `(m + 1) / 2`
*   **Number of even `y` values** (from `1` to `m`): `m / 2`

**Calculating Total Valid Pairs for Alice to Win:**

1.  **If `x` is odd and `y` is even:**
    *   Number of possibilities = (Count of odd `x` values) \* (Count of even `y` values)
    *   `= ((n + 1) / 2) * (m / 2)`

2.  **If `x` is even and `y` is odd:**
    *   Number of possibilities = (Count of even `x` values) \* (Count of odd `y` values)
    *   `= (n / 2) * ((m + 1) / 2)`

The **total number of possible pairs `(x, y)`** for Alice to win is the sum of possibilities from these two cases.

## Solution Code (C++)

The solution is a single-line calculation:

```cpp
class Solution {
public:
    long long flowerGame(int n, int m) {
        // Calculate (x_odd * y_even) + (x_even * y_odd)
        return (1LL * (n + 1) / 2 * (m / 2)) + (1LL * (n / 2) * (m + 1) / 2);
    }
};
```
*   `1LL` is used to ensure the multiplication is performed using `long long` to prevent integer overflow, as the result can be large.
```java
class Solution {
    public long flowerGame(int n, int m) {
        int nEven = n/2;
        int nOdd = n - nEven;
        int mEven = m/2;
        int mOdd = m - mEven;
        return ( 1l*nEven * mOdd + 1l*nOdd*mEven ) ;
    }
}
```