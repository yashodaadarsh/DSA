
# LeetCode 717: 1-bit and 2-bit Characters

## Overview

This problem (LeetCode 717, marked Easy) requires determining if the last character in a given binary array must be a one-bit character. The problem involves understanding two types of special characters that can represent the binary array `bits`.

The core challenge is to correctly parse the sequence of bits to see if the final bit (`0`) is consumed as part of a two-bit character or remains isolated as a one-bit character.

## Character Definitions and Constraints

1.  **One-bit Character:** Represented only by `0`.
2.  **Two-bit Character:** Represented by two bits, either `10` or `11`. Two-bit characters always start with `1`.
3.  **Constraint:** The input binary array `bits` always ends with `0`.
4.  **Goal:** Return `True` if the last character must be a one-bit character (i.e., `0`).

## Approach 1: Left-to-Right Traversal (Greedy Approach)

This approach uses a pointer `i` starting at the beginning of the array and traverses left-to-right.

**Logic:**

1.  We iterate until `i < n - 1` (since we know the last index `n-1` is guaranteed to be `0`).
2.  If the bit at index `i` is `1`, it is guaranteed to be the start of a two-bit character (`10` or `11`). We skip two indices (`i += 2`) to account for both bits of this character.
3.  If the bit at index `i` is `0`, it is a one-bit character, and we simply move to the next index (`i++`).
4.  After the loop finishes, we check if `i` has landed exactly on the last index (`n - 1`). If it has, the last character remained isolated (`0`), and the answer is `True`; otherwise, it is `False`.

**Complexity:**

*   Time Complexity: O(N).
*   Space Complexity: O(1).

### Code Initialization (Approach 1)

**Java Structure:**

```java
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;

        // Traverse up to the second-to-last index (n-2)
        while (i < n - 1) { 
            // Check if bits[i] is 1 (start of a 2-bit character)
            if (bits[i] == 1) { 
                i += 2; // Jump by 2
            } else { 
                // If bits[i] is 0 (a 1-bit character)
                i += 1; // Jump by 1
            }
        }
        
        // Return true if the pointer landed exactly on n - 1
        return i == n - 1; 
    }
}
```

**C++ Structure:**

```cpp
class Solution {
public:
    bool isOneBitCharacter(vector<int>& bits) {
        int n = bits.size();
        int i = 0;

        // Traverse up to the second-to-last index (n-2)
        while (i < n - 1) {
            // Check if bits[i] is 1 (start of a 2-bit character)
            if (bits[i] == 1) {
                i += 2; // Jump by 2
            } else {
                // If bits[i] is 0 (a 1-bit character)
                i += 1; // Jump by 1
            }
        }

        // Return true if the pointer landed exactly on n - 1
        return i == n - 1;
    }
};
```

---

## Approach 2: Right-to-Left Traversal (Count Continuous Ones)

This approach relies on a key observation regarding the contiguous sequence of `1`s immediately preceding the final `0`.

**Logic:**

1.  The last bit is guaranteed to be `0` (index `n-1`). We start the traversal from index `n-2` and move backward (right-to-left).
2.  We count the number of continuous `1`s encountered until we hit a `0` or the beginning of the array.
3.  A two-bit character consumes two bits (a pair).
4.  If the count of these continuous `1`s is **even**, it means all the `1`s can form pairs among themselves, leaving the last `0` isolated. In this case, the answer is `True`.
5.  If the count of these continuous `1`s is **odd**, the extra `1` will pair up with the last `0` (index `n-1`), meaning the last character is not a one-bit character. In this case, the answer is `False`.

**Complexity:**

*   Time Complexity: O(N) (Worst case, if the array is all `1`s before the last `0`). Potentially better in scenarios where a `0` appears close to the end.
*   Space Complexity: O(1).

### Code Initialization (Approach 2)

**Java Structure:**

```java
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int countOfOnes = 0;
        
        // Start traversal from n - 2 (index just before the final 0)
        int i = n - 2; 

        // Keep counting as long as we see 1s and are within array bounds
        while (i >= 0 && bits[i] == 1) {
            countOfOnes++;
            i--;
        }
        
        // If the count of continuous 1s is even, the answer is True. 
        // If the count is odd, the answer is False.
        // We check if (countOfOnes % 2 == 0)
        return (countOfOnes % 2 == 0);
    }
}
```

**C++ Structure:**

```cpp
class Solution {
public:
    bool isOneBitCharacter(vector<int>& bits) {
        int n = bits.size();
        int countOfOnes = 0;

        // Start traversal from n - 2 (index just before the final 0)
        int i = n - 2; 

        // Keep counting as long as we see 1s and are within array bounds
        while (i >= 0 && bits[i] == 1) {
            countOfOnes++;
            i--;
        }

        // If the count of continuous 1s is even, the answer is True.
        // If the count is odd, the answer is False.
        // We check if (countOfOnes % 2 == 0)
        return (countOfOnes % 2 == 0);
    }
};
```