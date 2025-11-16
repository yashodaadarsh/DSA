
-----

# Maximum Number of Operations to Move Ones to the End

This document outlines the approach for solving LeetCode problem 3228. The solution uses a single-pass, $O(N)$ approach with constant space.

## Problem Statement

You are given a binary string `s`. You can perform the following operation:

1.  Choose an index `i` where `s[i] == '1'` and `s[i + 1] == '0'`.
2.  Move the character `s[i]` to the right until it hits the end of the string or another '1'.

For example, if `s = "010010"` and we choose `i = 1`, the '1' at index 1 will "jump" over the '0's at indices 2 and 3, stopping before the '1' at index 4. The resulting string will be `s = "000110"`.

The goal is to find the **maximum** number of operations you can perform.

## 💡 Core Insight

The complex "move" operation is a bit of a distraction. The key to the problem is to re-frame what an "operation" represents.

An operation is triggered by a `10` pair. To move all '1's to the end, every '1' must eventually "jump" over every block of '0's that is to its right.

Let's consider an example: `s = "1010"`

1.  We have '1's at indices 0 and 2.
2.  We have '0's at indices 1 and 3.

To get to the end state ("0011"), the '1' at index 0 must jump the '0' at index 1 (Op 1). The string becomes `"0110"`.
Now, the '1' at index 1 must jump the '0' at index 3 (Op 2). The string becomes `"0101"`.
Finally, the '1' at index 2 must jump the '0' at index 3 (Op 3). The string becomes `"0101"`.
Wait, let's trace the example `s = "1010"` by the operations:

* Op 1 (choose `i=0`): `s = "**10**10"`. The '1' at 0 jumps the '0' at 1, stopping before the '1' at 2. `s` becomes `"0110"`.
* Op 2 (choose `i=2`): `s = "01**10**"`. The '1' at 2 jumps the '0' at 3. `s` becomes `"0101"`.
* Op 3 (choose `i=1`): `s = "0**10**1"`. The '1' at 1 jumps the '0' at 2. `s` becomes `"0011"`.
* Total ops = 3.

Let's analyze this result (3).

* The first '0' (at index 1) must be jumped by **one** '1' (the one at index 0).
* The second '0' (at index 3) must be jumped by **two** '1's (the ones at indices 0 and 2).
* Total operations = $1 + 2 = 3$.

This reveals the true logic: **The total number of operations is the sum, for each contiguous block of '0's, of the total number of '1's that appear before it.**

## ⚙️ Algorithm Explanation

The provided solution cleverly implements this logic in a single pass. It uses four key variables:

* `ans`: The total number of operations (our final answer).
* `count`: A running counter of *all* '1's seen so far.
* `last`: The index of the most recently seen '1'.
* `prev`: The index of the `last` '1' *at the time we last performed an operation*. This is used to identify the start of a *new* block of '0's.

The algorithm iterates through the string:

1.  If the character is **'1'**:

    * Increment `count`.
    * Update `last` to the current index `i`.

2.  If the character is **'0'**:

    * We must check if this is the *start* of a new block of '0's.
    * We do this with the check: `if (last != prev)`.
        * If `last == prev`, it means we've already seen a '0' since the last '1' (e.g., we're in the middle of a "1000..." block). We do nothing.
        * If `last != prev`, it means this is the **first '0'** we've encountered after a '1' (or a block of '1's). This signifies a new block of '0's that must be "jumped".
    * When this condition is true, we know that *all* `count` '1's we've seen so far must, at some point, jump this block of '0's.
    * Therefore, we add the current `count` to our `ans`: `ans += count`.
    * To prevent adding `count` again for the *next* '0' in this same block, we "lock" it by setting `prev = last`.

-----

## 📊 Walkthrough: `s = "1001101"`

Let's trace the solution with the first example.
`ans = 0`, `count = 0`, `last = -1`, `prev = -1`

| `i` | `s[i]` | `count` | `last` | `prev` | `last != prev`? | Action | `ans` |
|:---:|:---:|:---:|:---:|:---:|:---:|:--- |:---:|
| 0 | '1' | 1 | 0 | -1 | - | | 0 |
| 1 | '0' | 1 | 0 | -1 | Yes (0 \!= -1) | `ans += count` (1)<br>`prev = last` (0) | 1 |
| 2 | '0' | 1 | 0 | 0 | No (0 == 0) | Do nothing | 1 |
| 3 | '1' | 2 | 3 | 0 | - | | 1 |
| 4 | '1' | 3 | 4 | 0 | - | | 1 |
| 5 | '0' | 3 | 4 | 0 | Yes (4 \!= 0) | `ans += count` (3)<br>`prev = last` (4) | 4 |
| 6 | '1' | 4 | 6 | 4 | - | | 4 |

The loop finishes. The final result is **4**.

-----

## Complexity Analysis

* **Time Complexity:** $O(N)$
    * We iterate through the string exactly one time.
* **Space Complexity:** $O(1)$
    * We only use a few integer variables (`ans`, `count`, `last`, `prev`, `n`).

-----

## Java Solution

```java
class Solution {
    public int maxOperations(String s) {
        int n = s.length();
        int ans = 0 , count = 0;
        int last = -1 , prev = -1;

        for( int i = 0 ; i < n ; i++ ){
            char a = s.charAt(i);
            if( a == '1' ){
                // Increment total '1's count
                count++;
                // Record the position of this '1'
                last = i;
            }
            // We hit a '0'. Check if it's a *new* block of '0's.
            // 'last != prev' is true only for the *first* '0' after a block of '1's.
            else if( last != prev ){
                // Add the total '1's seen so far to the answer.
                // All 'count' '1's must jump this '0' block.
                ans += count;
                // "Lock" this operation. We won't add again until we see a new '1'.
                prev = last;
            }
        }
        
        return ans;
    }
}
```
## C++ Solution
```cpp
class Solution {
public:
    int maxOperations(string s) {
        int last = -1 , prev = -1;
        int ans = 0 , count = 0;
        int n = s.size();
        for( int i = 0 ; i < n ; i++ ){
            if( s[i] == '1' ){
                count++;
                last = i;
            }
            else if( last != prev ){
                ans += count;
                prev = last;
            }
        }
        return ans;
    }
};
```