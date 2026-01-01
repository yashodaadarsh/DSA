# Largest Triangle Area (LeetCode 812)

This README provides a summary of the approach and formulas discussed for solving LeetCode Problem 812, "Largest Triangle Area."

## Problem Description

The problem, marked as Easy on LeetCode, requires finding the **largest triangle area**.

*   **Input:** An array of points on the XY plane, where `points[i] = [xi, yi]`.
*   **Output:** The area of the largest triangle that can be formed by any three different points.
*   **Acceptance Criteria:** Answers within $\mathbf{10^{-5}}$ of the actual answer will be accepted.

## General Approach (Brute Force)

To determine the largest area, there is no way to predict which three coordinates will maximize the triangle size. Therefore, the recommended method is a brute-force approach.

1.  **Iteration:** Iterate through all possible combinations of three distinct points (i, j, k) using a **triple for loop**.
    *   The second loop index (`j`) starts from `i + 1`.
    *   The third loop index (`k`) starts from `j + 1`.
2.  **Coordinate Assignment:** Assign the coordinates from the points at indices i, j, and k to $(x_1, y_1)$, $(x_2, y_2)$, and $(x_3, y_3)$ respectively.
3.  **Area Calculation:** Calculate the area using a reliable formula (Heron's or Shoelace).
4.  **Maximization:** Continuously update a `max_area` variable with the maximum area found across all combinations.

### Time Complexity

The time complexity for checking all combinations is $\mathbf{O(N^3)}$ due to the necessity of three nested for loops. Operations performed inside the loop are constant time.

## Methods for Calculating Triangle Area

Two methods for calculating the area given the three vertices are detailed: Heron's Formula and the Shoelace Formula.

### 1. Heron's Formula

Heron's formula is utilized when the lengths of the three sides ($a, b, c$) of the triangle are known or calculable.

#### A. Calculating Side Lengths ($a, b, c$)

The length of each side is calculated using the distance formula. For side $a$:
$$a = \sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2} \text{}$$

**C++ Utility Function (`hypot`)**

Instead of manually calculating the square root of the sum of squared differences, the C++ utility function **`hypot`** can be used. This function simplifies the distance calculation:
$$\text{Side Length} = \text{hypot}(\Delta x, \Delta y) \text{}$$
For example, side $a$ can be calculated using `hypot(x2 - x1, y2 - y1)`. Users of Java can look for `Math.hypot`.

#### B. Calculating Semi-Perimeter ($s$)

The semi-perimeter is calculated by summing the three sides and dividing by two:
$$s = \frac{a + b + c}{2} \text{}$$
To maintain precision when dealing with double-precision floating-point numbers, dividing by two can be achieved by multiplying the perimeter by $\mathbf{0.5}$.

#### C. Calculating Area (Heron)

The area is calculated using the following formula:
$$\text{Area} = \sqrt{s(s - a)(s - b)(s - c)} \text{}$$

### 2. Shoelace Formula

The Shoelace formula is presented as an alternative method for calculating the area directly from the coordinates. It is simpler than Heron's formula because it avoids needing to calculate side lengths, semi-perimeter, or square roots. This formula is often known by those who prepared for exams like IIT JEE.

The area of the triangle is calculated as half the absolute value of a specific summation involving the coordinates:

$$\text{Area} = \mathbf{0.5} \times \left| x_1(y_2 - y_3) + x_2(y_3 - y_1) + x_3(y_1 - y_2) \right| \text{}$$
