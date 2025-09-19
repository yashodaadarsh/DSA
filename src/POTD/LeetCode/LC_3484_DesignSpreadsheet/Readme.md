
# LeetCode 3484: Design Spreadsheet (Simple Simulation)

This solution addresses LeetCode problem 3484, "Design Spreadsheet". Although the problem is marked Medium, the implementation strategy relies on **Simple Simulation**, following the problem requirements step-by-step.

The core task is to implement the `Spreadsheet` class, which simulates basic spreadsheet functionality, including setting cell values, resetting cells, and evaluating simple formulas.

## 1. Spreadsheet Structure and Initialization

The spreadsheet is defined as a grid.

*   **Columns:** There are **26 fixed columns**, labeled from A to Z.
*   **Rows:** The number of rows is specified as an input parameter.
*   **Cell Value:** Each cell holds an integer value between 0 and 10^5.
*   **Data Structure:** A 2D grid/vector (`vector<vector<int>>`) is used to represent the spreadsheet (`sheet`).

### `Spreadsheet(int rows)` (Constructor)

The constructor initializes the spreadsheet grid with the specified number of rows and 26 fixed columns. If a cell is referenced but has not been explicitly set using `SetCell`, its value is considered **zero**, so all cells are initially initialized to zero.

## 2. Handling Cell References (String Parsing)

Cell references are given in a string format, such as `A1` or `B10`. Rows in the input are **one-indexed**, while the implementation uses **zero-based indexing**.

To convert a cell string (`s`) into zero-based indices:

1.  **Column Calculation:** The column is represented by the first character (A-Z).
    *   `int column = cell - 'A';` (e.g., 'A' results in 0, 'B' results in 1, and so on).
2.  **Row Calculation:** The row is represented by the subsequent numbers.
    *   The numbers must be extracted using `substring` (starting from index 1) and converted from string to integer (`s2i`).
    *   `int row = s2i(cell.substring(1)) - 1;` (The `-1` adjusts for the one-based indexing specified in the problem).

## 3. Class Methods Implementation

### `SetCell(string cell, int value)`

This method sets the value of the specified cell.

1.  Calculate the 0-indexed `row` and `column` from the input `cell` string.
2.  Assign the input `value` to `sheet[row][column]`.

### `ResetCell(string cell)`

This method assigns **zero** to the specified cell.

1.  Calculate the 0-indexed `row` and `column` from the input `cell` string.
2.  Assign `0` to `sheet[row][column]`.

### `GetValue(string formula)`

This method evaluates a formula given in the form **`=x+y`**. The operands, $x$ and $y$, can be either cell references (like A1) or direct numerical values (integers).

**Steps for Evaluation:**

1.  **Remove the `=` symbol:** Create a new string `s` by taking a substring of the `formula` starting from index 1, effectively removing the leading equals sign.
2.  **Find the `+` index:** Determine the index of the addition operator (`+idx = s.find('+')`).
3.  **Extract Operands:**
    *   **Left Operand ($x$):** `string left = s.substring(0, +idx);`.
    *   **Right Operand ($y$):** `string right = s.substring(+idx + 1);`.
4.  **Calculate and Return:** The final result is the sum of the evaluated integer values of the left and right operands: `return Solve(left) + Solve(right);`.

#### Utility Function: `Solve(string s)`

A utility function is necessary to determine if the given string operand is an integer or a cell reference, and return its numerical value.

1.  **Check for Integer:** Check if the first character of the string `s` is a digit (`is_digit(s)`).
    *   If true, the operand is a numerical value. Return the string converted to an integer (`s2i(s)`).
2.  **Handle Cell Reference:** If it is not a digit, the operand must be a cell reference (e.g., A1).
    *   Calculate the 0-indexed `row` and `column` using the parsing logic defined above (Section 2).
    *   Return the value stored in the spreadsheet: `sheet[row][column]`.
    *   *Note: If a cell reference has not been explicitly set, the value retrieved from the grid (which was initialized to zero) will be 0.*

# Solution using Grid
````java
class Spreadsheet {

    private int[][] sheet ;

    public Spreadsheet(int rows) {
        sheet = new int[rows][26];
    }
    
    public void setCell(String cell, int value) {
        char col = cell.charAt(0);
        int row = Integer.parseInt( cell.substring(1) );
        sheet[row-1][col-'A'] = value;
    }
    
    public void resetCell(String cell) {
        char col = cell.charAt(0);
        int row = Integer.parseInt( cell.substring(1) );
        sheet[row-1][col-'A'] = 0;
    }
    
    public int getValue(String formula) {
        // String[] arr = formula.substring( 1 ).split("\\+");
        int ind = formula.indexOf("+");
        String cell1 = formula.substring(1,ind);
        String cell2 = formula.substring(ind+1);
        int val1 , val2 ;
        try{
            val1 = Integer.parseInt( cell1 );
        }
        catch( NumberFormatException e ){
            char col = cell1.charAt(0);
            int row = Integer.parseInt( cell1.substring(1) );
            val1 = sheet[row-1][col-'A'];
        }
        try{
            val2 = Integer.parseInt( cell2 );
        }
        catch( NumberFormatException e ){
            char col = cell2.charAt(0);
            int row = Integer.parseInt( cell2.substring(1) );
            val2 = sheet[row-1][col-'A'];
        }
        return val1 + val2;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */

````

# Solution using Map
````java
class Spreadsheet {

    public Spreadsheet(int rows) {
        
    }

    Map<String, Integer> map = new HashMap<>(); 
    
    public void setCell(String cell, int value) {
        map.put(cell, value);
    }
    
    public void resetCell(String cell) {
        map.remove(cell);
    }
    
    public int getValue(String formula) {
        int io = formula.indexOf('+');
        String cell1 = formula.substring(1, io);     
        String cell2 = formula.substring(io + 1); 
        
        int val1;
        if (cell1.charAt(0) > '9') {
            val1 = map.getOrDefault(cell1, 0);
        } else {
            val1 = Integer.parseInt(cell1);
        }

        int val2;
        if (cell2.charAt(0) > '9') {
            val2 = map.getOrDefault(cell2, 0);
        } else {
            val2 = Integer.parseInt(cell2);
        }

        return val1 + val2;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */
````
