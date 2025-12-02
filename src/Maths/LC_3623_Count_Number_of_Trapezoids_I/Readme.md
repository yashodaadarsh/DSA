
# Leetcode 3623: Count Number of Trapezoids I

**Video:** Count Number of Trapezoids I | Simplest Explanation | Detailed | Leetcode 3623 | codestorywithMIK [Transcript Context]
**Topic Context:** This problem is addressed as part of a proposed "One Month One Topic Challenge" initiated in December.

## Problem Description

You are given a 2D integer array `points` where `points[i] = [xi, yi]` represents the coordinates of the $i^{th}$ point in the Cartesian plane.

**Definition of a Horizontal Trapezoid:**
A horizontal trapezoid is defined as a convex quadrilateral with **at least one pair of horizontal sides**. Note that two lines are considered parallel if and only if they have the same slope.

The goal is to return the number of **unique horizontal trapezoids** that can be formed by choosing any four distinct points from the given set of points.

**Constraint:**
Since the answer may be very large, the result should be returned modulo $10^9 + 7$.

## Solution Approach

The key insight for solving this problem is recognizing that a horizontal side must be parallel to the x-axis. This means that any points forming a horizontal line must share a **constant y-coordinate**.

A trapezoid requires two pairs of points forming two parallel horizontal sides.

### 1. Data Structure and Point Grouping

1.  **Group Points by Y-Axis:** We must first determine, for a specific y-coordinate, how many points lie on that horizontal line. A `Map` (specifically, an unordered map in the implementation) is the best data structure for this.
    *   **Key:** The y-axis value (the constant y-coordinate).
    *   **Value:** The count of points available on that horizontal line.
    *   *Implementation detail: Iterate through the input `points` array and increment the count for `point` (the y-coordinate) in the map*.

### 2. Calculating Horizontal Lines ($nC2$)

A horizontal line segment requires two points. If a given constant y-coordinate has $C$ points (where $C$ is the count from the map), the total number of unique horizontal line segments that can be formed at that y-level is calculated using the combination formula $C$ choose 2 ($C_C2$):

$$
\text{Horizontal Lines} = \frac{C \times (C - 1)}{2} \quad \text{Formula for } C_C2
$$

### 3. Calculating Trapezoids and Avoiding Duplicates

A horizontal trapezoid is formed by pairing a horizontal line from one y-level with a horizontal line from a different y-level.

To avoid duplicating counts (e.g., counting a trapezoid formed by Line 1 on $Y_1$ and Line 2 on $Y_2$, and then counting it again later as Line 2 on $Y_2$ and Line 1 on $Y_1$), we iterate through the map of y-counts and maintain a running sum of all **previous** horizontal lines calculated.

**Iteration Steps:**

1.  **Current Horizontal Lines:** For the current y-level, calculate the number of possible horizontal line segments (`Current_H_Lines`) using the $C_C2$ formula.
2.  **Calculate Trapezoids:** The number of new trapezoids formed using the current y-level is:
    $$\text{Result} += \text{Current\_H\_Lines} \times \text{Total\_Previous\_H\_Lines}$$
    This multiplies the lines available at the current level by the total cumulative lines available from all previous, lower y-levels.
3.  **Update Previous Sum:** Before proceeding to the next y-level, update the running sum:
    $$\text{Total\_Previous\_H\_Lines} += \text{Current\_H\_Lines}$$
    This maintains the count of all horizontal line segments seen so far.

By iterating through the y-coordinates and accumulating the sum of previous horizontal lines, the total number of unique trapezoids is calculated.

## Complexity Analysis

*   **Time Complexity:** **O(N)**, where N is the number of points. This is because we iterate through the input points once to populate the map, and then iterate through the map (which holds at most N distinct y-coordinates). The solution is completed in linear time.
*   **Space Complexity:** **O(N)**, as the map is used to store the count of points corresponding to each distinct y-coordinate.
```