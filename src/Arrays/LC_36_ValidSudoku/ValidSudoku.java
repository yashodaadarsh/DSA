package Arrays.LC_36_ValidSudoku;

import java.util.HashSet;

public class ValidSudoku {

    // -------------------------
    // Approach 1: Iterative Checks (Brute Force)
    // -------------------------
    public boolean isValidSudokuBruteForce(char[][] board) {
        // Validate rows
        for (int row = 0; row < 9; row++) {
            HashSet<Character> set = new HashSet<>();
            for (int col = 0; col < 9; col++) {
                char c = board[row][col];
                if (c == '.') continue;
                if (set.contains(c)) return false;
                set.add(c);
            }
        }

        // Validate columns
        for (int col = 0; col < 9; col++) {
            HashSet<Character> set = new HashSet<>();
            for (int row = 0; row < 9; row++) {
                char c = board[row][col];
                if (c == '.') continue;
                if (set.contains(c)) return false;
                set.add(c);
            }
        }

        // Validate 3x3 sub-boxes
        for (int startRow = 0; startRow < 9; startRow += 3) {
            for (int startCol = 0; startCol < 9; startCol += 3) {
                HashSet<Character> set = new HashSet<>();
                for (int r = startRow; r < startRow + 3; r++) {
                    for (int c = startCol; c < startCol + 3; c++) {
                        char ch = board[r][c];
                        if (ch == '.') continue;
                        if (set.contains(ch)) return false;
                        set.add(ch);
                    }
                }
            }
        }

        return true;
    }

    // -------------------------
    // Approach 2: Smart Approach (Single Pass with String Set)
    // -------------------------
    public boolean isValidSudokuSmart(char[][] board) {
        HashSet<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;

                String rowKey = c + "_in_row_" + i;
                String colKey = c + "_in_col_" + j;
                String boxKey = c + "_in_box_" + (i / 3) + "_" + (j / 3);

                if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                    return false;
                }
            }
        }

        return true;
    }

    // -------------------------
    // Approach 3: Cell-by-Cell Validation
    // -------------------------
    public boolean isValidSudokuCellCheck(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!isPossible(i, j, board, board[i][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isPossible(int row, int col, char[][] board, char ch) {
        for (int i = 0; i < 9; i++) {
            // Row check
            if (i != col && board[row][i] == ch) return false;
            // Column check
            if (i != row && board[i][col] == ch) return false;
            // Subgrid check
            int subRow = 3 * (row / 3) + i / 3;
            int subCol = 3 * (col / 3) + i % 3;
            if (subRow != row && subCol != col && board[subRow][subCol] == ch) return false;
        }
        return true;
    }

    // -------------------------
    // Example Usage
    // -------------------------
    public static void main(String[] args) {
        ValidSudoku solver = new ValidSudoku();

        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println("Brute Force Check: " + solver.isValidSudokuBruteForce(board));
        System.out.println("Smart Approach Check: " + solver.isValidSudokuSmart(board));
        System.out.println("Cell-by-Cell Check: " + solver.isValidSudokuCellCheck(board));
    }
}
