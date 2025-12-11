

# 📘 Postfix to Prefix Conversion — Java Solution

This project provides a simple and efficient Java implementation for converting a **postfix expression** (Reverse Polish Notation) into its corresponding **prefix expression** (Polish Notation).
The conversion uses a **stack-based approach**, making it ideal for learning, competitive programming, and interview preparation.

---

## 🚀 Features

* Converts postfix expressions (e.g., `AB+C*`) to prefix (e.g., `*+ABC`)
* Uses a clean and intuitive **stack algorithm**
* Supports operators:

  ```
  +, -, *, /, ^
  ```
* Supports single-character operands (`A–Z`, `a–z`, `0–9`)
* Efficient time complexity: **O(n)**

---

## 🧠 Algorithm Explanation

### 📌 Key Concept

In **postfix notation**, every operator appears *after* its operands.
To convert postfix → prefix, we:

1. Read the expression from **left to right**.
2. If a character is an **operand**, push it on the stack.
3. If it is an **operator**:

    * Pop two operands from the stack:

        * First pop → right operand (`t1`)
        * Second pop → left operand (`t2`)
    * Form a prefix expression:

      ```
      operator + t2 + t1
      ```
    * Push the result back into the stack.
4. At the end, the stack contains **one final prefix expression**.

---

## 📚 Example

### Input (Postfix):

```
ABC*+
```

### Steps:

| Read | Action                            |
| ---- | --------------------------------- |
| A    | push "A"                          |
| B    | push "B"                          |
| C    | push "C"                          |
| *    | pop C, pop B → "*BC" → push       |
| +    | pop "*BC", pop A → "+A*BC" → push |

### Output (Prefix):

```
+A*BC
```

---

## 🧩 Java Code (Complete Implementation)

```java
class Solution {
    private static boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    static String postToPre(String post_exp) {
        Stack<String> st = new Stack<>();
        int n = post_exp.length();

        for(int i = 0; i < n; i++){
            char ch = post_exp.charAt(i);

            // Operand → push directly
            if(!isOperator(ch)){
                st.push(ch + "");
            }
            // Operator → pop 2 operands, build prefix, push back
            else{
                String t1 = st.pop();   // right operand
                String t2 = st.pop();   // left operand
                StringBuilder sb = new StringBuilder();

                sb.append(ch);   // operator
                sb.append(t2);   // left
                sb.append(t1);   // right

                st.push(sb.toString());
            }
        }

        // Final prefix expression
        return st.pop();
    }
}
```

---

## ⏱ Time & Space Complexity

| Measure   | Complexity |
| --------- | ---------- |
| **Time**  | O(n)       |
| **Space** | O(n)       |

---

## 🧪 Usage Example

```java
public class Main {
    public static void main(String[] args) {
        String postfix = "ABC*+";
        String prefix = Solution.postToPre(postfix);
        System.out.println(prefix); // Output: +A*BC
    }
}
```

---

## 📄 Notes

* The code assumes all inputs are valid postfix expressions.
* Parentheses are **not needed** in prefix or postfix expressions because order is encoded.
* The solution supports only single-character operands; multi-digit operands require tokenization.

---

## MultiDigit Operands

To make your **postfix → prefix** converter work for **multi-character operands** (e.g., `AB`, `123`, `var1`, `x10`, `hello`), you must change **HOW YOU READ THE INPUT**.

Right now, you process character-by-character:

```java
char ch = post_exp.charAt(i);
```

This only works when each operand is a **single character**.

For multi-character operands, you must treat the expression as **tokens**, NOT as characters.

---

# ✅ ✔️ APPROACH — Space-Separated Tokens (Recommended)

If your postfix input is like:

```
12 3 + 4 *
```

Then split by space:

### ✔ Modified Code

```java
static String postToPre(String post_exp) {
    Stack<String> st = new Stack<>();
    String[] tokens = post_exp.trim().split("\\s+");

    for (String token : tokens) {

        if (!isOperator(token)) {
            // operand
            st.push(token);
        } else {
            // operator
            String t1 = st.pop();
            String t2 = st.pop();
            String expr = token + " " + t2 + " " + t1;
            st.push(expr);
        }
    }
    return st.pop();
}

// Overload isOperator for string tokens
private static boolean isOperator(String s){
    return s.length() == 1 &&
        "+-*/^".contains(s);
}
```

### ✔ Input:

```
12 3 +
```

### ✔ Output:

```
+ 12 3
```

---

