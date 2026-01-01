
# LeetCode 66: Plus One - Constraint Analysis & Solution

## Problem Description
You are given a **large integer** represented as an integer array `digits`, where each `digits[i]` is the $i^{th}$ digit of the integer. The digits are ordered from **most significant to least significant** in left-to-right order. The large integer does not contain any leading zeros.

**Task:** Increment the large integer by **one** and return the resulting array of digits.

### Example 1:
- **Input:** `digits =`
- **Output:** ``
- **Explanation:** The array represents 123. Incrementing by 1 gives 124.

---

## Constraint Analysis
Understanding the constraints is vital for choosing the right approach:
- `digits.length` is between **1 and 100**.
- `digits[i]` is between **0 and 9**.
- **Why Brute Force Fails:** Converting the array into a standard integer (like `long long` in C++) will result in an **overflow**. A `long long` can typically store about 18–19 digits, and `unsigned long long` can store up to 20 digits. Since our input can have up to **100 digits**, we cannot store it in a standard numeric variable,.

---

## Thought Process & Logic

### 1. Iterating from the Least Significant Bit (LSB)
Since we are adding 1, we must start from the end of the array (the rightmost digit), just like manual addition,.

### 2. Handling Non-Nine Digits
If the digit at the current index is **less than 9**, we simply increment it by 1 and return the array immediately, as no carry will be generated for the next position,.

### 3. Handling the Digit '9'
If the digit is **9**, adding 1 makes it 10. In this case:
- Set the current digit to **0**,.
- Carry over the **1** to the next digit (the loop continues to the next index on the left),.

### 4. The "All Nines" Corner Case
If the loop finishes and we haven't returned, it means the entire number consisted of 9s (e.g., ``).
- Every digit will have been turned into **0**.
- We must **insert/prepend a 1** at the beginning of the array to represent the final carry (e.g., `` becomes ``),.

---

## Code Implementation (Java)

```java
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
    
        // Start from the least significant digit
        for (int i = n - 1; i >= 0; i--) {
            // If the digit is less than 9, increment and return
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            // If the digit is 9, it becomes 0 due to carry
            digits[i] = 0;
        }
        
        // If we reach here, all digits were 9 (e.g., 999 -> 1000)
        int[] result = new int[n+1];
        result[0] = 1;
        return result;
    }
}
```

---

## Complexity Analysis
- **Time Complexity:** **$O(N)$**, where $N$ is the length of the array. In the worst case (all 9s), we iterate through the entire array and perform an insertion.
- **Space Complexity:** **$O(1)$** (if we don't count the space for the output array), as we are modifying the input array in place, though inserting at the beginning may require $O(N)$ space in some implementations.

---

### Analogy
Think of this problem like an **odometer** in a car. When a digit hits 9 and you travel one more mile, it flips to 0 and forces the digit to its left to increment. If the entire odometer is at `999,999` and you move forward, every dial flips to 0 and a new "1" appears at the very front to show a million.