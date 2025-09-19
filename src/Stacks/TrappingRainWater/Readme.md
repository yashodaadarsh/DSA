
# L8. Trapping Rainwater (2 Approaches) | Stack and Queue Playlist


## 1. Problem Description and Core Intuition

The input is an array of non-negative integers, where each integer represents the height of a building. The task is to return the total units of water that will be stored (water logged) when it rains.

### Conditions for Water Storage

Water can only be logged in a space if there is a **taller building on the left AND a taller building on the right**.

### Calculation Formula

To find the total trapped water, we sum the water stored on the terrace of each individual building. For any given building at index $I$, the units of water stored are calculated using the following formula:

$$
\text{Water}_I = \min(\text{Left Max}, \text{Right Max}) - \text{array}[I]
$$

Here, `Left Max` is the maximum height found to the left of the current building, and `Right Max` is the maximum height found to the right. The water level is limited by the minimum of the bounding heights (Left Max and Right Max).

## 2. Approach 1: Pre-computing Maxima (O(N) Time, O(N) Space)

This approach focuses on efficiently determining the `Left Max` and `Right Max` for every index using auxiliary arrays, thereby avoiding the $O(N^2)$ brute force complexity.

### Implementation using Prefix and Suffix Max

1.  **Prefix Max Array (Left Max):**
    *   A `Prefix Max` array of size $N$ is computed by iterating from left to right (0 to $N-1$).
    *   `Prefix Max[I]` stores the maximum height encountered up to and including index $I$.
    *   The first element is `array`. Subsequent elements are the maximum of the previous `Prefix Max[I-1]` and the current `array[I]`.

2.  **Suffix Max Array (Right Max):**
    *   A `Suffix Max` array of size $N$ is computed by iterating from right to left ($N-1$ to 0).
    *   `Suffix Max[I]` stores the maximum height encountered from index $I$ up to the end of the array.

3.  **Total Calculation:**
    *   Traverse the original array again (0 to $N-1$).
    *   For each index $I$, the Left Max is `Prefix Max[I]`, and the Right Max is `Suffix Max[I]`.
    *   Apply the water calculation formula and sum the results to `Total`.

### Complexity Analysis (Approach 1)

*   **Time Complexity:** $O(N)$. Generating Prefix Max and Suffix Max takes $O(2N)$, and computing the total takes an additional $O(N)$, resulting in an overall complexity of $O(N)$.
*   **Space Complexity:** $O(N)$. This is due to using two auxiliary arrays (Prefix Max and Suffix Max).

### Code
````java
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] lh = new int[n];
        int[] rh = new int[n];
        int max = 0;
        for( int i = 0 ; i < n ; i++ ){
            if( height[i] > max )   max = height[i];
            lh[i] = max;
        }
        max = 0;
        for( int i = n-1 ; i >= 0 ; i-- ){
            if( height[i] > max )   max = height[i];
            rh[i] = max;
        }
        
        int ans = 0;

        for( int i = 0 ; i < n ; i++ ){
            ans += Math.min(lh[i] , rh[i]) - height[i];
        }
        return ans;
    }
}
````

## 3. Approach 2: Optimal Two Pointers (O(N) Time, O(1) Space)

The previous solution's $O(N)$ space complexity is optimized to $O(1)$ by using a two-pointer approach, avoiding the need for pre-computed arrays.

### Core Optimization Intuition

At any point, we only need the **smaller one** of the `Left Max` or `Right Max` to determine the water level, because water storage is limited by the shorter boundary.

### Implementation Details

1.  **Initialization:**
    *   Initialize pointers: `L = 0` (left pointer) and `R = N - 1` (right pointer).
    *   Initialize maxima and total: `leftMax = 0`, `rightMax = 0`, `total = 0`.

2.  **Two-Way Traversal:** The process iterates while `L` is less than `R` (`L < R`). We always work on the pointer that corresponds to the smaller current building height (`array[L]` or `array[R]`), ensuring we are always dealing with the limited boundary.

3.  **Handling the Left Pointer (if `array[L] <= array[R]`):**
    *   When working on $L$, we are certain that a building of height at least `array[R]` (which is `>= array[L]`) exists on the right side.
    *   If `leftMax` is greater than `array[L]`, water is trapped: `total += leftMax - array[L]`.
    *   Otherwise, update the boundary: `leftMax = array[L]`.
    *   Move the pointer: `L = L + 1`.

4.  **Handling the Right Pointer (Else, if `array[R] < array[L]`):**
    *   When working on $R$, we are certain that a building of height at least `array[L]` exists on the left side.
    *   If `rightMax` is greater than `array[R]`, water is trapped: `total += rightMax - array[R]`.
    *   Otherwise, update the boundary: `rightMax = array[R]`.
    *   Move the pointer: `R = R - 1`.

### Complexity Analysis (Approach 2)

*   **Time Complexity:** $O(N)$. The left and right pointers move inward, touching every element exactly once.
*   **Space Complexity:** $O(1)$. No external data structures or arrays are used. This is considered the most optimal solution.


### Optimal Code
```java
class Solution {
    public int trap(int[] height) {
        int total = 0;
        int n = height.length;
        int l = 0 , r = n-1 , lMax = 0 , rMax = 0;
        while( l <= r ){
            if( height[l] <= height[r] ){
                if( lMax > height[l] ){
                    total += lMax - height[l];
                }
                lMax = Math.max( lMax , height[l] );
                l++;
            }
            else{
                if( rMax > height[r] ){
                    total += rMax - height[r];
                }
                rMax = Math.max( rMax , height[r] );
                r--;
            }
        }
        return total;
    }
}
```