# Count Collisions on a Road

## Context and Motivation

This problem was encountered during an online assessment, though in that instance, the moving objects were referred to as **robots** instead of cars.


## Problem Description

The problem involves $N$ cars situated on an infinitely long road, numbered from 0 to $N-1$ from left to right.

You are given a zero-indexed string, `directions`, of length $N$. The character at `directions[i]` indicates the movement state of the $i^{th}$ car:
*   **L:** Moving towards the Left.
*   **R:** Moving towards the Right.
*   **S:** Staying/Stationary.

**Important Rule:** Each moving car has the **same speed**. Cars cannot change their state or direction of motion.

## Collision Rules

The total number of collisions is calculated based on these scenarios:

1.  **Two Cars Moving in Opposite Directions (R and L):**
    *   Collision count increases by **two (2)**.
    *   After the collision, the cars involved can no longer move and will stay at the point where they collided.

2.  **Moving Car Collides with a Stationary Car (R or L hitting S):**
    *   Collision count increases by **one (1)**.
    *   After the collision, the moving car becomes stationary alongside the originally stationary car.

## Thought Process and Algorithm

The crucial observation derived from the problem constraints is that since all moving cars have the same constant speed:
*   Cars moving in the **same direction** will **never collide**. The distance between them will remain constant.

This observation forms the basis of the solution.

### Step 1: Skip Non-Colliding Boundary Cars

Since the road is infinitely long, cars moving away from the main group at the boundaries will never be involved in a collision. We use two pointers, $i$ and $j$, to define the necessary segment for processing.

1.  **Left Boundary Skipper ($i$):** Starting from $i=0$, we **skip all consecutive 'L' cars**. These cars are moving left and will never encounter any obstacle to their left. The pointer $i$ moves forward until it finds a character that is not 'L'.

2.  **Right Boundary Skipper ($j$):** Starting from $j=N-1$, we **skip all consecutive 'R' cars**. These cars are moving right and will never encounter any obstacle to their right. The pointer $j$ moves backward until it finds a character that is not 'R'.

Collision calculations are only necessary for the segment between $i$ and $j$ (inclusive).

### Step 2: Linear Iteration and Collision Counting

We iterate linearly from $i$ to $j$ (while $i \le j$). The core insight is that **any car that is moving ('L' or 'R') within this constrained boundary ($i$ to $j$) is guaranteed to collide**.

We count collisions by focusing on the **moving status** of the car at the current index $i$:

*   If `directions[i]` is not 'S' (meaning it is either 'L' or 'R'), we increment the total collision count by **one (1)**.
*   The stationary cars ('S') are effectively skipped in the counting logic.

#### Why increment by +1 for every moving car?

This simplified approach correctly accounts for all collision types:

1.  **R colliding with L (Opposite Direction Collision = 2 counts):**
    *   When the 'R' car is processed, count increments by **+1**.
    *   When the 'L' car is processed, count increments by **+1**.
    *   Total count added: $1 + 1 = 2$, satisfying the rule for opposite-direction collisions.

2.  **Moving Car colliding with S (Collision = 1 count):**
    *   If an 'R' car hits a stationary car ('S'), the 'R' car is processed (at some previous index), incrementing the count by **+1**. The stationary car itself is skipped from the counting mechanism.
    *   If an 'L' car hits a stationary car ('S'), the 'L' car is processed (at some later index), incrementing the count by **+1**.

This single iteration logic successfully covers both scenarios by treating every moving car within the effective collision range as accounting for one unit of collision it will be involved in.

## Complexity Analysis

The solution involves a linear iteration of the input string.

| Metric | Complexity | Rationale |
| :--- | :--- | :--- |
| **Time Complexity** | **O(N)** | Every character in the `directions` string is visited at most once by the $i$ or $j$ pointers. |
| **Space Complexity** | **O(1)** | No extra space, such as a stack or supplementary data structure, is used. |