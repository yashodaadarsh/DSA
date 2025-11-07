
# Maximize the Minimum Powered City | Deep Dive | Leetcode 2528

## 📖 Problem Description

This problem involves maximizing the minimum power among all cities by strategically building additional power stations.

### Given:
1.  A zero-indexed integer array, `stations`, of length $N$, where `stations[i]` represents the number of power stations in the $i^{th}$ city.
2.  A fixed range, $R$ (Radius), for power distribution.
    *   A power station at city $i$ provides power to all cities $j$ such that the absolute difference $|i - j| \le R$.
3.  An integer $K$, representing the number of additional power stations authorized for building.
    *   These $K$ stations can be built in any city and have the same range $R$ as pre-existing stations.

### Goal:
Return the **maximum possible minimum power of a city**. The power of a city is defined as the total number of power stations providing power to it. The $K$ additional stations must be built optimally to achieve this maximum minimum power.

## 🧠 Approach: Binary Search on Answer

The structure of the problem—"Maximize the Minimum"—strongly suggests using **Binary Search on the Answer**.

### 1. Binary Search Range (Low and High)

The search space is the potential minimum power value we are trying to maximize.

*   **Low (Left Bound):** The smallest possible minimum power is the minimum element present in the initial `stations` array.
*   **High (Right Bound):** The maximum possible power a single city could achieve is the sum of all existing stations plus the $K$ additional stations.
    *   $High = \text{Sum}(\text{stations}) + K$.

### 2. The Check Function (`check(mid, R, K)`)

The core of the solution lies in the `check` function, which determines if a given minimum power `mid` is achievable using at most $K$ extra stations.

If the check returns `True`, we store `mid` as a potential result and try to maximize it by searching higher ($\text{Left} = \text{Mid} + 1$). If it returns `False`, we must search lower ($\text{Right} = \text{Mid} - 1$).

## ⚙️ Key Technique: Difference Array (DAT)

The major challenge is efficiently calculating and updating the power of cities when new stations are added. A naive approach involving iterating through the range $R$ for every update is too slow, leading to a complexity of $O(N \cdot 2R)$ inside the check function, which results in a Time Limit Exceeded (TLE) error.

To handle simple range updates optimally, the **Difference Array Technique (DAT)** is utilized.

### Step A: Initial Power Calculation using DAT

The initial power for all cities is calculated efficiently using DAT:

1.  Initialize a difference array (`diff_array`).
2.  For each station $i$ with value $S_i$:
    *   The power contributes to the range $[\max(0, i - R), i + R]$.
    *   In the difference array, add $S_i$ at the start of the range: $\text{diff\_array}[\max(0, i - R)] += S_i$.
    *   Subtract $S_i$ one index past the end of the range: $\text{diff\_array}[i + R + 1] -= S_i$ (if $i + R + 1 < N$).
3.  The cumulative sum of the `diff_array` provides the actual power value for each city.

### Step B: Optimal Placement of $K$ Stations (Greedy Strategy)

Inside the `check` function, we iterate through the cities and calculate the current power using the cumulative sum of a temporary difference array (`temp_diff_array`).

1.  **Calculate Need:** For the current city $i$, if the current cumulative power is less than the required minimum `mid`, calculate the required additional power ($\text{Need} = \text{Mid} - \text{Current Power}$).
2.  **Check Feasibility:** If the $\text{Need}$ is greater than the remaining $K$, the `mid` is not achievable, and we immediately return `False`.
3.  **Greedy Update:** If the $\text{Need}$ is available ($K \ge \text{Need}$), we use the stations optimally.
    *   We update $K$: $K = K - \text{Need}$.
    *   To maximize the range covered, the new stations (equal to $\text{Need}$) are hypothetically installed at the city that creates the largest impact, which is at index $i + R$.
    *   This installation covers the range starting at $i$ (the city that currently needs power) up to the rightmost index, which is $i + 2R$.
    *   Since we are adding $\text{Need}$ to the entire range $[i, i + 2R]$, DAT is applied:
        *   Implicitly add $\text{Need}$ to the current city's power (by updating the cumulative sum variable).
        *   Subtract $\text{Need}$ at the index immediately following the covered range: $\text{temp\_diff\_array}[i + 2R + 1] -= \text{Need}$ (provided this index is less than $N$).

By using DAT, we avoid expensive range traversal and update operations inside the check loop, keeping the complexity optimal.

## ⏱️ Complexity Analysis

The complexity is determined by the binary search steps multiplied by the cost of the `check` function.

*   **Time Complexity:** $\mathbf{O(N \cdot \log(\text{Sum of Stations} + K))}$.
    *   The binary search space spans from $L$ to $R$, taking $O(\log R)$ steps.
    *   Crucially, the `check` function runs in $\mathbf{O(N)}$ time due to the efficient $O(1)$ range updates provided by the Difference Array Technique.

*   **Space Complexity:** $\mathbf{O(N)}$ to store the difference array.