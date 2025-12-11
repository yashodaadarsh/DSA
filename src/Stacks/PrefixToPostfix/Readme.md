

# 📘 Prefix to Postfix Conversion — Java Solution

This repository contains a clean and efficient Java implementation for converting a **prefix expression** (Polish notation) into a **postfix expression** (Reverse Polish Notation). The conversion uses a simple **stack-based approach**, making it ideal for DSA practice, competitive programming, and interview preparation.

---

## 🚀 Features

* Converts prefix expressions (e.g., `*+AB-CD`) to postfix (e.g., `AB+CD-*`)
* Supports all basic operators:

  ```
  +, -, *, /, ^
  ```
* Supports single-character operands (`A–Z`, `a–z`, `0–9`)
* Time-efficient linear algorithm: **O(n)**

---

## 🧠 Algorithm Explanation

### Prefix Expression Evaluation Rule

In prefix notation, operators appear **before** their operands.

Example:

```
+AB → A + B
```

### Conversion Logic (Prefix → Postfix)

To convert prefix → postfix:

1. **Scan the prefix expression from RIGHT to LEFT**.
2. For each character:

    * If it's an **operand**, push it into the stack.
    * If it's an **operator**, pop the top two elements:

        * First pop → left operand (`t1`)
        * Second pop → right operand (`t2`)
    * Form a postfix expression:

      ```
      t1 + t2 + operator
      ```
    * Push this back on the stack.
3. At the end, the stack contains exactly **one element**, which is the postfix expression.

---

## 📚 Example

### Input (Prefix):

```
*+AB-CD
```

### Step-by-step:

| Read | Stack Contents            | Action |
| ---- | ------------------------- | ------ |
| D    | D                         | push   |
| C    | D, C                      | push   |
| -    | D, C → pop → postfix: CD- | push   |
| B    | CD-, B                    | push   |
| A    | CD-, B, A                 | push   |
| +    | CD-, B, A → pop → AB+     | push   |
| *    | AB+, CD- → pop → AB+CD-*  | push   |

### Output (Postfix):

```
AB+CD-*
```

---

## 🧩 Full Java Implementation

```java
class Solution {

    private static boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    static String preToPost(String pre_exp) {
        Stack<String> st = new Stack<>();
        int n = pre_exp.length();

        // Traverse prefix from right to left
        for (int i = n - 1; i >= 0; i--) {
            char ch = pre_exp.charAt(i);

            // Operand → push directly
            if (!isOperator(ch)) {
                st.push(ch + "");
            }
            // Operator → pop two operands, merge, push result
            else {
                String t1 = st.pop(); // left operand
                String t2 = st.pop(); // right operand

                StringBuilder sb = new StringBuilder();
                sb.append(t1).append(t2).append(ch);

                st.push(sb.toString());
            }
        }

        return st.pop();
    }
}
```

---

## ⏱ Complexity Analysis

| Component            | Complexity                                |
| -------------------- | ----------------------------------------- |
| **Time Complexity**  | O(n) — single pass                        |
| **Space Complexity** | O(n) — stack for intermediate expressions |

---

## 🧪 Usage Example

```java
public class Main {
    public static void main(String[] args) {
        String prefix = "*+AB-CD";
        String postfix = Solution.preToPost(prefix);
        System.out.println(postfix); 
        // Output: AB+CD-*
    }
}
```

---

## 📄 Notes

* Assumes all operands are single characters.
* Works only with **binary operators**.
* Extendable to multi-character operands with tokenization.

---