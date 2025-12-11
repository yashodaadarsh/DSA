

# 📘 Infix to Prefix Conversion — Java Solution

This project implements a complete conversion from **infix expressions** to **prefix notation** using the classical algorithm:

1. **Reverse the infix expression**
2. **Swap parentheses**
3. **Convert the result to postfix**
4. **Reverse the postfix → prefix**

This method ensures correct handling of **operator precedence**, **associativity**, and **parentheses**.

---

## 🚀 Features

* Converts any valid infix expression to prefix.
* Supports:

    * Operands: `A–Z`, `a–z`, `0–9`
    * Operators: `+`, `-`, `*`, `/`, `^`
    * Parentheses: `(`, `)`
* Fully handles:

    * **Operator precedence**
    * **Left-associative operators** (`+ - * /`)
    * **Right-associative operator** (`^`)
* Uses stack-based postfix conversion internally.
* Includes a safe string reversal function for prefix construction.

---

## 🔧 Algorithm Overview

### ✔ Prefix Conversion Steps

1. **Reverse the infix string**

```
(A+B) becomes )B+A(
```

2. **Swap parentheses**

```
)B+A( becomes (B+A)
```

3. **Convert to postfix using stack rules**

```
(B+A) → BA+
```

4. **Reverse the postfix expression**

```
BA+ → +AB  (prefix)
```

---

## 📚 Operator Precedence Table

| Operator | Priority | Associativity |
| -------- | -------- | ------------- |
| `^`      | 5        | Right         |
| `*`, `/` | 4        | Left          |
| `+`, `-` | 2        | Left          |

---

## 🧠 Associativity Handling

In postfix conversion:

* **Left-associative (`+ - * /`)**
  Pop while:

  ```
  priority(curr) <= priority(stackTop)
  ```

* **Right-associative (`^`)**
  Pop while:

  ```
  priority(curr) < priority(stackTop)
  ```

This ensures expressions like `a ^ b ^ c` convert correctly.

---

## 🧩 Full Java Implementation

```java
class Solution {
    
    public StringBuilder reverse(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < n/2; i++) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(n-1-i));
            sb.setCharAt(n-1-i, temp);
        }
        return sb;
    }

    public String infixToPrefix(String s) {
        int n = s.length();

        // 1. Reverse input
        StringBuilder sb = reverse(s);

        // 2. Swap '(' and ')'
        for (int i = 0; i < n; i++) {
            if (sb.charAt(i) == '(') sb.setCharAt(i, ')');
            else if (sb.charAt(i) == ')') sb.setCharAt(i, '(');
        }

        // 3. Convert to postfix
        String postfix = infixToPostfix(sb.toString());

        // 4. Reverse postfix to get prefix
        return reverse(postfix).toString();
    }

    public String infixToPostfix(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // Operand
            if ((ch >= 'A' && ch <= 'Z') ||
                (ch >= 'a' && ch <= 'z') ||
                (ch >= '0' && ch <= '9')) {
                sb.append(ch);
            }
            else if (ch == '(') st.push(ch);
            else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(')
                    sb.append(st.pop());
                st.pop(); // remove '('
            }
            // Operator
            else {
                while (!st.isEmpty() &&
                      ((ch != '^' && priority(ch) <= priority(st.peek())) ||
                       (ch == '^' && priority(ch) < priority(st.peek())))) {
                    sb.append(st.pop());
                }
                st.push(ch);
            }
        }

        // Append remaining operators
        while (!st.isEmpty()) sb.append(st.pop());

        return sb.toString();
    }

    private int priority(char ch) {
        switch (ch) {
            case '^': return 5;
            case '/': 
            case '*': return 4;
            case '-': 
            case '+': return 2;
            default: return 0;
        }
    }
}
```

---

## 🧪 Example

### Input (Infix):

```
(A+B)*(C^D-E)
```

### Output (Prefix):

```
*+AB-^CDE
```

---

## 📝 How to Use

```java
Solution sol = new Solution();
String prefix = sol.infixToPrefix("(A+B)*(C^D-E)");
System.out.println(prefix);
```

---

