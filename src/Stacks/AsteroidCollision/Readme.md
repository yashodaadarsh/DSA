# Asteroid Collisions

## Introduction
This repository addresses the "Asteroid Collisions" problem, which involves determining the final state of a set of asteroids after all possible collisions have occurred. This problem utilizes the Last-In, First-Out (LIFO) principle, making a stack or list data structure the ideal approach for resolution.

## Problem Statement
You are provided with an array representing a sequence of asteroids. Each asteroid has a size, represented by the absolute value of the number at its index.

*   **Positive Asteroids:** Move in the rightward direction.
*   **Negative Asteroids:** Move in the leftward direction.

The objective is to return the final state of all asteroids after all resulting collisions.

## Collision Rules
Collisions occur only when one asteroid is traveling in the right direction (positive) and another is traveling in the leftward direction (negative).

1.  **Unequal Size:** When two asteroids collide, the one with the **lesser absolute value will be destroyed**.
2.  **Equal Size:** If two asteroids are of **equal absolute value, both of them will get destroyed**,.
3.  **Same Direction:** Asteroids traveling in the same direction will keep traversing without any collision,.

## Algorithm Approach (Using List/Vector)
The key insight for solving this problem is recognizing that when a collision occurs (upon encountering a negative asteroid), we need to compare it with the *last* asteroid that was seen and potentially survived (the previous last),. This requirement suggests using a LIFO data structure like a stack.

However, to avoid an additional step of reversing the elements at the end to get the correct output order (e.g., `4, 17` instead of `17, 4`), it is **better to use a dynamic list or vector** (referred to as `St` in the sources) that supports LIFO operations (like `push back` and `pop back`),,.

### Pseudocode Logic Breakdown
The algorithm iterates through the array of asteroids, processing each element (`array[i]`):

1.  **Positive Asteroid Encounter:**
    *   If `array[i]` is positive (`> 0`), simply **insert it into the list (St)**,.

2.  **Negative Asteroid Encounter:**
    *   If `array[i]` is negative (leftward traversal), collisions must be checked against the previous elements stored in the list,.
    *   **Collision Loop:** While the list is non-empty AND the top/back element of the list (`St.back()`) is positive AND the list's top element's size is smaller than the current negative asteroid's size (i.e., `St.back() < absolute(array[i])`), the smaller asteroid gets destroyed, so **pop the element from the list** (`St.pop`),. This while loop continues until the collision stops.
    *   **Equal Destruction Check:** After the loop, if the list is non-empty AND the size of the list's top element is equal to the size of the current negative asteroid (`St.back() == absolute(array[i])`), **both are destroyed**, so pop the element from the list,.
    *   **Negative Insertion Check:** The negative asteroid (`array[i]`) is only inserted into the list if one of two conditions is met:
        *   The **list is empty**.
        *   OR the **top element of the list is also negative** (`St.back() < 0`), as they are traveling in the same direction (leftward) and will never collide,.

3.  **Return:** Once traversal is complete, **return the list (St)**, as it contains the final state of the surviving asteroids in the correct order,.

## Complexity Analysis

| Metric | Complexity | Rationale |
| :--- | :--- | :--- |
| **Time Complexity** | O(N) | Every element is pushed onto the list at most once, and although a while loop handles collisions, the maximum number of times an element can be popped out across the entire traversal is also N,. The total complexity is O(N + N), which simplifies to O(N). |
| **Space Complexity** | O(N) | An external data structure (the list/vector `St`) is used to store elements, which may hold up to N elements in the worst case. |