# LeetCode 2106: Maximum Fruits Harvested After at Most K Steps

## Problem Description
You are given a 2D integer array `fruits`, where `fruits[i] = [position, amount]`, indicating that `amount` of fruits are available at `position` on an infinite x-axis. The `fruits` array is **already sorted by position in ascending order**, and each position is unique.

You are also given an `startPosition` and an integer `k`. Initially, you are at `startPosition`.
From any position, you can move either to the left or to the right, and it takes one step to move one unit on the x-axis. You can walk **at most `k` steps** in total.
For every position you reach, you harvest all the fruits at that position, and the fruits will disappear.

The goal is to **return the maximum total number of fruits you can harvest**.

**Example:**
If `fruits = [,,]`, `startPosition = 5`, `k = 4`.
The answer is `9`. This is achieved by moving right: one step to position 6 (3 fruits), then two more steps to position 8 (6 fruits). Total fruits: 3 + 6 = 9. Total steps: (6-5) + (8-6) = 1 + 2 = 3 <= 4.

## Key Observations & Approach

The most crucial observation in this problem is related to the movement pattern:
*   **Avoid zig-zagging**: You should **never** go left, then right, and then **back to left again**. Similarly, you should **never** go right, then left, and then **back to right again**. This is because any steps taken to re-traverse an already visited (and now empty) path are wasted. If you needed to pick up fruits in that segment, you should have picked them up when you first traversed it.

This observation leads to **two main possible movement patterns** to maximize fruit collection:
1.  **Go Left, then turn and go Right**: Move some distance `d` to the left from `startPosition`, then turn around and move right, potentially past `startPosition`.
2.  **Go Right, then turn and go Left**: Move some distance `d` to the right from `startPosition`, then turn around and move left, potentially past `startPosition`.

The maximum distance `d` you can initially move in one direction (left or right from `startPosition`) and still have enough steps to turn around and move to the other side is such that `k - 2 * d >= 0`, which simplifies to `d <= k / 2`.

To efficiently calculate the sum of fruits in a given range `[i, j]`, we can use **prefix sums**. Since the `fruits` array is sorted by position, we can use **binary search (specifically `lower_bound` and `upper_bound`)** on the `positions` array to find the indices corresponding to our calculated `i` and `j` range.

## Detailed Logic

We iterate through all possible initial distances `d` from `0` up to `k/2`. For each `d`, we calculate the reachable `[i, j]` range for both main cases and update our maximum fruits.

First, pre-process the `fruits` array:
*   Create a `positions` array containing only the `position` values from `fruits`. This will also be sorted.
*   Create a `prefix_sum` array where `prefix_sum[x]` stores the total fruits up to `fruits[x]` (inclusive).

Initialize `maxFruits = 0`.

Loop `d` from `0` to `k/2` (inclusive):

### Case 1: Move `d` steps to the Left, then Right
1.  **Calculate Leftmost Position (`i`):** You start at `startPosition` and move `d` steps to the left.
    `i = startPosition - d`.
2.  **Calculate Rightmost Position (`j`):**
    *   You spent `d` steps to reach `i`.
    *   To turn around and move right, you first need to travel `d` steps back to `startPosition`.
    *   Total steps used so far: `d + d = 2 * d`.
    *   Remaining steps: `k - 2 * d`.
    *   From `startPosition`, you can move these `(k - 2 * d)` steps to the right.
    *   `j = startPosition + (k - 2 * d)`.
3.  **Find Indices in `fruits` array:**
    *   Use `lower_bound` on the `positions` array to find `left_idx`: the index of the first fruit position `>= i`.
    *   Use `upper_bound` on the `positions` array to find `temp_right_idx`: the index of the first fruit position `> j`. Then, `right_idx = temp_right_idx - 1`. This ensures we only include fruits up to position `j`.
4.  **Calculate Fruits in Range:**
    *   If `left_idx <= right_idx` (valid range), calculate `current_fruits = prefix_sum[right_idx] - prefix_sum[left_idx - 1]` (handle `left_idx - 1 < 0` by using 0 instead of `prefix_sum[-1]`).
5.  **Update `maxFruits`:** `maxFruits = max(maxFruits, current_fruits)`.

### Case 2: Move `d` steps to the Right, then Left
1.  **Calculate Rightmost Position (`j`):** You start at `startPosition` and move `d` steps to the right.
    `j = startPosition + d`.
2.  **Calculate Leftmost Position (`i`):**
    *   Similar to Case 1, `2 * d` steps are used to go right and come back to `startPosition`.
    *   Remaining steps: `k - 2 * d`.
    *   From `startPosition`, you can move these `(k - 2 * d)` steps to the left.
    *   `i = startPosition - (k - 2 * d)`.
3.  **Find Indices in `fruits` array:** (Same as Case 1)
    *   Use `lower_bound` on `positions` for `i` to find `left_idx`.
    *   Use `upper_bound` on `positions` for `j` (and then subtract 1 from index) to find `right_idx`.
4.  **Calculate Fruits in Range:** (Same as Case 1)
    *   If `left_idx <= right_idx`, calculate `current_fruits` using prefix sums.
5.  **Update `maxFruits`:** `maxFruits = max(maxFruits, current_fruits)`.

Finally, **return `maxFruits`**.

## Time and Space Complexity
*   **Time Complexity**: `O(N + K log N)`
    *   `O(N)` for pre-calculating positions and prefix sums.
    *   The loop runs `K/2` times (`K` is `k`).
    *   Inside the loop, `lower_bound` and `upper_bound` operations take `O(log N)` time (where `N` is the number of fruit positions).
*   **Space Complexity**: `O(N)`
    *   For storing the `positions` array and the `prefix_sum` array.

## Personal Motivation
"No one is coming and do things for you. You, me, or all of us are on our own. We have to do our own thing. Just accept it and be ready to face whatever comes in your life. You have to do it on your own and you've got this. Just believe in yourself."
