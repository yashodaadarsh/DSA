

# 📘 Infix to Postfix Conversion — Java Implementation

This project provides a Java implementation of the **Infix to Postfix (Reverse Polish Notation)** conversion algorithm using a stack.
It follows the classic **Shunting Yard Algorithm** with special handling for **operator associativity**, particularly the right-associative exponent operator `^`.

---

## 🚀 Features

* Converts an infix expression (e.g., `A+B*(C^D-E)^(F+G*H)-I`) into postfix form.
* Supports:

    * Operands: `A-Z`, `a-z`, `0-9`
    * Operators: `+`, `-`, `*`, `/`, `^`
    * Parentheses: `(`, `)`
* Correctly handles:

    * **Operator precedence**
    * **Left-associative operators** (`+ - * /`)
    * **Right-associative operator** (`^`)
* Uses a `Stack<Character>` internally.

---

## 📚 Operator Precedence Table

| Operator | Precedence | Associativity |
| -------- | ---------- | ------------- |
| `^`      | Highest    | Right         |
| `* /`    | Medium     | Left          |
| `+ -`    | Low        | Left          |

---

## 🧠 Associativity Rules Used

* **Left-associative operators** (`+ - * /`)
  Pop from stack while:

  ```
  priority(curr) <= priority(top)
  ```

* **Right-associative operator** (`^`)
  Pop from stack while:

  ```
  priority(curr) < priority(top)
  ```

This ensures expressions like:

```
a ^ b ^ c
```

correctly convert to:

```
a b c ^ ^
```

---

## 🧩 Code Overview

### `infixToPostfix(String s)`

Main conversion function that:

* Scans the string left-to-right
* Appends operands immediately
* Pushes operators onto a stack
* Pops based on precedence & associativity
* Removes matching parentheses
* Appends all remaining operators at the end

---

## 🧮 Example

**Input:**

```
A+B*(C^D-E)^(F+G*H)-I
```

**Output (postfix):**

```
ABCD^E-*FGH*+^+I-
```

---

## 📝 Full Java Code

```java
class Solution {
    public static String infixToPostfix(String s) {
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
            // Opening parenthesis
            else if (ch == '(') st.push(ch);
            
            // Closing parenthesis
            else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(')
                    sb.append(st.pop());
                st.pop(); // pop '('
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
        
        // Append any remaining operators
        while (!st.isEmpty())
            sb.append(st.pop());
        
        return sb.toString();
    }

    private static int priority(char ch) {
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

## 🧪 How to Use

Call:

```java
String postfix = Solution.infixToPostfix("A+B*(C^D-E)");
System.out.println(postfix);
```

---

