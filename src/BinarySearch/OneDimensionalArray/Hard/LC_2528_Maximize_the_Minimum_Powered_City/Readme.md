

# Maximize the Minimum Powered City | Deep Dive | Leetcode 2528

## 📖 Problem Description

This problem involves maximizing the minimum power among all cities by strategically building additional power stations.

### Given:

1. A zero-indexed integer array, `stations`, of length **N**, where `stations[i]` represents the number of power stations in the *i<sup>th</sup>* city.
2. A fixed range, **R** (Radius), for power distribution.

    * A power station at city `i` provides power to all cities `j` such that `|i - j| ≤ R`.
3. An integer **K**, representing the number of additional power stations authorized for building.

    * These `K` stations can be built in any city and have the same range `R` as pre-existing stations.

### Goal:

Return the **maximum possible minimum power of a city**.
The power of a city is defined as the total number of power stations providing power to it.
The `K` additional stations must be built optimally to achieve this maximum minimum power.

---

## 🧠 Approach: Binary Search on Answer

The structure of the problem — “Maximize the Minimum” — strongly suggests using **Binary Search on the Answer**.

### 1. Binary Search Range (Low and High)

The search space is the potential minimum power value we are trying to maximize.

* **Low (Left Bound):** The smallest possible minimum power is the minimum element present in the initial `stations` array.
* **High (Right Bound):** The maximum possible power a single city could achieve is the sum of all existing stations plus the `K` additional stations.

```
High = Sum(stations) + K
```

---

### 2. The Check Function (`check(mid, R, K)`)

The core of the solution lies in the `check` function, which determines if a given minimum power `mid` is achievable using at most `K` extra stations.

If the check returns `true`, we store `mid` as a potential result and try to maximize it by searching higher (`Left = Mid + 1`).
If it returns `false`, we must search lower (`Right = Mid - 1`).

---

## ⚙️ Key Technique: Difference Array (DAT)

The major challenge is efficiently calculating and updating the power of cities when new stations are added.
A naive approach involving iterating through the range `R` for every update is too slow, leading to a complexity of `O(N * 2R)` inside the check function, which results in a **TLE**.

To handle range updates efficiently, the **Difference Array Technique (DAT)** is used.

---

### Step A: Initial Power Calculation using DAT

The initial power for all cities is calculated efficiently using the difference array:

1. Initialize a difference array `diff_array` of size `N`.
2. For each station `i` with value `S_i`:

    * The power contributes to the range `[max(0, i - R), i + R]`.
    * Add `S_i` at the start of the range:

      ```
      diff_array[max(0, i - R)] += S_i
      ```
    * Subtract `S_i` one index past the end of the range:

      ```
      diff_array[i + R + 1] -= S_i   (if i + R + 1 < N)
      ```
3. The cumulative sum of the `diff_array` gives the actual power for each city.

---

### Step B: Optimal Placement of `K` Stations (Greedy Strategy)

Inside the `check` function, we iterate through the cities and calculate the current power using the cumulative sum of a temporary difference array `temp_diff_array`.

1. **Calculate Need:**
   For the current city `i`, if the current cumulative power is less than the required minimum `mid`, calculate:

   ```
   need = mid - current_power
   ```

2. **Check Feasibility:**
   If `need > K`, the `mid` value is not achievable — return `false`.

3. **Greedy Update:**
   If we have enough remaining stations (`K >= need`):

    * Deduct `need` from `K`.
    * Place these new stations optimally at position `i + R` to cover the maximum range.
    * The installation covers `[i, i + 2R]`.
    * Apply DAT update:

      ```
      temp_diff_array[i + 2R + 1] -= need   (if i + 2R + 1 < N)
      ```
    * Increment the cumulative sum by `need`.

This ensures we never re-check previous cities unnecessarily and maintain an efficient update using the difference array.

---

## ⏱️ Complexity Analysis

| Measure              | Complexity                      |
| -------------------- | ------------------------------- |
| **Time Complexity**  | `O(N * log(Sum(stations) + K))` |
| **Space Complexity** | `O(N)`                          |

* Binary search runs in `O(log R)` iterations.
* Each check runs in `O(N)` due to efficient range updates using DAT.

---

## 💻 Code (C++)

```cpp
class Solution {
    using ll = long long;
public:
    long long maxPower(vector<int>& stations, int r, int k) {
        int n = stations.size();
        ll ans = 0;
        ll low = *min_element(stations.begin(), stations.end());
        ll high = accumulate(stations.begin(), stations.end(), 0LL) + k;

        while (low <= high) {
            ll mid = (high - low) / 2 + low;
            if (check(mid, stations, r, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

private:
    bool check(ll tar, vector<int> stations, int r, int k) {
        int n = stations.size();
        vector<ll> diff(n, 0);

        for (int i = 0; i < n; i++) {
            diff[max(i - r, 0)] += stations[i];
            if (i + r + 1 < n)
                diff[i + r + 1] -= stations[i];
        }

        ll sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (sum < tar) {
                ll need = tar - sum;
                if (need > k) return false;
                k -= need;
                if (i + 2 * r + 1 < n)
                    diff[i + 2 * r + 1] -= need;
                sum += need;
            }
        }
        return true;
    }
};
```

---

## 💻 Code (Java)

```java
import java.util.Arrays;

class Solution {
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long ans = 0;
        long low = Arrays.stream(stations).min().getAsInt();
        long high = Arrays.stream(stations).asLongStream().sum() + k;

        while (low <= high) {
            long mid = (high - low) / 2 + low;
            if (check(mid, stations, r, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(long tar, int[] stations, int r, long k) {
        int n = stations.length;
        long[] diff = new long[n];

        for (int i = 0; i < n; i++) {
            diff[Math.max(0, i - r)] += stations[i];
            if (i + r + 1 < n)
                diff[i + r + 1] -= stations[i];
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (sum < tar) {
                long need = tar - sum;
                if (need > k) return false;
                k -= need;
                sum += need;
                if (i + 2 * r + 1 < n)
                    diff[i + 2 * r + 1] -= need;
            }
        }
        return true;
    }
}
```

---

