# Leetcode 869: Reordered Power of 2

## Problem Description

Given an integer `n`, determine if its digits can be reordered (including the original order, with no leading zeros) to form a number that is a power of two.

**Examples:**
- `n = 526` → can be reordered to `256` = 2⁸ → `true`
- `n = 821` → can be reordered to `128` = 2⁷ → `true`
- `n = 215` → can be reordered to `512` = 2⁹ → `true`

---

## Approaches Implemented

### 1. Sort and Compare (`reorderedPowerOf2_Approach1`)

**Idea:**
- Two numbers that are permutations of each other will have the same sorted digit sequence.
- Sort the digits of `n`.
- Compare with sorted digits of every power of two from `2⁰` to `2²⁹`.

**Implementation Details:**
- `getSort(int n)` converts `n` to a `String`, sorts characters, and returns the sorted string.
- Loop from `p = 0` to `29`, check if `getSort(n)` equals `getSort(1 << p)`.

**Time Complexity:** O(29 × D log D), where D ≤ 10 → effectively **O(1)**  
**Space Complexity:** O(D) → effectively **O(1)**

---

### 2. Precompute and Store Sorted Powers of Two (`reorderedPowerOf2_Approach2`)

**Idea:**
- Avoid sorting powers of two repeatedly by precomputing and storing all their sorted forms in a `HashSet`.
- Simply check if the sorted digits of `n` exist in the set.

**Implementation Details:**
- Static block runs once: for `p = 0` to `29`, store `getSortStatic(1 << p)` in `HashSet<String> st`.
- At runtime, sort `n` and check set membership.

**Time Complexity:**
- Precomputation: O(29 × D log D) once.
- Query: O(D log D) for sorting + O(1) set lookup.  
  **Space Complexity:** O(29 × D) → O(1) for fixed D.

---

### 3. Digit Frequency Counting (Vector) (`reorderedPowerOf2_Approach3`)

**Idea:**
- Represent each number as a digit frequency array of size 10.
- If `n` has the same frequency array as a power of two, they are permutations.

**Implementation Details:**
- `getDigitCountVector(int n)` returns an `int[10]` with counts of each digit.
- Compare `n`'s frequency array with that of each power of two.

**Time Complexity:** O(29 × D) → effectively **O(1)**  
**Space Complexity:** O(1) (fixed-size array).

---

### 4. Digit Frequency Counting (Integer Encoding) (`reorderedPowerOf2_Approach4`)

**Idea:**
- Encode the digit frequency into a single integer where digit `d` contributes `10^d` to the sum.
- Two numbers with the same encoded value have identical digit frequencies.

**Implementation Details:**
- `getNumberFormat(int n)` sums `10^(digit)` for each digit in `n`.
- Compare encoded `n` with encoded powers of two.

**Time Complexity:** O(29 × D) → effectively **O(1)**  
**Space Complexity:** O(1).

---

## Java Code

```java
package Strings.LC_869_Reordered_Power_of_2;

import java.util.*;

class Solution {

    // Approach 1: Sort and Compare
    public String getSort(int n) {
        String str = String.valueOf(n);
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    public boolean reorderedPowerOf2_Approach1(int n) {
        String s = getSort(n);
        for (int p = 0; p <= 29; p++) {
            if (s.equals(getSort(1 << p))) return true;
        }
        return false;
    }

    // Approach 2: Precompute and Store Sorted Powers of Two
    public static String getSortStatic(int n) {
        String str = String.valueOf(n);
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    static Set<String> st = new HashSet<>();
    static {
        for (int p = 0; p <= 29; p++) {
            st.add(getSortStatic(1 << p));
        }
    }

    public boolean reorderedPowerOf2_Approach2(int n) {
        String s = Solution.getSortStatic(n);
        return Solution.st.contains(s);
    }

    // Approach 3: Digit Frequency Counting (Vector)
    private int[] getDigitCountVector(int n) {
        int[] count = new int[10];
        while (n > 0) {
            count[n % 10]++;
            n /= 10;
        }
        return count;
    }

    public boolean reorderedPowerOf2_Approach3(int n) {
        int[] nCount = getDigitCountVector(n);
        for (int p = 0; p <= 29; p++) {
            if (Arrays.equals(nCount, getDigitCountVector(1 << p))) {
                return true;
            }
        }
        return false;
    }

    // Approach 4: Digit Frequency Counting (Integer Encoding)
    private int pow(int a, int b) {
        if (b == 0) return 1;
        int half = pow(a, b / 2);
        int result = half * half;
        if ((b & 1) == 1) result *= a;
        return result;
    }

    private int getNumberFormat(int n) {
        int num = 0;
        while (n != 0) {
            num += pow(10, n % 10);
            n = n / 10;
        }
        return num;
    }

    public boolean reorderedPowerOf2_Approach4(int n) {
        int input = getNumberFormat(n);
        for (int p = 0; p <= 29; p++) {
            if (input == getNumberFormat(1 << p)) return true;
        }
        return false;
    }
}
