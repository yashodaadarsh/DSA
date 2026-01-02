
# LeetCode 961: N-Repeated Element in Size 2N Array

---

## Problem Description
You are given an integer array `nums` of size **2n** with the following properties:
*   The total number of elements is **2n**.
*   There are exactly **n + 1** unique elements,.
*   Exactly **one** element is repeated **n** times.

**Task:** Find and return the element that is repeated $n$ times.

---

## Key Observations
1.  **Frequency Rule:** Since there are $2n$ slots and $n+1$ unique elements, if one element takes $n$ slots, the remaining $n$ slots must be filled by the other $n$ unique elements. This means **every other element appears exactly once**,.
2.  **The Goal:** We simply need to find the only element that appears more than once.

---

## Approaches

### 1. Hash Map / Set Approach
*   **Method:** Use an `unordered_map` to count frequencies or an `unordered_set` to track seen elements,.
*   **Logic:** As you iterate, if you encounter an element already present in the set, that is your answer.
*   **Time Complexity:** $O(n)$.
*   **Space Complexity:** $O(n)$ to store elements in the set.

### 2. Frequency Array (Constraint-Based)
*   **Method:** Since constraints show `nums[i]` max value is $10^4$, use a fixed-size array of $10^4 + 1$.
*   **Logic:** Use the element's value as the index of the array to store its count,.
*   **Complexity:** $O(n)$ time and $O(1)$ space (though it uses a constant $10,001$ size array),.

### 3. Optimized Distance Observation ($O(1)$ Space)
*   **Logic:** Because the repeating element $x$ occupies 50% of the array, it is mathematically guaranteed to be close to itself,.
*   **The Check:** For any index $i$, check if:
    *   `nums[i] == nums[i-1]`
    *   `nums[i] == nums[i-2]`
*   **Corner Case:** In a size 4 array where the repeating elements are at the extreme ends (e.g., `[x, a, b, x]`), the distance is 3. In this specific case, the loop might not catch the repetition,.
*   **Fix:** If the loop finishes without returning, return the last element or compare the first and last elements,.

---

## Final Optimized Code Structure (C++)
```cpp
class Solution {
public:
    int repeatedNTimes(vector<int>& nums) {
        int n = nums.size();
        for(int i = 2; i < n; i++) {
            // Check adjacent or one element apart,
            if(nums[i] == nums[i-1] || nums[i] == nums[i-2])
                return nums[i];
        }
        // Handle corner case for size 4 arrays like [x, a, b, x],
        return nums == nums ? nums : nums.back();
    }
};
```

---

## Complexity Analysis
*   **Time Complexity:** **$O(n)$** because we traverse the array once,.
*   **Space Complexity:** **$O(1)$** as we use no extra data structures in the optimized approach.

