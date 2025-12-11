

# 📘 Prefix to Infix Conversion — Java Solution

This project implements a stack-based algorithm to convert a **prefix expression** (Polish notation) into a **fully parenthesized infix expression**.

It supports:

* Standard operators: `+`, `-`, `*`, `/`, `^`
* Single-character operands (`A–Z`, `a–z`, `0–9`)
* Proper infix formatting using parentheses to preserve precedence

---

## 🚀 Features

* Converts valid prefix expressions into correct infix expressions.
* Uses a stack to manage intermediate sub-expressions.
* Handles operator precedence by always enclosing expressions in parentheses.
* Efficient linear time algorithm: **O(n)**.

---

## 🧠 How Prefix → Infix Conversion Works

In a **prefix expression**, operators appear *before* their operands.

Example:

```
+AB
```

means:

```
A + B
```

### Algorithm Steps:

1. Scan the prefix string **from right to left**.
2. If the character is an **operand** → push it on the stack.
3. If it is an **operator**:

    * Pop two expressions → `operand1`, `operand2`
    * Form a new infix expression:

      ```
      (operand1 operator operand2)
      ```
    * Push this expression back on the stack.
4. At the end, the stack contains one final infix string.

---

## 📚 Example

### Input (Prefix):

```
*-A/BC-/AKL
```

### Output (Infix):

```
((A-(B/C))*((A/K)-L))
```

---

## 🧩 Full Java Implementation

```java
class Solution {
    private static boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    static String preToInfix(String pre_exp) {
        Stack<String> st = new Stack<>();
        int n = pre_exp.length();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            char ch = pre_exp.charAt(i);

            // Operand: push directly
            if (!isOperator(ch)) {
                st.push(ch + "");
            }
            // Operator: pop two operands, combine
            else {
                String s1 = st.pop();
                String s2 = st.pop();
                StringBuilder sb = new StringBuilder();

                sb.append("(");
                sb.append(s1);
                sb.append(ch);
                sb.append(s2);
                sb.append(")");

                st.push(sb.toString());
            }
        }

        return st.pop();
    }
}
```

---

## 📝 Time & Space Complexity

| Operation | Complexity                                 |
| --------- | ------------------------------------------ |
| Time      | **O(n)** — scanning once                   |
| Space     | **O(n)** — stack holds partial expressions |

---

## 🧪 How to Use

```java
String prefix = "*-A/BC-/AKL";
String infix = Solution.preToInfix(prefix);
System.out.println(infix);
```

---

## 📄 Notes

* Produces fully parenthesized infix form.
* Works for all prefix expressions with binary operators.
* Can be extended to support multi-digit/multi-letter operands.

---

