

# 📘 Postfix to Infix Conversion — Java Solution

This project implements a method to convert a **postfix expression (Reverse Polish Notation)** into its corresponding **infix expression** using a stack-based algorithm.

The solution supports:

* All standard binary operators: `+`, `-`, `*`, `/`, `^`
* Single-character operands (`A–Z`, `a–z`, `0–9`)
* Automatic insertion of parentheses to preserve precedence

---

## 🚀 Features

* Converts any valid postfix expression into a fully parenthesized infix expression.
* Uses a stack to reconstruct sub-expressions based on operator order.
* Ensures correct operand ordering for binary operators.
* Simple and efficient (`O(n)` time complexity).

---

## 🧠 How the Algorithm Works

1. **Scan the postfix expression from left to right.**
2. For each character:

    * If it's an **operand**, push it onto the stack.
    * If it's an **operator**:

        * Pop the top two elements → `operand1`, `operand2`
        * Create a new infix expression:

          ```
          (operand1 operator operand2)
          ```
        * Push the new expression back onto the stack.
3. At the end, the stack contains exactly **one string**, which is the final infix expression.

---

## 📚 Example

### Input (Postfix Expression):

```
ABC*+
```

### Execution:

* Read `A` → push
* Read `B` → push
* Read `C` → push
* Read `*` → combine → `(B*C)`
* Read `+` → combine → `(A+(B*C))`

### Output:

```
(A+(B*C))
```

---

## 🧩 Full Java Implementation

```java
class Solution {

    private static boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    static String postToInfix(String exp) {
        Stack<String> st = new Stack<>();
        int n = exp.length();

        for (int i = 0; i < n; i++) {
            char ch = exp.charAt(i);

            // Operand
            if (!isOperator(ch)) {
                st.push(ch + "");
            }
            // Operator
            else {
                String s2 = st.pop();
                String s1 = st.pop();

                StringBuilder sb = new StringBuilder();
                sb.append("(");
                sb.append(s1);
                sb.append(ch);
                sb.append(s2);
                sb.append(")");

                st.push(sb.toString());
            }
        }

        // Final infix expression
        return st.pop();
    }
}
```

---

## 📝 Time & Space Complexity

| Operation | Complexity                                 |
| --------- | ------------------------------------------ |
| Time      | **O(n)** — one scan with stack operations  |
| Space     | **O(n)** — stack holds partial expressions |

---

## 🧪 How to Use

```java
String postfix = "ABC*+";
String infix = Solution.postToInfix(postfix);
System.out.println(infix);  
// Output: (A+(B*C))
```

---

## 📄 Notes

* This algorithm builds a *fully parenthesized* expression.
* Works for any single-character operand.
* Extendable to handle multi-character operands with slight modifications.

---

