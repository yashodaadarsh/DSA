
# Valid Sudoku | Leetcode 36

## Problem Description
The goal is to determine if a 9x9 Sudoku board is valid according to these rules:
1.  **Each row** must contain the digits 1-9 without repetition.
2.  **Each column** must contain the digits 1-9 without repetition.
3.  **Each of the nine 3x3 sub-boxes** (often called "boxes" or "squares") must contain the digits 1-9 without repetition.

The Sudoku board is given as a 2D array of characters. Empty cells are represented by a '.' character. Only the filled cells need to be validated according to the rules.

**Example of an Invalid Sudoku:**
*   A row with duplicate numbers (e.g., '8' appearing twice in the same row) makes the Sudoku invalid.
*   A 3x3 box with duplicate numbers (e.g., '3' appearing twice in the same box) makes the Sudoku invalid.

## Approaches

### Approach 1: Iterative Checks (Brute Force)

This approach directly implements the three validation rules by checking each component (rows, columns, and 3x3 boxes) separately. If any check fails, the Sudoku is invalid.

#### Logic:
1.  **Validate Rows**:
    *   Iterate through each row from `row = 0` to `8`.
    *   For each row, use an `unordered_set<char>` to store encountered digits.
    *   Iterate through each column `col = 0` to `8` within the current row.
    *   If the cell `board[row][col]` is '.', **skip it**.
    *   If the digit `board[row][col]` is already present in the `set`, it means a duplicate has been found in the current row, so **return `false`**.
    *   Otherwise, add the digit to the `set`.
    *   After checking all cells in a row, clear the `set` for the next row.

2.  **Validate Columns**:
    *   Similar to rows, iterate through each column from `col = 0` to `8`.
    *   For each column, use an `unordered_set<char>` to store encountered digits.
    *   Iterate through each row `row = 0` to `8` within the current column (i.e., traverse downwards).
    *   If `board[row][col]` is '.', **skip it**.
    *   If the digit `board[row][col]` is already present in the `set`, it means a duplicate has been found in the current column, so **return `false`**.
    *   Otherwise, add the digit to the `set`.
    *   After checking all cells in a column, clear the `set` for the next column.

3.  **Validate 3x3 Sub-Boxes**:
    *   This is the most complex part of this approach due to traversal.
    *   To iterate through all nine 3x3 boxes, we can use nested loops that define the starting row and starting column of each box.
        *   `startRow` loop: `0, 3, 6` (increments by `3` each time).
        *   `startCol` loop: `0, 3, 6` (increments by `3` each time).
    *   For each `(startRow, startCol)` pair, define the `endRow` (`startRow + 2`) and `endCol` (`startCol + 2`).
    *   Within each box (from `startRow` to `endRow` and `startCol` to `endCol`), use an `unordered_set<char>` to check for duplicates.
    *   If `board[row][col]` is '.', **skip it**.
    *   If the digit `board[row][col]` is already present in the `set`, a duplicate is found in the 3x3 box, so **return `false`**.
    *   Otherwise, add the digit to the `set`.
    *   Clear the `set` after validating each box.

If all three checks (rows, columns, and boxes) pass without returning `false`, then the Sudoku is **valid**, and we **return `true`**.

### Approach 2: Smart Approach (Single Pass with String Set)

This approach is considered "smarter" and "simpler" as it validates all three conditions in a **single pass** through the 9x9 matrix. It uses a single `unordered_set<string>` to store unique "stories" or "facts" about each number encountered.

#### Key Insight for 3x3 Boxes:
For any cell `(i, j)` on the board, the 3x3 box it belongs to can be uniquely identified by the integer division `(i / 3, j / 3)`. For example:
*   Cells in the top-left box (0,0 to 2,2) will all have `(i/3, j/3) = (0,0)`.
*   Cells in the second box of the first row (0,3 to 2,5) will all have `(i/3, j/3) = (0,1)`.

#### Logic:
1.  Initialize an `unordered_set<string>` to store the unique facts.
2.  Iterate through the entire 9x9 board using nested loops: `i` from `0` to `8` (rows) and `j` from `0` to `8` (columns).
3.  If `board[i][j]` is '.', **skip it** and continue to the next cell.
4.  For each non-empty cell `board[i][j]`, create three "story" strings:
    *   **Row Story**: `digit + "_in_row_" + to_string(i)`
        *   Example: For digit '8' at `(0,0)`, this would be `"8_in_row_0"`.
    *   **Column Story**: `digit + "_in_col_" + to_string(j)`
        *   Example: For digit '8' at `(0,0)`, this would be `"8_in_col_0"`.
    *   **Box Story**: `digit + "_in_box_" + to_string(i/3) + "_" + to_string(j/3)`
        *   Example: For digit '8' at `(0,0)`, this would be `"8_in_box_0_0"`.
5.  **Check for Duplicates**: Before inserting, attempt to insert all three strings into the `set`. If `set.insert()` returns `false` (meaning the string was already present), it indicates a duplicate has been found in that specific row, column, or box context. In this case, **immediately return `false`**.
    *   For instance, if `"8_in_row_0"` is already in the set, it means '8' has appeared twice in row 0.
    *   If `"8_in_box_0_0"` is already in the set, it means '8' has appeared twice in the top-left 3x3 box.

If the entire board is traversed without finding any duplicates (i.e., without returning `false`), then the Sudoku is **valid**, and we **return `true`**.

