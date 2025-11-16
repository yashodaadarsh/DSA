
-----

# Number of Substrings With Only 1s

This document explains the approach for solving the "Number of Substrings With Only 1s" problem (LeetCode 1513) using a single-pass iterative approach.

## Problem Statement

> Given a binary string `s`, return the number of substrings that contain only '1's. Since the answer may be large, return it **modulo $10^9 + 7$**.

For example:

* `s = "0110111"`
* Substrings with only '1's are: "1", "1", "11", "1", "11", "111", "1", "11", "111"
* Wait, let's group them by the blocks:
    * From "11": "1", "1", "11" (3 substrings)
    * From "111": "1", "1", "1", "11", "11", "111" (6 substrings)
* Total = $3 + 6 = 9$

## 💡 Approach: Iterative Counting

The core idea is to understand how many new substrings are created *each time we encounter a '1'*.

A single '1' ("1") creates **1** substring.
If we add another '1' ("11"), we create **2** *new* substrings that end at this new '1': "1" and "11". (Total is $1 + 2 = 3$)
If we add a third '1' ("111"), we create **3** *new* substrings ending at this new '1': "1", "11", and "111". (Total is $1 + 2 + 3 = 6$)

We can see a pattern: **A continuous block of `k` ones contributes $1 + 2 + 3 + ... + k$ substrings.**

The provided solution cleverly calculates this sum incrementally. It maintains a `count` variable that tracks the length of the *current* continuous block of '1's.

* When it sees a '1', it increments `count`.
* It then adds this `count` to the total `res`. This `count` represents the number of *new* substrings that end at the current '1'.
* When it sees a '0', the continuous block is broken, so it resets `count` to 0.

-----

## Algorithm Walkthrough

We will use two main variables:

* `count`: Tracks the length of the current, unbroken sequence of '1's.
* `res`: The total number of '1'-only substrings found so far.

We also use `MOD = 10^9 + 7` to prevent integer overflow.

### Example: `s = "0110111"`

| Character `ch` | `count` (Before) | Action | `count` (After) | `res` (After) | Notes |
| :--- | :---: | :--- | :---: | :---: | :--- |
| `0` | 0 | `ch == '0'`, reset `count` | 0 | 0 | |
| `1` | 0 | `ch == '1'`, increment `count`, add `count` to `res` | 1 | (0 + 1) % MOD = **1** | New substrings: "1" (1) |
| `1` | 1 | `ch == '1'`, increment `count`, add `count` to `res` | 2 | (1 + 2) % MOD = **3** | New substrings: "1", "11" (2) |
| `0` | 2 | `ch == '0'`, reset `count` | 0 | 3 | Block of "11" is finished. |
| `1` | 0 | `ch == '1'`, increment `count`, add `count` to `res` | 1 | (3 + 1) % MOD = **4** | New substrings: "1" (1) |
| `1` | 1 | `ch == '1'`, increment `count`, add `count` to `res` | 2 | (4 + 2) % MOD = **6** | New substrings: "1", "11" (2) |
| `1` | 2 | `ch == '1'`, increment `count`, add `count` to `res` | 3 | (6 + 3) % MOD = **9** | New substrings: "1", "11", "111" (3) |

The loop finishes. The final result is **9**.

-----

## Complexity Analysis

* **Time Complexity:** $O(N)$
    * We iterate through the string `s` exactly once.
* **Space Complexity:** $O(1)$
    * We only use a few constant-space variables (`count`, `res`, `MOD`).

-----

## Java Solution

```java
class Solution {
    public int numSub(String s) {
        int count = 0 , res = 0;
        // Modulo for large results
        int MOD = (int)(1e9+7);
        
        for( char ch : s.toCharArray() ){
            if( ch == '1' ){
                // We found a '1', increment the current block length
                count++;
                // Add the new number of substrings to the result
                // (A block of length 'count' adds 'count' new substrings)
                res = ( res + count ) % MOD;
            } else {
                // The block of '1's is broken, reset the count
                count = 0;
            }
        }
        return res;
    }
}
```



## C++ Solution

```cpp
class Solution {
public:
    int numSub(string s) {
        int count = 0 , res = 0;
        int MOD = 1e9+7;
        for( char ch : s ){
            if( ch == '1' ){
                count++;
                res = ( res + count ) % MOD;
            }
            else{
                count = 0;
            }
        }
        return res;
    }
};
```