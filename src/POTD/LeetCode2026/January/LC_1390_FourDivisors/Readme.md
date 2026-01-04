# LeetCode 1390: Four Divisors

## **Problem Description**
Given an integer array `nums`, the goal is to **return the sum of divisors** of the integers in that array that have **exactly four divisors**. If an integer has more or fewer than four divisors, it is ignored. If no such integer exists in the array, return zero.

**Example:**
*   Input: `nums =`
*   Divisors of 21: 1, 3, 7, 21 (Count: 4). Sum = 32.
*   Divisors of 4: 1, 2, 4 (Count: 3) — Ignore.
*   Divisors of 7: 1, 7 (Count: 2) — Ignore.
*   Result: 32.

---

## **Constraints**
*   `nums.length` <= $10^4$.
*   `nums[i]` ranges from 1 to $10^5$.
*   Because of the length of the array, an $O(n^2)$ solution is not feasible as it would reach $10^8$ operations; we need a more efficient approach.

---

## **Approach and Logic**
The most straightforward approach is to iterate through each number in the array and determine how many divisors it has.

### **1. Finding Divisors Efficiently**
To find the divisors (factors) of a number, we do not need to check every number from 1 to `num`. Instead:
*   **Factors always appear in pairs.** For example, if 3 is a factor of 21, then $21 / 3 = 7$ is also a factor.
*   We only need to iterate up to the **square root of the number** ($\sqrt{num}$) to find all factors.
*   **Optimization:** Instead of using the expensive `sqrt()` function, we can use the condition `factor * factor <= num`.

### **2. Handling Special Cases**
*   **Perfect Squares:** For a number like 36, $\sqrt{36}$ is 6. In this case, the pair is $(6, 6)$. We must ensure we only **count the divisor once** rather than twice to avoid incorrect counts.
*   **Early Exit:** If at any point the divisor count exceeds four, we can stop checking that number immediately and return zero for its sum.

### **3. Algorithm Steps**
1.  Initialize a total `result = 0`.
2.  Iterate through each `num` in `nums`.
3.  For each `num`, call a helper function to find the count and sum of its divisors.
4.  Inside the helper:
    *   Iterate from `i = 1` to `i * i <= num`.
    *   If `num % i == 0`, increment the divisor count and add the factors to a temporary sum.
    *   If `i` is not the same as `num / i`, add the second factor and increment the count again.
5.  If the final count for that specific number is **exactly 4**, add its divisor sum to the total `result`.
6.  Return the final `result`.

---

## **Complexity Analysis**
*   **Time Complexity:** **$O(N \cdot \sqrt{M})$**, where $N$ is the number of elements in the array and $M$ is the maximum value in the array.
*   **Space Complexity:** **$O(1)$**, as we are using a constant amount of extra space regardless of the input size.

