
# Maximum Number of Words You Can Type | Simple Approach | Leetcode 1935


## 📖 Problem Statement: LeetCode 1935

**Problem Name:** Maximum Number of Words You Can Type
**Difficulty:** Easy

You are given a **malfunctioning keyboard** where some letter keys do not work. All other keys on the keyboard work properly.

**Input:**
*   A string `text` of words separated by a single space (no leading or trailing spaces).
*   A string `brokenLetters` containing all distinct letter keys that are broken.

**Output:**
*   Return the **number of words in `text` that you can fully type** using the keyboard.

### Example

Let's say:
*   `text = "hello world"`
*   `brokenLetters = "ad"`

1.  **"hello"**:
    *   'h' is not broken.
    *   'e' is not broken.
    *   'l' is not broken.
    *   'l' is not broken.
    *   'o' is not broken.
    *   Since no letter in "hello" is broken, **"hello" can be fully typed**.

2.  **"world"**:
    *   'w' is not broken.
    *   'o' is not broken.
    *   'r' is not broken.
    *   'l' is not broken.
    *   'd' is broken (present in `brokenLetters`).
    *   Since 'd' is broken, **"world" cannot be fully typed**.

Therefore, the total number of words that can be fully typed is **1**.

## 🧠 Simple Approach / Thought Process

The problem can be solved with a straightforward approach.

1.  **Pre-process Broken Letters:**
    *   Create a **boolean array (or map) of size 26** (for lowercase English letters). This array will represent whether a character is broken or not. For example, index 0 could represent 'a', index 1 for 'b', and so on, up to index 25 for 'z'.
    *   **Iterate through the `brokenLetters` string**. For each broken character `ch`, mark its corresponding index in the boolean array as `true` (indicating it's broken). All other characters will remain `false` by default (not broken).

2.  **Iterate Through the Text:**
    *   Initialize a `result` variable to `0` to count typable words.
    *   Initialize a boolean variable `canType` to `true` at the beginning of processing each word. This variable tracks if the *current word* being examined can be typed.
    *   **Iterate through the `text` string character by character**.

3.  **Word Processing Logic:**
    *   **If a space character `' '` is encountered**: This signifies the **end of a word**.
        *   Check the `canType` variable. If `canType` is `true` (meaning no broken letters were found in the just-completed word), **increment `result`**.
        *   **Reset `canType` to `true`** to prepare for the next word.
    *   **If a non-space character `ch` is encountered**:
        *   Check if `ch` is broken by looking it up in the pre-populated boolean array (e.g., `map[ch - 'a']`).
        *   If `ch` is found to be broken (`map[ch - 'a'] == true`), then the current word **cannot be fully typed**. Set `canType` to `false` for this word.

4.  **Handle the Last Word:**
    *   After the loop finishes, the **last word might not have been counted** because there's no trailing space to trigger the space-handling logic.
    *   Perform one final check: if `canType` is `true` after the loop (meaning the very last word was typable), **increment `result` one last time**.

5.  **Return `result`**.

## ⚙️ Complexity Analysis

*   **Time Complexity:** **O(N + M)**
    *   `N` is the length of the `text` string. We iterate through `text` once.
    *   `M` is the length of the `brokenLetters` string. We iterate through `brokenLetters` once to populate the map.
*   **Space Complexity:** **O(26)** or **O(1)**
    *   We use a boolean array of fixed size 26 to store the broken letters, as all letters are lowercase English letters.
```