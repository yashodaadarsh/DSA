# Leetcode 2561: Rearranging Fruits

## Problem Description
You are given two integer arrays, `basket1` and `basket2`, each representing the costs of fruits in two baskets.  
Your goal is to make both baskets **equal** (same elements after sorting) by performing the following operation any number of times:

- Choose `i` and `j` and swap `basket1[i]` with `basket2[j]`.
- The cost of the swap is `min(basket1[i], basket2[j])`.

Return the **minimum total cost** to make both baskets equal, or **-1** if impossible.

---

## Key Observations

1. **Frequency balancing**  
   Use a `Map<Integer, Integer>` to track count differences between baskets.  
   Positive count = extra in basket1, Negative count = extra in basket2.

2. **Impossible case**  
   If the difference for any fruit type is odd → return `-1`.

3. **Only `count / 2` swaps per fruit type**  
   Each swap fixes 2 units of imbalance, so store `(abs(count)/2)` copies of the fruit cost in a `List<Integer>`.

4. **Swap cost optimization**  
   Use the **global minimum fruit cost** from both baskets for potential indirect swaps:
    - Direct swap: `currentVal`
    - Indirect swap: `2 * minElem` (cheaper if minElem is much smaller than currentVal)  
      Choose the smaller.

---

## Approach

1. Count differences for each fruit type and track the global minimum cost.
2. If any difference is odd → return `-1`.
3. Store `abs(count)/2` copies in a list of needed swaps.
4. Sort the list and sum costs for first `size/2` elements using the cheaper of:
    - `currentVal`
    - `2 * minElem`

---

## Java Solution

```java
import java.util.*;

class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int minElem = Integer.MAX_VALUE;

        // Populate from basket1
        for (int x : basket1) {
            countMap.put(x, countMap.getOrDefault(x, 0) + 1);
            minElem = Math.min(minElem, x);
        }

        // Decrement from basket2
        for (int x : basket2) {
            countMap.put(x, countMap.getOrDefault(x, 0) - 1);
            minElem = Math.min(minElem, x);
        }

        List<Integer> extras = new ArrayList<>();

        // Build extras list and check impossibility
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int fruit = entry.getKey();
            int diff = entry.getValue();

            if (diff % 2 != 0) return -1; // Odd difference → impossible

            for (int i = 0; i < Math.abs(diff) / 2; i++) {
                extras.add(fruit);
            }
        }

        // Sort to handle cheapest swaps first
        Collections.sort(extras);

        long cost = 0;
        int n = extras.size();

        // Only first half (smallest) needed for direct cost calculation
        for (int i = 0; i < n / 2; i++) {
            int val = extras.get(i);
            cost += Math.min(val, 2L * minElem);
        }

        return cost;
    }
}
