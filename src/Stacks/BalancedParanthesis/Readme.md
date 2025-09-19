
# L2. Check for Balanced Parentheses | Stack and Queue

This document summarizes the approach for solving the **Balanced Parenthesis** problem, as discussed in the video "L2. Check for Balanced Parentheses | Stack and Queue."

## 1. Problem Statement

The task is to determine if a given input string, containing only various types of brackets (e.g., `{}`, `[]`, `()`), is a **balanced parenthesis string**.

## 2. Conditions for a Balanced String

A string must satisfy three key conditions to be considered balanced:

1.  **Matching Types:** Every opening bracket should have a closing bracket of the same type. Conversely, every closing bracket should have a corresponding opening bracket of the same type.
2.  **Order (Nesting):** The brackets must follow the correct order. For example, a closing bracket must correspond to the opening bracket immediately preceding it in the sequence of unmatched openings.

If the string fails any of these conditions (e.g., an opening bracket lacks a closing pair, or a closing bracket appears without an opening pair, or the order is incorrect), it is considered unbalanced.

## 3. Solution Approach: Using a Stack

The core requirement of the problem is that when a closing bracket is encountered, we must check the **last opening bracket that was seen**.

The **Stack** data structure is ideal for this because it follows a **Last In, First Out (LIFO)** mechanism, allowing us to easily retrieve the most recently encountered item (the last opening bracket).

## 4. Implementation Steps (Pseudo Code Logic)

The solution involves iterating over the input string and using a stack (`S`) to preserve opening brackets.

### Initialization

1.  Define a stack (`stack s`) that stores characters.
2.  Iterate through the string from index 0 to $n-1$ (where $n$ is the string length).

### Processing Characters

#### A. Handling Opening Brackets

If the character (`S of I`) is any type of opening bracket (`{`, `[`, or `(`):
*   **Push** the bracket onto the stack to preserve it.

#### B. Handling Closing Brackets

If the character (`S of I`) is a closing bracket:

1.  **Check for Empty Stack:** Immediately check if the stack is empty.
    *   If the stack is empty, it means there is no corresponding opening bracket for the current closing bracket. **Return `false`** and break out.
2.  **Retrieve and Pop:** If the stack is not empty, get the top element (`CH = St.top()`) and **pop** it out. This element represents the last opening bracket encountered.
3.  **Comparison:** Compare the current closing bracket (`S of I`) with the retrieved opening bracket (`CH`).
    *   If the closing bracket is `}` (curly), the opening bracket (`CH`) must be `{`.
    *   If the closing bracket is `]` (square), the opening bracket (`CH`) must be `[`.
    *   If the closing bracket is `)` (parenthesis), the opening bracket (`CH`) must be `(`.
4.  **Mismatch:** If the opening and closing brackets do **not match** (e.g., closing `}` but opening `(`): **Return `false`**.

### Final Check

After the iteration loop ends, one final condition must be verified:

*   **Check Stack Emptiness:** If the stack is empty (`return stack.empty()`), it means that for every opening bracket that was preserved, a corresponding, correctly ordered closing bracket was found. **Return `true`**.
*   If the stack is **not empty**, it means there are still opening brackets remaining that never found a corresponding closing bracket. **Return `false`**.

## 5. Complexity Analysis

*   **Time Complexity:** O(N).
    *   This is determined by iterating over the string once (where N is the length of the string).
*   **Space Complexity:** O(N).
    *   In the worst case (if the entire string consists only of opening brackets), the stack would store N characters. This space complexity cannot be improved because storage is necessary to match every opening bracket with its corresponding closing bracket.
```