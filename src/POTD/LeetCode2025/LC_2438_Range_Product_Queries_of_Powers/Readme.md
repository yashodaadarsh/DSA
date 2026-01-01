# Leetcode 2438: Range Product Queries of Powers

## Problem Description
You are given two integers `n` and an array of queries. The integer `n` is represented as a sum of distinct powers of two (in binary form).  
For each query `[start, end]`, return the product of powers in that range modulo `10^9 + 7`.

### Example
**Input:**
```
n = 15
queries = [[0, 1], [1, 3]]
```

**Output:**
```
[2, 56]
```

**Explanation:**
- Binary of `n = 15` → `1111` → powers: `[1, 2, 4, 8]`
- Query [0, 1]: 1 × 2 = 2
- Query [1, 3]: 2 × 4 × 8 = 64 → 64 % (10^9 + 7) = 56

---

## Approach: Direct Multiplication of Powers

In this approach:
1. Extract the list of powers of two from the binary representation of `n`.
2. For each query, multiply the relevant powers modulo `10^9 + 7`.

### Java Code Implementation
```java
package BitManipulation.LC_2438_Range_Product_Queries_of_Powers;

import java.util.*;

class Solution {
    private final int MOD = 1000000000 + 7;

    public int[] productQueries(int n, int[][] queries) {
        int ql = queries.length;
        int[] res = new int[ql];
        List<Integer> powers = new ArrayList<>();

        // Step 1: Build powers array
        for (int i = 0; i < 31; i++) {
            if (((1 << i) & n) != 0) {
                powers.add(1 << i);
            }
        }

        // Step 2: Answer queries
        int k = 0;
        for (int[] query : queries) {
            int st = query[0];
            int end = query[1];
            long prod = 1;

            for (int i = st; i <= end; i++) {
                prod = (prod * powers.get(i)) % MOD;
            }
            res[k++] = (int) prod;
        }
        return res;
    }
}
```

---

## Complexity Analysis
- **Time Complexity:** `O(Q * 32)` where `Q` is the number of queries and `32` is the average range length.
- **Space Complexity:** `O(32)` for storing the powers of two.

---

## Key Takeaways
- The binary representation of `n` directly gives the list of powers.
- Direct multiplication is straightforward but may be slow if queries are large — prefix product optimization can improve performance.