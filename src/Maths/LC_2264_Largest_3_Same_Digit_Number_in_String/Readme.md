
# Largest 3-Same-Digit Number in String (Leetcode 2264)

## Problem Description

You are given a string `num` which represents a large integer. The goal is to find the **"largest good integer"** within this string.

A "good integer" is defined by the following conditions:
*   It must be a **substring of `num`**.
*   Its **length must be exactly three**.
*   It must consist of **only one unique digit**, meaning all three characters in the substring are identical (e.g., "777", "000", "999").

The problem requires you to return the **maximum "good integer" found as a string**. If no such good integer exists in the input string, an **empty string** should be returned.

## Example

Let's consider the input string `num = "1377767779998"`.

1.  **Iterate and check for 3-same-digit substrings:**
    *   Starting from index 2, we check `num`, `num`, `num`. In this case, '7', '3', '1'. These are not all same.
    *   Moving `i` forward, at a point `num[i]`, `num[i-1]`, `num[i-2]` become '7', '7', '7'. This is a good integer candidate.
    *   We store the digit '7' as our `max_char` (representing "777").
    *   Continuing the iteration, another good candidate is found: '9', '9', '9'.
    *   Comparing the current digit '9' with the previously stored `max_char` '7', we find that '9' is greater than '7'.
    *   Therefore, `max_char` is updated to '9'.
2.  **No more good integers found** after iterating through the string.
3.  **Final Answer:** The largest good integer is "999" because '9' was the largest digit found among all three-same-digit substrings.

## Approach

The approach involves a straightforward iteration through the input string `num`.

1.  **Initialize `max_char`**: A variable `max_char` (e.g., a character type) is initialized to a default empty or space value. This variable will store the largest single digit of any "good integer" found.
2.  **Iterate through the string**: Start looping from index `i = 2` up to `n-1` (where `n` is the length of `num`). This allows checking `num[i]`, `num[i-1]`, and `num[i-2]` simultaneously.
3.  **Check for "Good Integer" condition**: Inside the loop, at each `i`, verify if `num[i]`, `num[i-1]`, and `num[i-2]` are all equal.
    *   This can be done with a condition like: `(num[i] == num[i-1]) && (num[i] == num[i-2])`.
4.  **Update `max_char`**: If the condition in step 3 is met (a "good integer" is found):
    *   Compare the current digit `num[i]` with the `max_char` found so far.
    *   If `num[i]` is greater than `max_char`, update `max_char` to `num[i]`.
5.  **Construct and Return Result**: After the loop finishes:
    *   **Check `max_char`**: If `max_char` is still its initial empty/space value, it means no "good integer" was found during the iteration. In this case, return an **empty string**.
    *   **Otherwise**: Construct a string consisting of `max_char` repeated three times (e.g., if `max_char` is '7', return "777").

## Implementation Details

### String Construction Examples:

*   **C++**: To create a string of a specific length with repeated characters, you can use the `std::string` constructor:
    ```cpp
    std::string result = std::string(3, max_char); // Creates a string like "999"
    ```
    This will create a string of length 3, where all characters are `max_char`.

*   **Java**: To create a string of a specific length with repeated characters, you can create a character array and then pass it to the `String` constructor:
    ```java
    String result = new String(new char[]{max_char, max_char, max_char}); // Creates a string like "999"
    ```
    This constructs a new `char` array with three instances of `max_char` and then converts it to a `String`.

## Edge Cases

*   **No "Good Integer" Found**: If the `max_char` variable retains its initial empty or space value after the entire string has been processed, it indicates that no three-same-digit substrings were found. In this scenario, the function should return an **empty string**.
```